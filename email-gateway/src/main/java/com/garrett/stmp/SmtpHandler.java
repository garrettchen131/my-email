package com.garrett.stmp;

import com.garrett.domain.protocol.SmtpContext;
import com.garrett.stmp.state.SmtpState;
import com.garrett.stmp.state.StateTask;
import com.garrett.holder.StateTaskHolder;
import com.garrett.util.ParseUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Slf4j
public class SmtpHandler implements Runnable {

    private Socket socket;

    private BufferedReader input = null;

    private PrintWriter output = null;

    private Class<? extends StateTask> taskName;

    @Getter
    private final SmtpContext.SmtpProtocolBuilder builder = SmtpContext.builder();

    public SmtpHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), false);
    }

    @Override
    public void run() {
        var stateTask = StateTaskHolder.getTask(SmtpState.INIT);
        stateTask.handle(this, null);
        while (true) {
            var line = readFromClient();
            if (line == null) {
                return;
            }
            log.info("line: {}", line);
            var pair = ParseUtils.parseLine(line.trim());
            if (pair.getKey() == null) {
                log.error("cmd is null");
                closeConnection();
                return;
            }
            var state = SmtpState.getState(pair.getKey());
            stateTask = StateTaskHolder.getTask(state);
            if (stateTask == null) {
                log.error("stateTask is null");
                closeConnection();
                return;
            }
            if (!stateTask.handle(this, pair.getValue())) {
                break;
            }
        }
        log.info("stmpHandler ends, smtpProtocol: {}", builder.build());
        closeConnection();

    }

    public void writeToClient(String outStr) {
        this.output.println(outStr);
        this.output.flush();
    }

    public String readFromClient() {
        try {
            return this.input.readLine();
        } catch (IOException e) {
            log.error("SmtpHandler.readFromClient io error ", e);
            closeConnection();
            return null;
        }
    }


    public void closeConnection() {
        if (socket != null && !socket.isClosed()) {
            try {
//                this.writeToClient("221 Bye");
                socket.close();
            } catch (Exception e) {
                log.error("SmtpHandler.run io error ", e);
            }
            socket = null;
        }
    }
}

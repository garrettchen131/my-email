package com.garrett.stmp;

import com.garrett.stmp.state.InitStateTask;
import com.garrett.stmp.state.StateTask;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class StmpHandler implements Runnable {

    private Socket socket;

    private BufferedReader input = null;

    private PrintWriter output = null;

    @Setter
    private StateTask task;

    public StmpHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true);
        this.task = new InitStateTask();
    }

    @Override
    public void run() {

        while (true) {
            task.handle(this);
            if (!task.hasNext()) {
                break;
            }
            task = task.next();
        }
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void writeToClient(String outStr) {
        this.output.println(outStr);
        this.output.flush();
    }

    public String readFromClient() {
        try {
            return this.input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            setTask(null);
            closeConnection();
            return null;
        }
    }


    public void closeConnection() {
        if (socket != null) {
            try {
//                this.writeToClient("221 Bye");
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            socket = null;
        }
    }
}

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
            if (task == null) {
                break;
            }
            task.handle(this);
        }
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        System.out.println("start handle");
//        try {
//            output.println("220 Welcome to my-mail smtp Server");
//            output.flush();
//            System.out.println(input.readLine());
//            output.println("250");
//            output.flush();
//            System.out.println(input.readLine());
//            output.println("250");
//            output.flush();
//            System.out.println(input.readLine());
//            output.println("250");
//            output.flush();
//            System.out.println(input.readLine());
//            output.println("354");
//            output.flush();
//            while (true) {
//                String line = input.readLine();
//                System.out.println(line);
//                if (".".equals(line)) {
//                    output.println("250");
//                    output.flush();
//                    break;
//                }
//            }
//            System.out.println(input.readLine());
//            output.println("221");
//            output.flush();
//            System.out.println("close socket1");
//            socket.close();
//            input.close();
//            output.close();
//            System.out.println("close socket2");
//            System.out.println(socket.isClosed());
//            System.out.println(socket.isConnected());
//            System.out.println(socket.isInputShutdown());
//            System.out.println(socket.isOutputShutdown());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
        //close the client
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

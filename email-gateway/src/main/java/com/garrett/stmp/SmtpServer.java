package com.garrett.stmp;


import java.io.IOException;
import java.net.ServerSocket;

public class SmtpServer implements Runnable {
    private int port = 25;
    private ServerSocket server = null;

    @Override
    public void run() {
        try {
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
    }

    public void start() throws IOException {
        server = new ServerSocket(port);
        while (true) {
            var socket = server.accept();
            System.out.println("accept connection");
            Thread.startVirtualThread(new SmtpHandler(socket));
        }
    }

    public void close() {
        if(server != null) {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

package com.garrett.stmp;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;

public class StmpServer implements Runnable {
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
            Socket socket = server.accept();
            System.out.println("accept connection");
            Thread.startVirtualThread(new StmpHandler(socket));
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

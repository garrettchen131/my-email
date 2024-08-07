package com.garrett;

import com.garrett.server.MyHttpServer;
import com.garrett.server.MySMTPServer;



/**
 * Hello world!
 *
 */
public class GatewayNewApp {
    public static void main(String[] args) throws Exception {

        Thread smtpServer = new Thread(new MySMTPServer());
        smtpServer.start();

        Thread httpServer = new Thread(new MyHttpServer());
        httpServer.start();

        httpServer.join();
        smtpServer.join();
    }
}

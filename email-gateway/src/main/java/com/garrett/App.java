package com.garrett;

import com.garrett.stmp.SmtpServer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        Thread stmp = Thread.ofVirtual().start(new SmtpServer());
        System.out.println("stmp server start");
        stmp.join();

    }
}

package com.garrett;

import com.garrett.domain.protocol.SmtpContext;
import com.garrett.stmp.SmtpServer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        //SmtpContext smtpContext = new SmtpContext("helo", "mail", "rcpt", "data", "quit");
        //System.out.println(smtpContext.toString());
        Thread stmp = Thread.ofVirtual().start(new SmtpServer());
        System.out.println("stmp server start");
        stmp.join();

    }
}

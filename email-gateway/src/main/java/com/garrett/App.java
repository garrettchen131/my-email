package com.garrett;

import com.garrett.stmp.StmpServer;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {

        Thread stmp = Thread.ofVirtual().start(new StmpServer());
        System.out.println("stmp server start");
        stmp.join();
    }
}

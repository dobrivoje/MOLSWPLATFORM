/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author root
 */
public class Loop {

    private static boolean done = false;

    public synchronized void startLoop() throws InterruptedException {
        while (!done) {
            TimeUnit.MILLISECONDS.sleep(10);
            System.err.println("Not done.");
        }
    }

    public synchronized void stopLoop() {
        while (true) {
            done = true;
            System.err.println("END !!!");
        }
    }
}

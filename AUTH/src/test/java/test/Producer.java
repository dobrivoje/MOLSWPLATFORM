/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

    private final Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        for (int m = 1; m < 1001; m++) {
            System.err.println("server produces->" + m);
            drop.put("message-" + String.valueOf(m));
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(100));
            } catch (InterruptedException ex) {
            }
        }

        drop.put("DONE");
    }

}

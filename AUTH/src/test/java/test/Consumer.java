/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author root
 */
public class Consumer implements Runnable {

    private final Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        for (String m = drop.take(); !m.equals("DONE"); m = drop.take()) {
            System.err.println("Message received : " + m);

            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException ex) {
            }
        }
    }
}

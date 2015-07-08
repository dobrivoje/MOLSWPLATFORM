/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.concurrent.TimeUnit;

public class Friend {

    private String name;

    private boolean firendBowed = false;

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public Friend(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>

    public synchronized void bow(Friend bower) throws InterruptedException {

        for (int i = 0; i < 10; i++) {

            while (!firendBowed) {
                System.err.println("čekam da mi se prijatelj nakloni...");
                wait();
            }

            System.out.format("Naklon br. %s, %s: %s mi se naklonio. Naklonjen ? %s%n", i, this.name, bower.getName(), firendBowed);
            firendBowed = true;

            TimeUnit.MILLISECONDS.sleep(1000);
            bower.bowBack(this);
            notifyAll();
        }
    }

    public synchronized void bowBack(Friend bower) throws InterruptedException {
        for (int i = 0; i < 10; i++) {

            while (!firendBowed) {
                System.err.println("čekam da mi se prijatelj poovo nakloni...");
                wait();
            }

            System.err.format("%s, %s: %s odgovorio naklonom! Naklonjen ? %s%n ", i, this.name, bower.getName(), firendBowed);
            firendBowed = false;
            notifyAll();
        }
    }
}

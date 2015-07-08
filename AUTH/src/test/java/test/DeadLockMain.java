/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author root
 */
public class DeadLockMain {

    public static void main(String[] args) {
        final Friend Dobri = new Friend("Dobri");
        final Friend Moki = new Friend("Moki");

        Thread tDobri = new Thread(() -> {
            try {
                Dobri.bow(Moki);
            } catch (InterruptedException ex) {
            }
        });

        Thread tMoki = new Thread(() -> {
            try {
                Moki.bow(Dobri);
            } catch (InterruptedException ex) {
            }
        });

        tDobri.start();
        tMoki.start();
    }
}

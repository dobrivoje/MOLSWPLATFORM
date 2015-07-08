/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

public class TestProdConsMain {

    public static void main(String[] args) {
        Drop drop = new Drop();
        Producer p = new Producer(drop);
        Consumer c = new Consumer(drop);

        new Thread(p).start();
        new Thread(c).start();
    }
}

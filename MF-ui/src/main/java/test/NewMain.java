package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author root
 */
public class NewMain {

    public static void main(String[] args) {
        List<Integer> tmpRandomList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                tmpRandomList.add((int) (1 + 100 * Math.random()));
            }
            System.err.println(tmpRandomList);
            tmpRandomList.clear();
        }

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author root
 */
public class CocaMain {

    public static void main(String[] args) {
        List<Map<String, Float>> L = new ArrayList<>();

        Map<String, Float> M = new HashMap();
        M.put("Maingrade", 137632.25f);
        M.put("Premium", 137632.25f);
        M.put("LPG", 21110.60f);

        L.add(M);
        
        Map<String, Float> M1 = new HashMap();
        M1.put("Maingrade", 11.25f);
        M1.put("Premium", 12.25f);
        M1.put("LPG", 13.60f);

        L.add(M1);

        for (Map<String, Float> lista : L) {
            for (Map.Entry<String, Float> e : lista.entrySet()) {
                String key = e.getKey();
                Float value = e.getValue();

                System.err.println("K-V : " + key + " - " + value);
            }
        }

    }

}

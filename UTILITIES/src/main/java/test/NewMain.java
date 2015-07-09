/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dprtenjak
 */
public class NewMain {

    public static void main(String[] args) {

        Map<String, Float> series = new HashMap<>();

        series.put("name: \"Internet Explorer\"", 12.23f);
        series.put("name: \"Mozilla\"", 33.43f);
        series.put("name: \"Chrome\"", 50.07f);

        String header = "series: [{ "
                + " name: TXXX, \n"
                + " data: [ \n";

        String footer = "       ]\n"
                + " }]";

        String s = new String();
        for (Map.Entry<String, Float> entrySet : series.entrySet()) {
            String key = entrySet.getKey();
            Float value = entrySet.getValue();

            s += "{" + key + ", " + value + " },";
        }

        System.err.println(header + s + footer);
    }

}

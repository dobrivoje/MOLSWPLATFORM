/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dobrivoje.utils.colors;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PleasingColorGenerator {

    private static Color genRandomColor(Color mix) {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);

        if (mix != null) {
            red = (red + mix.getRed()) / 2;
            green = (green + mix.getGreen()) / 2;
            blue = (blue + mix.getBlue()) / 2;
        }

        Color color = new Color(red, green, blue);

        return color;
    }

    /**
     *
     * @param color Color as a base for creating pleasing colors
     * @param howManySeries to generate
     * @return Javascript color model, eg. #FF23B7
     */
    public static List<String> generateRandomColor(Color color, int howManySeries) {
        List<String> L = new ArrayList<>();

        for (int i = 0; i < howManySeries; i++) {
            L.add("#" + Integer.toHexString(genRandomColor(color).getRGB()).substring(0, 6));
        }

        return L;
    }

    /**
     *
     * @param color Color as a base for creating pleasing colors
     * @return Javascript color model, eg. #FF23B7
     */
    public static String generateRandomColor(Color color) {
        return generateRandomColor(color, 1).get(0);
    }

}

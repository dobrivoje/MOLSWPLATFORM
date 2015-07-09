/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dobrivoje.highcharts.types;

/**
 *
 * @author root
 */
public enum ChartType {

    LINE(""),
    
    SPLINE(
            "chart: {type: 'spline'}, "
            + "tooltip: { headerFormat: '<b>{series.name}</b><br>', "
            + "pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'}, "
            + "plotOptions: { spline: { marker: { enabled: true } } }, "
    );
    

    private final String name;

    private ChartType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

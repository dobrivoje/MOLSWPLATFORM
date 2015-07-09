/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vaadin.highcharts;

/**
 *
 * @author root
 */
public enum ChartType {

    LINE(""),
    SPLINE(
            "chart: {type: 'spline'}, "
            + "         tooltip: { headerFormat: '<b>{series.name}</b><br>', "
            + "         pointFormat: '{point.x:%e. %b}: {point.y:.2f} m'}, "
            + "         plotOptions: { spline: { marker: { enabled: true } } }, "
    ),
    PIE_GRADIENT(
            "    Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) { "
            + "        return { "
            + "            radialGradient: { "
            + "                cx: 0.5, "
            + "                cy: 0.3, "
            + "                r: 0.7 "
            + "            }, "
            + "            stops: [ "
            + "                [0, color], "
            + "                [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken "
            + "            ] "
            + "        }; "
            + "    });"
            + " chart: { "
            + "            plotBackgroundColor: null, "
            + "            plotBorderWidth: null, "
            + "            plotShadow: false, "
            + "            type: 'pie' "
            + "        },"
            + " tooltip: { "
            + "            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' "
            + "        }, "
            + "        plotOptions: { "
            + "            pie: { "
            + "                allowPointSelect: true, "
            + "                cursor: 'pointer', "
            + "                dataLabels: { "
            + "                    enabled: true, "
            + "                    format: '<b>{point.name}</b>: {point.percentage:.1f} %', "
            + "                    style: { "
            + "                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black' "
            + "                    }, "
            + "                    connectorColor: 'silver' "
            + "                } "
            + "            } "
            + "        }, "
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

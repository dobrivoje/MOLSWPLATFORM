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

    //<editor-fold defaultstate="collapsed" desc="BAR">
    BAR(
            " chart: { "
            + "  type: 'bar' "
            + "},"
            + " tooltip: { "
            + "            valueSuffix: ' millions' "
            + "        }, "
            + "        plotOptions: { "
            + "            bar: { "
            + "                dataLabels: { "
            + "                    enabled: true "
            + "                } "
            + "            } "
            + "        }, "
            + "        legend: { "
            + "            layout: 'vertical', "
            + "            align: 'right', "
            + "            verticalAlign: 'top', "
            + "            x: -40, "
            + "            y: 80, "
            + "            floating: true, "
            + "            borderWidth: 1, "
            + "            backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'), "
            + "            shadow: true "
            + "        }, "
            + "        credits: { "
            + "            enabled: false "
            + "        },"
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="STACKED_BAR">
    STACKED_BAR(
            " chart: { "
            + "  type: 'column' "
            + "}, "
            + " legend: { "
            + "            align: 'right', "
            + "            x: -30, "
            + "            verticalAlign: 'top', "
            + "            y: 25, "
            + "            floating: true, "
            + "            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white', "
            + "            borderColor: '#CCC', "
            + "            borderWidth: 1, "
            + "            shadow: false "
            + "        }, "
            + " tooltip: { "
            + "            formatter: function () { "
            + "                return '<b>' + this.x + '</b><br/>' + "
            + "                    this.series.name + ': ' + this.y + '<br/>' + "
            + "                    'Total: ' + this.point.stackTotal; "
            + "            } "
            + "        },"
            + " plotOptions: { "
            + "            column: { "
            + "                stacking: 'normal', "
            + "                dataLabels: { "
            + "                    enabled: false, "
            + "                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white', "
            + "                    style: { "
            + "                        textShadow: '0 0 3px black' "
            + "                    } "
            + "                } "
            + "            } "
            + "        },"
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LINE">
    LINE(
            "subtitle: { "
            + "            text: 'Source: WorldClimate.com', "
            + "            x: -20 "
            + "        }, "
            + "        xAxis: { "
            + "            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', "
            + "                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'] "
            + "        }, "
            + "        yAxis: { "
            + "            title: { "
            + "                text: 'Temperature (°C)' "
            + "            }, "
            + "            plotLines: [{ "
            + "                value: 0, "
            + "                width: 1, "
            + "                color: '#808080' "
            + "            }] "
            + "        }, "
            + "        tooltip: { "
            + "            valueSuffix: '°C' "
            + "        }, "
            + "        legend: { "
            + "            layout: 'vertical', "
            + "            align: 'right', "
            + "            verticalAlign: 'middle', "
            + "            borderWidth: 0 "
            + "        },"
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="LINE_TIMESERIES_ZUMABLE">
    LINE_TIMESERIES_ZUMABLE(
            "chart: { zoomType: 'x' "
            + "            }, "
            + "            title: { "
            + "                text: 'USD to EUR exchange rate over time' "
            + "            }, "
            + "             "
            + "            plotOptions: { "
            + "                area: { "
            + "                    fillColor: { "
            + "                        linearGradient: { "
            + "                            x1: 0, "
            + "                            y1: 0, "
            + "                            x2: 0, "
            + "                            y2: 1 "
            + "                        }, "
            + "                        stops: [ "
            + "                            [0, Highcharts.getOptions().colors[0]], "
            + "                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')] "
            + "                        ] "
            + "                    }, "
            + "                    marker: { "
            + "                        radius: 2 "
            + "                    }, "
            + "                    lineWidth: 1, "
            + "                    states: { "
            + "                        hover: { "
            + "                            lineWidth: 1 "
            + "                        } "
            + "                    }, "
            + "                    threshold: null "
            + "                } "
            + "            },"
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SPLINE">
    SPLINE(
            "chart: { "
            + "            type: 'spline' "
            + "        }, "
            + " tooltip: { "
            + "            headerFormat: '<b>{series.name}</b><br>', "
            + "            pointFormat: '{point.x:%e. %b}: {point.y:.2f} m' "
            + "        }, "
            + "        plotOptions: { "
            + "            spline: { "
            + "                marker: { "
            + "                    enabled: true "
            + "                } "
            + "            } "
            + "        }, "
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AREA">
    AREA(
            " chart: { "
            + "            type: 'area' "
            + "        },"
            + " plotOptions: { "
            + "            area: { "
            + "                marker: { "
            + "                    enabled: false, "
            + "                    symbol: 'circle', "
            + "                    radius: 2, "
            + "                    states: { "
            + "                        hover: { "
            + "                            enabled: true "
            + "                        } "
            + "                    } "
            + "                } "
            + "            } "
            + "        }, "
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AREA_SPLINE">
    AREA_SPLINE(
            "chart: { "
            + "            type: 'areaspline' "
            + "        },"
            + "legend: { "
            + "            layout: 'vertical', "
            + "            align: 'left', "
            + "            verticalAlign: 'top', "
            + "            x: 150, "
            + "            y: 100, "
            + "            floating: true, "
            + "            borderWidth: 1, "
            + "            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF' "
            + "        },"
            + "        yAxis: { "
            + "            title: { "
            + "                text: 'Fruit units' "
            + "            } "
            + "        }, "
            + "        tooltip: { "
            + "            shared: true, "
            + "            valueSuffix: ' units' "
            + "        }, "
            + "        credits: { "
            + "            enabled: false "
            + "        },"
            + "        plotOptions: { "
            + "            areaspline: { "
            + "                fillOpacity: 0.5 "
            + "            } "
            + "        },"
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="AREA_RANGE">
    AREA_RANGE(
            " chart: { "
            + "     type: 'arearange', "
            + "     zoomType: 'x'"
            + "}, "
    ),
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PIE_GRADIENT">
    PIE_GRADIENT(
            " chart: { "
            + "            plotBackgroundColor: null, "
            + "            plotBorderWidth: null, "
            + "            plotShadow: false, "
            + "            type: 'pie' "
            + "        }, "
            + " plotOptions: { "
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
    //</editor-fold>

    private final String name;

    private ChartType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

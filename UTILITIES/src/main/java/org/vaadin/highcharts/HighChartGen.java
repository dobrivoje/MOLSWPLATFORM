package org.vaadin.highcharts;

import com.vaadin.ui.Component;
import java.util.List;
import java.util.Map;

public class HighChartGen extends AbstractHighChart {

    public HighChartGen() {
    }

    public Component generateHighChart(ChartType chartType, String title, Map<String, List<Float>> series) {
        HighChart hc = new HighChart();

        String out = generateHeader(title)
                + generateChartType(chartType)
                + generateSeriesBegin()
                + generateSeries(series)
                + generateSeriesEnd();

        hc.setHcjs(out);
        return hc;
    }

    //<editor-fold defaultstate="collapsed" desc="highchart generating methods">
    private String generateSeries(Map<String, List<Float>> series) {
        String s = new String();

        for (Map.Entry<String, List<Float>> es : series.entrySet()) {
            String key = es.getKey();
            List<Float> value = es.getValue();

            s += " { name: '" + key + "', data: " + value + "} , ";
        }
        return s;
    }

    private String generateSeriesForPIE(Map<String, Float> series) {
        String s = new String();

        for (Map.Entry<String, Float> entrySet : series.entrySet()) {
            String key = entrySet.getKey();
            Float value = entrySet.getValue();

            s += " { name: \"" + key + "\", y: " + value + "} , ";

        }

        return s;
    }

    public Component generateTEST222() {
        HighChart hc = new HighChart();

        String out = " chart: {\n"
                + "                zoomType: 'x'\n"
                + "            },\n"
                + "            title: {\n"
                + "                text: 'USD to EUR exchange rate over time'\n"
                + "            },\n"
                + "            subtitle: {\n"
                + "                text: document.ontouchstart === undefined ?\n"
                + "                        'Click and drag in the plot area to zoom in' : 'Pinch the chart to zoom in'\n"
                + "            },\n"
                + "            xAxis: {\n"
                + "                type: 'datetime'\n"
                + "            },\n"
                + "            yAxis: {\n"
                + "                title: {\n"
                + "                    text: 'Exchange rate'\n"
                + "                }\n"
                + "            },\n"
                + "            legend: {\n"
                + "                enabled: false\n"
                + "            },\n"
                + "            plotOptions: {\n"
                + "                area: {\n"
                + "                    fillColor: {\n"
                + "                        linearGradient: {\n"
                + "                            x1: 0,\n"
                + "                            y1: 0,\n"
                + "                            x2: 0,\n"
                + "                            y2: 1\n"
                + "                        },\n"
                + "                        stops: [\n"
                + "                            [0, Highcharts.getOptions().colors[0]],\n"
                + "                            [1, Highcharts.Color(Highcharts.getOptions().colors[0]).setOpacity(0).get('rgba')]\n"
                + "                        ]\n"
                + "                    },\n"
                + "                    marker: {\n"
                + "                        radius: 2\n"
                + "                    },\n"
                + "                    lineWidth: 1,\n"
                + "                    states: {\n"
                + "                        hover: {\n"
                + "                            lineWidth: 1\n"
                + "                        }\n"
                + "                    },\n"
                + "                    threshold: null\n"
                + "                }\n"
                + "            },\n"
                + "\n"
                + "            series: [{\n"
                + "                type: 'area',\n"
                + "                name: 'USD to EUR',\n"
                + "                data: data\n"
                + "            }] ";

        hc.setHcjs(out);
        
        return hc;

    }

    private String generateHeader(String title) {
        return "var options = { "
                + "title: {text: '" + title + "' }, ";
    }

    private String generateChartType(ChartType chartType) {
        return chartType.toString();
    }

    private String generateSeriesBegin() {
        return "series: [ ";
    }

    private String generateSeriesEnd() {
        return " ]};";
    }
    //</editor-fold>
}

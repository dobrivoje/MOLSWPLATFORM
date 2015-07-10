package org.vaadin.highcharts;

import com.vaadin.ui.Component;
import java.util.List;
import java.util.Map;

public class HighChartGen extends AbstractHighChart {

    public HighChartGen() {
    }

    public Component generateHighChart3(ChartType chartType, String title, Map<String, List<Object>> series, List<String> categories) {
        HighChart hc = new HighChart();

        String out = generateHeader(title)
                + generateChartType(chartType)
                + generateChartCategories(categories)
                + generateSeriesBegin()
                + generateSeries3(series)
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

    private String generateSeries2(Map<String, List<Integer>> series) {
        String s = new String();

        for (Map.Entry<String, List<Integer>> es : series.entrySet()) {
            String key = es.getKey();
            List<Integer> value = es.getValue();

            s += " { name: '" + key + "', data: " + value + "} , ";
        }
        return s;
    }

    private String generateSeries3(Map<String, List<Object>> series) {
        String s = new String();

        for (Map.Entry<String, List<Object>> es : series.entrySet()) {
            String key = es.getKey();
            List<Object> value = es.getValue();

            s += " { name: '" + key + "', data: " + value + "} , ";
        }
        return s;
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

    private String generateChartCategories(List<String> categories) {
        if (categories == null) {
            return "";
        } else {
            String s = new String();

            s = categories.stream().map((k) -> " ' " + k + " ', ").reduce(s, String::concat);

            s = s.substring(0, s.length() - 2);

            return "xAxis: {"
                    + "            categories: [ " + s + " ]"
                    + " },";
        }
    }

    private String generateSeriesEnd() {
        return " ]};";
    }
    //</editor-fold>

}

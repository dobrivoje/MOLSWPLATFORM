package org.vaadin.highcharts;

import com.vaadin.ui.Component;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import org.dobrivoje.utils.colors.PleasingColorGenerator;

public class HighChartGen extends AbstractHighChart {

    public HighChartGen() {
    }

    public Component generateHighChart(ChartType chartType, String title, Map<Object, List<Object>> series, List<Object> categories) {
        HighChart hc = new HighChart();

        String out = generateHeader(title)
                + chartType.toString()
                + generateChartCategories(categories)
                + generateSeriesBegin()
                + generateSeries(series)
                + generateSeriesEnd();

        hc.setHcjs(out);
        return hc;
    }

    public Component generatePleasingHighChart(ChartType chartType, String title, Map<Object, List<Object>> series, List<Object> categories) {
        HighChart hc = new HighChart();

        String out = generateHeader(title)
                + chartType.toString()
                + generateChartCategories(categories)
                + generateSeriesBegin()
                + generateSeriesWithPleasingColors(series)
                + generateSeriesEnd();

        hc.setHcjs(out);
        return hc;
    }

    //<editor-fold defaultstate="collapsed" desc="highchart generating methods">
    private String generateSeries(Map<Object, List<Object>> series) {
        String s = new String();

        for (Map.Entry<Object, List<Object>> es : series.entrySet()) {
            Object key = es.getKey();
            List<Object> value = es.getValue();

            s += " { name: '" + key + "', data: " + value + "} , ";
        }
        return s;
    }

    private String generateSeriesWithPleasingColors(Map<Object, List<Object>> series) {
        String s = new String();
        String pleasingColor;

        for (Map.Entry<Object, List<Object>> es : series.entrySet()) {
            Object key = es.getKey();
            List<Object> values = es.getValue();
            pleasingColor = PleasingColorGenerator.generateRandomColor(Color.RED);

            s += " { name: '" + key + "', data: " + values + "} , color: '" + pleasingColor + "', ";
        }
        return s;
    }

    private String generateHeader(String title) {
        return "var options = { "
                + "title: {text: '" + title + "' }, ";
    }

    private String generateSeriesBegin() {
        return "series: [ ";
    }

    private String generateChartCategories(List<Object> categories) {
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

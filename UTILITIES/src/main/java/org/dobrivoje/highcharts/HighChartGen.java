package org.dobrivoje.highcharts;

import com.vaadin.ui.Component;
import java.util.List;
import java.util.Map;
import org.dobrivoje.highcharts.types.ChartType;
import org.vaadin.highcharts.AbstractHighChart;
import org.vaadin.highcharts.HighChart;

public class HighChartGen extends AbstractHighChart {

    public HighChartGen() {
    }

    public Component generateHighChart(ChartType chartType, String title, Map<String, List<Integer>> series) {
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
    private String generateSeries(Map<String, List<Integer>> series) {
        String s = new String();

        for (Map.Entry<String, List<Integer>> es : series.entrySet()) {
            String key = es.getKey();
            List<Integer> value = es.getValue();

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
        return "series: "
                + "[ ";
    }

    private String generateSeriesEnd() {
        return " ]};";
    }
    //</editor-fold>
}

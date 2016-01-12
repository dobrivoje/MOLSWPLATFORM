package org.vaadin.highcharts;

import com.vaadin.ui.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.dobrivoje.utils.colors.AppealingColorGenerator;
import org.dobrivoje.utils.colors.IColorGenerator;

public class HighChartGen extends AbstractHighChart {

    public HighChartGen() {
    }

    /**
     *
     * @param chartType Chart type
     * @param title Title for the chart to be displayed
     * @param series Set for the series of data to be shown
     * @param xAxisValues
     * @return Vaadin component to be typically embedded into layout component.
     */
    public Component generateHighChart(ChartType chartType, String title, List<Object> xAxisValues, Map<Object, List> series) {
        return generateHighChart(chartType, title, xAxisValues, series, new AppealingColorGenerator());
    }

    /**
     *
     * @param chartType Chart type
     * @param title Title for the chart to be displayed
     * @param series Set for the series of data to be shown
     * @param xAxisValues 
     * @param colorGenerator
     * @return Vaadin component to be typically embedded into layout component.
     */
    public Component generateHighChart(ChartType chartType, String title, List<Object> xAxisValues, Map<Object, List> series, IColorGenerator colorGenerator) {
        HighChart hc = new HighChart();

        List<IColorGenerator> colorFactory = new ArrayList<>();

        // prvo želimo crvenu linuju kao marker za npr. target
        colorFactory.add((IColorGenerator) () -> {
            return Arrays.asList(255, 0, 0, 0.7f);
        });
        // zatim preostalih series.size()-1 serija...
        for (int i = 1; i < series.size(); i++) {
            colorFactory.add(new AppealingColorGenerator());
        }

        String out = generateHeader(title)
                + chartType.toString()
                + generateChartCategories(xAxisValues)
                + generateSeriesBegin()
                + generateSeries(series, colorFactory)
                + generateSeriesEnd();

        hc.setHcjs(out);

        return hc;
    }

    //<editor-fold defaultstate="collapsed" desc="highchart generating methods">
    private String generateSeries(Map<Object, List> series, List<IColorGenerator> colorFactory) {
        String s = new String();

        int ind = 0;
        for (Map.Entry<Object, List> es : series.entrySet()) {
            Object key = es.getKey();
            List<Object> value = es.getValue();

            List c = colorFactory.get(ind++).generateRGBColor();

            s += " { color: 'rgba(" + c.get(0) + "," + c.get(1) + "," + c.get(2) + "," + c.get(3) + ")', ";
            s += " name: '" + key + "', data: " + value + "} , ";
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

    private String generateChartCategories(List<Object> xAxisValues) {
        if (xAxisValues == null) {
            return "";
        } else {
            String s = new String();

            s = xAxisValues.stream().map((k) -> " ' " + k + " ', ").reduce(s, String::concat);

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

package RETAIL.Views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import db.retail.ent.FS;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vaadin.highcharts.HighChartGen;
import org.vaadin.highcharts.ChartType;
import java.util.stream.Collectors;
import static Main.MyUI.DS_RETAIL;
import static Main.MyUI.SYSTEM_DATE_FORMAT;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import org.dobrivoje.utils.colors.PastelColorGenerator;
import org.superb.apps.utilities.vaadin.Views.View_Dashboard;

public class View_R_SysNotifBoard extends View_Dashboard {

    private final DateField viewDateFrom = new DateField();
    private final DateField viewDateTo = new DateField();

    private final OS_Search ossEvent;

    public View_R_SysNotifBoard() {
        super("Retail Dashboard");
        tools.addComponents(viewDateFrom, viewDateTo);

        ossEvent = new OS_Search(new DateIntervalSearch(dateInterval.getFromStr(), dateInterval.getToStr()), null);

        viewDateFrom.setDateFormat(SYSTEM_DATE_FORMAT);
        viewDateTo.setDateFormat(SYSTEM_DATE_FORMAT);
        viewDateFrom.setValue(dateInterval.getFrom());
        viewDateTo.setValue(dateInterval.getTo());

        //<editor-fold defaultstate="collapsed" desc="test verzija">
        /*
         List reportNames = DS_RETAIL.getASC_ReportDetails_C().get(new NameIDLogicSearch(null, true, -1));
         List<String> fsNames = DS_RETAIL.getASC_FS_C().getAll(false).subList(0, 4)
         .stream().map(FS::getNaziv).collect(Collectors.toList());
         Dates dates = new Dates(-3, true);
         OS_Search criteria = new OS_Search(new DateIntervalSearch(dates.getFromStr(), dates.getToStr()), "90431");
        
         buildContentWithComponents(
         createReport_FSDailyPerformance(ChartType.AREA_SPLINE, "FS Daily Performances", criteria),
         createReport(ChartType.STACKED_BAR, "Report", reportNames, fsNames),
         createReport(ChartType.SPLINE, "Report", reportNames, fsNames),
         createReport(ChartType.BAR, "Fuel Consumption", reportNames, Arrays.asList("Premium", "EVO")),
         createReport(ChartType.AREA_SPLINE, "Report", reportNames, fsNames),
         createReport(ChartType.BASIC_COLUMN, "Report", reportNames, fsNames)
         );
         */
        /*
         Dates dates = new Dates(-3, true);
         List<Component> c = new ArrayList();
        
         for (FS f : DS_RETAIL.getASC_FS_C().getAll(false).stream().filter(p -> p.getCocaModel()).collect(Collectors.toList())) {
         OS_Search criteria = new OS_Search(new DateIntervalSearch(dates.getFromStr(), dates.getToStr()), f.getCode());
         c.add(createReport_FSDailyPerformance(ChartType.AREA_SPLINE, "FS Daily Performances", criteria));
         }
         buildContentWithComponents(c);
         */
        //</editor-fold>
        List<Component> c = new ArrayList();

        for (FS f : DS_RETAIL.getASC_FS_C().getAll(false).stream().filter(p -> p.getCocaModel()).collect(Collectors.toList())) {
            OS_Search criteria = new OS_Search(new DateIntervalSearch(dateInterval.getFromStr(), dateInterval.getToStr()), f.getCode());
            c.add(createReport_FSPeriodTotalPerformance(
                    ChartType.BASIC_COLUMN,
                    f.toString()
                    + ", " + dateInterval.getFromStr(SYSTEM_DATE_FORMAT)
                    + " - "
                    + dateInterval.getToStr(SYSTEM_DATE_FORMAT),
                    criteria)
            );
        }

        buildContentWithComponents(c);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc="createTopBar">
    public final HorizontalLayout createTopBar() {

        //<editor-fold defaultstate="collapsed" desc="DateField listeners">
        viewDateFrom.addValueChangeListener((Property.ValueChangeEvent event) -> {
            dateInterval.setFrom((Date) event.getProperty().getValue());
            ossEvent.setDateFrom(dateInterval.getFromStr());

            if (ossEvent.getFsCode() != null) {
                // refreshFSPerformancePanel(ossEvent);
            }
        });

        viewDateTo.addValueChangeListener((Property.ValueChangeEvent event) -> {
            dateInterval.setTo((Date) event.getProperty().getValue());
            ossEvent.setDateTo(dateInterval.getToStr());

            if (ossEvent.getFsCode() != null) {
                // refreshFSPerformancePanel(ossEvent);
            }
        });
        //</editor-fold>  

        //<editor-fold defaultstate="collapsed" desc="Topbar visual">
        TextField filter = new TextField();
        filter.setStyleName("filter-textfield");
        filter.setInputPrompt("search data...");
        ResetButtonForTextField.extend(filter);
        filter.setImmediate(false);
        filter.addTextChangeListener((FieldEvents.TextChangeEvent event) -> {
            // table.setFilter(event.getText());
        });

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);

        topLayout.addComponents(filter, viewDateFrom, viewDateTo);

        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setComponentAlignment(viewDateFrom, Alignment.MIDDLE_RIGHT);
        topLayout.setComponentAlignment(viewDateTo, Alignment.MIDDLE_RIGHT);

        topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");
        //</editor-fold>

        return topLayout;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="primeri...">
    //<editor-fold defaultstate="collapsed" desc="createYAxisValues - primer">
    private Map<Object, List> createYAxisValues(List xAxisValues, List categories) {
        Map<Object, List> M = new HashMap<>();

        List categoryValues;

        for (Object category : categories) {
            categoryValues = new ArrayList<>();

            for (Object xV : xAxisValues) {
                categoryValues.add((float) (10 + 17000 * Math.random()));
            }
            M.put(category, categoryValues);
        }

        return M;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createReport - za primer..">
    private Component createReport(ChartType chartType, String title, List categories, List xAxisValues) {
        Component c1;
        String panelMessage;
        Panel p;

        try {
            panelMessage = title;

            c1 = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxisValues,
                    createYAxisValues(xAxisValues, categories),
                    new PastelColorGenerator(0.8f)
            );

            p = new Panel(panelMessage, c1);

        } catch (Exception ex) {
            panelMessage = "No results for the " + title + ", for selected period !";
            p = new Panel(panelMessage);
        }

        VerticalLayout vle = new VerticalLayout(p);
        vle.setMargin(true);
        vle.setSpacing(true);
        vle.setComponentAlignment(p, Alignment.MIDDLE_CENTER);

        return vle;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="createReport_FSDailyPerformance">
    private Component createReport_FSDailyPerformance(ChartType chartType, String title, OS_Search criteria) {
        Map<ReportDetails, List> data = DS_RETAIL.getMD_FSPerformanceDetailed_C(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).getTree();

        Map<Object, List> YAxisValues = new LinkedHashMap<>();

        // Set of ReportDetails -> Set of Object :
        List repDetail0 = data.keySet().stream().collect(Collectors.toList());

        // List of days :
        List xAxisValues = ((List<Obracun_FS_PerfDetaljno>) data.get((ReportDetails) repDetail0.get(0)))
                .stream().map(Obracun_FS_PerfDetaljno::getDan).collect(Collectors.toList());

        for (Map.Entry<ReportDetails, List> E : data.entrySet()) {
            ReportDetails key = E.getKey();
            List value = ((List<Obracun_FS_PerfDetaljno>) E.getValue()).stream().map(Obracun_FS_PerfDetaljno::getOstvarenje)
                    .collect(Collectors.toList());

            YAxisValues.put(key, value);
        }

        return createReport(chartType, title, xAxisValues, YAxisValues);

    }
    //</editor-fold>
    //</editor-fold>

    private Component createReport(ChartType chartType, String title, List xAxis, Map<Object, List> yAxisByCategoryValues) {
        Component c1;
        String panelMessage;
        Panel p;

        try {
            panelMessage = title;

            c1 = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxis,
                    yAxisByCategoryValues,
                    new PastelColorGenerator(0.8f)
            );

            p = new Panel(panelMessage, c1);

        } catch (Exception ex) {
            panelMessage = "No results for the " + title + ", for selected period !";
            p = new Panel(panelMessage);
        }

        VerticalLayout vle = new VerticalLayout(p);
        vle.setMargin(true);
        vle.setSpacing(true);
        vle.setComponentAlignment(p, Alignment.MIDDLE_CENTER);

        return vle;
    }

    private Component createReport_FSPeriodTotalPerformance(ChartType chartType, String title, OS_Search criteria) {
        Map<Object, List> data = DS_RETAIL.getMD_FS_Performace_C3(criteria).getTree();

        Map<Object, List> YAxisValues = new LinkedHashMap<>();

        for (Map.Entry<Object, List> E : data.entrySet()) {
            ReportDetails key = (ReportDetails) E.getKey();
            Double value = (Double) E.getValue().get(2);

            YAxisValues.put(key, Arrays.asList(value));
        }

        return createReport(
                chartType,
                title,
                DS_RETAIL.getASC_FS_C().getAll(false)
                .stream().filter(f -> f.getCode().equals(criteria.getFsCode())).collect(Collectors.toList()),
                YAxisValues
        );

    }
}

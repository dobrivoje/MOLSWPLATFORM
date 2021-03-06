package HSE.Views;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import db.retail.ent.FS;
import db.retail.ent.ReportDetails;
import db.retail.ent.criteria.DateIntervalSearch;
import db.retail.ent.criteria.FSSearch;
import db.retail.ent.criteria.NameIDLogicSearch;
import db.retail.ent.criteria.OS_Search;
import db.retail.ent.reports.Obracun_FS_PerfDetaljno;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static Main.MyUI.DS_RETAIL;
import com.vaadin.ui.Alignment;
import org.superbapps.utils.common.colors.AppealingColorGenerator;
import org.superbapps.utils.common.colors.IColorGenerator;
import org.superbapps.utils.common.dates.Dates;
import org.superbapps.utils.vaadin.Views.View_Dashboard;
import org.vaadin.highcharts.HighChartGen;
import org.vaadin.highcharts.ChartType;

public class View_HSE_SysNotifBoard extends View_Dashboard {

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public View_HSE_SysNotifBoard() {
        super("HSE Notifications Board");

        createTopBar();

        List reportNames = DS_RETAIL.getASC_ReportDetails_C().get(new NameIDLogicSearch(null, true, -1));
        List<String> fsNames = DS_RETAIL.getASC_FS_C().getAll(false).subList(0, 4)
                .stream().map(FS::getNaziv).collect(Collectors.toList());

        Dates dates = new Dates(-2, true);
        OS_Search criteria = new OS_Search(new DateIntervalSearch(dates.getFromStr(), dates.getToStr()), "90431");

        buildContentWithComponents(
                createReport_FSDailyPerformance(ChartType.AREA_SPLINE, "FS Daily Performances", criteria),
                createReport(ChartType.STACKED_BAR, "Report", reportNames, fsNames),
                createReport(ChartType.SPLINE, "Report", reportNames, fsNames),
                createReport(ChartType.BAR, "Fuel Consumption", reportNames, Arrays.asList("Premium", "EVO")),
                createReport(ChartType.AREA, "Report", reportNames, fsNames)
        );
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc="createTopBar">
    public final HorizontalLayout createTopBar() {

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>

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

    private Map<Object, List> createYAxisValues2(Map<ReportDetails, List> MM) {
        Map<Object, List> M = new HashMap<>();

        for (Map.Entry<ReportDetails, List> E : MM.entrySet()) {
            ReportDetails category = E.getKey();
            List<Obracun_FS_PerfDetaljno> categoryValues = E.getValue();

            M.put(category, categoryValues.stream().map(Obracun_FS_PerfDetaljno::getOstvarenje).collect(Collectors.toList()));
        }

        return M;
    }

    private Component createReport(ChartType chartType, String title, List categories, List xAxisValues) {
        Component c1;
        try {
            c1 = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxisValues,
                    createYAxisValues(xAxisValues, categories)
            );
        } catch (Exception ex) {
            Panel pe = new Panel("No results for the selected period !");
            VerticalLayout vle = new VerticalLayout(pe);
            vle.setMargin(true);
            vle.setSpacing(true);
            vle.setComponentAlignment(pe, Alignment.MIDDLE_CENTER);

            return vle;
        }

        subPanels.add(new Panel(title, c1));

        VerticalLayout VL = new VerticalLayout();
        VL.setSizeUndefined();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

    private Component createReport_FSDailyPerformance(ChartType chartType, String title, OS_Search criteria) {
        Map<ReportDetails, List> data = DS_RETAIL.getMD_FSPerformanceDetailed_C(criteria.getDateFrom(), criteria.getDateTo(), criteria.getFsCode()).getTree();

        List categories = data.keySet().stream().collect(Collectors.toList());

        List xAxisValues = ((List<Obracun_FS_PerfDetaljno>) data.get((ReportDetails) categories.get(0)))
                .stream().map(Obracun_FS_PerfDetaljno::getDan).collect(Collectors.toList());

        List<IColorGenerator> colorFactory = new ArrayList<>();

        //<editor-fold defaultstate="collapsed" desc="Create series colors">
        for (int i = 0; i < 3; i++) {
            colorFactory.add(new AppealingColorGenerator());
        }
        // maingrade
        colorFactory.add((IColorGenerator) (int colorIndex) -> {
            return Arrays.asList(255, 50, 0, 0.7f);
        });
        // premium
        colorFactory.add((IColorGenerator) (int colorIndex) -> {
            return Arrays.asList(255, 12, 0, 0.7f);
        });
        for (int i = 6; i <= createYAxisValues2(data).size(); i++) {
            colorFactory.add(new AppealingColorGenerator());
        }
        //</editor-fold>

        Component c1;

        try {
            c1 = new HighChartGen().generateHighChart(
                    chartType,
                    title,
                    xAxisValues,
                    createYAxisValues2(data),
                    colorFactory
            );
        } catch (Exception ex) {
            Panel pe = new Panel("No results for the selected period !");
            VerticalLayout vle = new VerticalLayout(pe);
            vle.setMargin(true);
            vle.setSpacing(true);
            vle.setComponentAlignment(pe, Alignment.MIDDLE_CENTER);

            return vle;
        }

        subPanels.add(
                new Panel(
                        DS_RETAIL.getASC_FS_C().getByID(new FSSearch("", criteria.getFsCode())).toString()
                        .concat(" from : ")
                        .concat(criteria.getDateFrom())
                        .concat(" - ")
                        .concat(criteria.getDateTo()
                        ),
                        c1
                )
        );

        VerticalLayout VL = new VerticalLayout();
        VL.setSizeUndefined();
        VL.setMargin(true);
        VL.setSpacing(true);

        for (Component c : subPanels) {
            VL.addComponents(c);
        }

        return VL;
    }

}

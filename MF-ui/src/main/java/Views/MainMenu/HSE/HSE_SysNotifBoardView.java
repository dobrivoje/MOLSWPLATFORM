package Views.MainMenu.HSE;

import Custom.HSE.HSE_SysNotifController;
import Custom.HSE.HSE_SysNotif_Bean;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import org.dussan.vaadin.dcharts.DCharts;
import org.dussan.vaadin.dcharts.base.elements.XYaxis;
import org.dussan.vaadin.dcharts.data.DataSeries;
import org.dussan.vaadin.dcharts.data.Ticks;
import org.dussan.vaadin.dcharts.metadata.renderers.AxisRenderers;
import org.dussan.vaadin.dcharts.metadata.renderers.SeriesRenderers;
import org.dussan.vaadin.dcharts.options.Axes;
import org.dussan.vaadin.dcharts.options.Highlighter;
import org.dussan.vaadin.dcharts.options.Legend;
import org.dussan.vaadin.dcharts.options.Options;
import org.dussan.vaadin.dcharts.options.SeriesDefaults;
import org.dussan.vaadin.dcharts.renderers.series.PieRenderer;

public class HSE_SysNotifBoardView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "HSE System Notifications";

    private final VerticalLayout VL = new VerticalLayout();
    // private final GridLayout GL = new GridLayout();

    private final DataSeries dataSeries = new DataSeries();

    private final Panel p = new Panel();

    // private final HSE_SysNotifTable hseSysNotifTable = new HSE_SysNotifTable();
    public HSE_SysNotifBoardView() {
        //<editor-fold defaultstate="collapsed" desc="UI setup">
        setSizeFull();
        addStyleName("crud-view");

        //GL.setRows(3);
        //GL.setColumns(3);
        VL.setSizeFull();
        VL.setMargin(true);

        HorizontalLayout topLayout = createTopBar();

        // hseSysNotifTable.setSizeFull();
        // p.setWidth(500, Unit.PERCENTAGE);
        // p.setHeight(500, Unit.PIXELS);
        // p.setCaption("Workplans Report");
        //p.setContent(hseSysNotifTable);
        //GL.addComponent(p, 0, 0);
        //GL.addComponent(generateDonut(), 1, 1);
        VL.addComponent(topLayout);
        //VL.addComponent(GL);
        VL.addComponent(generatePie());
        VL.addComponent(generateLine());
        VL.setSizeFull();
        //VL.setExpandRatio(GL, 1);
        VL.setStyleName("crud-main-layout");
        addComponent(VL);
        //</editor-fold>

        addComponent(VL);
    }
    //<editor-fold defaultstate="collapsed" desc="Customer Table - Double click - Customer Form">

    @Override
    public void enter(ViewChangeEvent event) {
    }

    //<editor-fold defaultstate="collapsed" desc="createTopBar">
    public final HorizontalLayout createTopBar() {

        HorizontalLayout topLayout = new HorizontalLayout();
        topLayout.setSpacing(true);
        topLayout.setWidth(100, Unit.PERCENTAGE);
        // topLayout.addComponents(filter, newBtn);
        // topLayout.setComponentAlignment(filter, Alignment.MIDDLE_LEFT);
        // topLayout.setExpandRatio(filter, 1);
        topLayout.setStyleName("top-bar");

        return topLayout;
    }
    //</editor-fold>

    private DCharts generatePie() {

        for (HSE_SysNotif_Bean r1 : new HSE_SysNotifController().getSysNotifBoard_Report1()) {
            if (r1.getTotal() > 3) {
                dataSeries.newSeries().add(r1.getFs().getName(), r1.getTotal());
            }
        }

        SeriesDefaults seriesDefaults = new SeriesDefaults()
                .setRenderer(SeriesRenderers.PIE)
                .setRendererOptions(
                        new PieRenderer()
                        .setShowDataLabels(true));

        Legend legend = new Legend()
                .setShow(true);

        Highlighter highlighter = new Highlighter()
                .setShow(true)
                .setShowTooltip(true)
                .setTooltipAlwaysVisible(true)
                .setKeepTooltipInsideChart(true);

        Options options = new Options()
                .setSeriesDefaults(seriesDefaults)
                .setLegend(legend)
                .setHighlighter(highlighter);

        return new DCharts()
                .setDataSeries(dataSeries)
                .setOptions(options)
                .show();
    }

    private DCharts generateLine() {
        DataSeries ds2 = new DataSeries();
        Axes axes = new Axes();
        SeriesDefaults seriesDefaults = new SeriesDefaults().setRenderer(SeriesRenderers.BAR);

        for (HSE_SysNotif_Bean r1 : new HSE_SysNotifController().getSysNotifBoard_Report1()) {
            ds2.add(r1.getTotal());

            axes.addAxis(new XYaxis()
                    .setRenderer(AxisRenderers.CATEGORY)
                    .setTicks(new Ticks().add(r1.getFs().getIdfs())));
        }

        Highlighter highlighter = new Highlighter()
                .setShow(false);

        Options options = new Options()
                .setSeriesDefaults(seriesDefaults)
                .setAxes(axes)
                .setHighlighter(highlighter);

        return new DCharts()
                .setDataSeries(ds2)
                .setOptions(options)
                .show();
    }
}

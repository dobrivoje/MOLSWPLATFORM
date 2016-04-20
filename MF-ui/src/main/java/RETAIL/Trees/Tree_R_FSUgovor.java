/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Trees;

import db.retail.ent.FS;
import static Main.MyUI.DS_RETAIL;
import RETAIL.Forms.Form_R_FS;
import RETAIL.Forms.Form_R_UGOVOR;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Sizeable.Unit;
import db.retail.ent.Ugovor;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.superbapps.utils.vaadin.Exceptions.CustomTreeNodesEmptyException;
import org.superbapps.utils.vaadin.MyWindows.WindowForm3;
import org.superbapps.utils.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSUgovor extends CustomObjectTree<FS> {

    public Tree_R_FSUgovor(FS fs, boolean expandRootNodes) throws CustomTreeNodesEmptyException {
        this(fs, expandRootNodes, true);
    }

    public Tree_R_FSUgovor(FS fs, boolean expandRootNodes, boolean readOnly) throws CustomTreeNodesEmptyException {
        super(Arrays.asList(fs), expandRootNodes);

        //<editor-fold defaultstate="collapsed" desc="addItemClickListener">
        addItemClickListener((ItemClickEvent event) -> {
            if (event.isDoubleClick()) {
                WindowForm3 wf = null;

                if (event.getItemId() instanceof FS) {
                    crudForm = new Form_R_FS(new BeanItem(event.getItemId()), false, null, readOnly);

                    winFormImgHeight = 200;
                    winFormImgWidth = 235;

                    wf = new WindowForm3(
                            "Fuelstation Data Form",
                            crudForm,
                            432, 676, Unit.PIXELS,
                            "img/partner1.png",
                            "Save",
                            crudForm.getClickListener(),
                            winFormImgHeight, winFormImgWidth, readOnly
                    );

                }

                if (event.getItemId() instanceof Ugovor) {
                    crudForm = new Form_R_UGOVOR(new BeanItem(event.getItemId()), false, null, readOnly);

                    winFormImgHeight = 225;
                    winFormImgWidth = 225;

                    wf = new WindowForm3(
                            "Contracts Data Form",
                            crudForm,
                            495, 700, Unit.PIXELS,
                            "img/contract.png", "Save",
                            crudForm.getClickListener(), winFormImgHeight, winFormImgWidth,
                            readOnly
                    );
                }

                try {
                    getUI().addWindow(wf);
                } catch (IllegalArgumentException | NullPointerException e) {
                }
            }
        }
        );
        //</editor-fold>
    }

    public Tree_R_FSUgovor(boolean expandRootNodes, boolean justCoca) throws CustomTreeNodesEmptyException {
        super("",
                DS_RETAIL.getASC_FS_C().getAll(false).stream().filter(p -> p.getCocaModel().equals(justCoca)).collect(Collectors.toList()),
                expandRootNodes
        );
    }

    @Override
    protected void createSubNodes(FS fs) {
        createChildNodesForTheRoot(fs, DS_RETAIL.getMD_Ugovor_C().getDetails(fs), expandRootNodes);
    }

}

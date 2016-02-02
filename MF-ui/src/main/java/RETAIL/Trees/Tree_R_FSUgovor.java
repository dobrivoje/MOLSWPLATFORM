/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.FS;
import java.util.Arrays;
import static Main.MyUI.DS_RETAIL;
import RETAIL.Forms.Form_R_FS;
import RETAIL.Forms.Form_R_UGOVOR;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import db.retail.ent.Ugovor;
import java.util.stream.Collectors;
import org.superb.apps.utilities.vaadin.MyWindows.WindowForm3;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_FSUgovor extends CustomObjectTree<FS> {

    private String imageLocation;
    private int imageX;
    private int imageY;

    public Tree_R_FSUgovor(FS fs, boolean expandRootNodes) throws CustomTreeNodesEmptyException {
        super(Arrays.asList(fs), expandRootNodes);

        //<editor-fold defaultstate="collapsed" desc="addItemClickListener">
        addItemClickListener((ItemClickEvent event) -> {
            if (event.isDoubleClick()) {
                imageX = 250;

                if (event.getItemId() instanceof FS) {
                    crudForm = new Form_R_FS(new BeanItem(event.getItemId()), false, null);

                    winFormCaption = "Fuelstation Data Form";
                    imageLocation = "img/cbt.png";

                    imageY = 225;
                } else if (event.getItemId() instanceof Ugovor) {
                    crudForm = new Form_R_UGOVOR(new BeanItem(event.getItemId()), false, null);

                    winFormCaption = "Contracts Data Form";
                    imageLocation = "img/contract.png";

                    imageY = 250;
                }

                if (crudForm != null) {
                    getUI().addWindow(
                            new WindowForm3(
                                    winFormCaption,
                                    crudForm,
                                    imageLocation,
                                    "Save",
                                    crudForm.getClickListener(),
                                    imageX, imageY, false
                            )
                    );
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

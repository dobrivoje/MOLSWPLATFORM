package RETAIL.Trees;

import db.Exceptions.CustomTreeNodesEmptyException;
import db.retail.ent.Partner;
import java.util.Arrays;
import static Main.MyUI.DS_RETAIL;
import RETAIL.Forms.Form_R_PARTNER;
import RETAIL.Forms.Form_R_UGOVOR;
import com.vaadin.data.util.BeanItem;
import com.vaadin.event.ItemClickEvent;
import db.retail.ent.Ugovor;
import org.superb.apps.utilities.vaadin.MyWindows.WindowForm3;
import org.superb.apps.utilities.vaadin.Trees.CustomObjectTree;

/**
 *
 * @author Dobri
 */
public class Tree_R_PartnerUgovor extends CustomObjectTree<Partner> {

    public Tree_R_PartnerUgovor(Partner rootItem) throws CustomTreeNodesEmptyException {
        this(rootItem, false);
    }

    public Tree_R_PartnerUgovor(Partner rootItem, boolean expandRootNodes) throws CustomTreeNodesEmptyException {
        this(rootItem, expandRootNodes, false);
    }

    public Tree_R_PartnerUgovor(Partner rootItem, boolean expandRootNodes, boolean readOnly) throws CustomTreeNodesEmptyException {
        super("", Arrays.asList(rootItem), expandRootNodes);

        //<editor-fold defaultstate="collapsed" desc="addItemClickListener">
        addItemClickListener((ItemClickEvent event) -> {
            if (event.isDoubleClick()) {

                if (event.getItemId() instanceof Partner) {
                    crudForm = new Form_R_PARTNER(new BeanItem(event.getItemId()), false, null);

                    winFormCaption = "Partner Data Form";
                    winFormImagePath = "img/partner3.png";

                    winFormImgWidth = 250;
                    winFormImgHeight = 300;
                } else if (event.getItemId() instanceof Ugovor) {
                    crudForm = new Form_R_UGOVOR(new BeanItem(event.getItemId()), false, null);

                    winFormCaption = "Contract Data Form";
                    winFormImagePath = "img/contract.png";

                    winFormImgWidth = 250;
                    winFormImgHeight = 250;
                }

                if (crudForm != null) {
                    getUI().addWindow(
                            new WindowForm3(
                                    winFormCaption,
                                    crudForm,
                                    winFormImagePath,
                                    "Save",
                                    crudForm.getClickListener(),
                                    winFormImgWidth, winFormImgHeight, readOnly
                            )
                    );
                }
            }
        }
        );
        //</editor-fold>
    }

    @Override
    protected void createSubNodes(Partner rootItem) {
        createChildNodesForTheRoot(rootItem, DS_RETAIL.getMD_Partner_C().getDetails(rootItem), expandRootNodes);
    }

}

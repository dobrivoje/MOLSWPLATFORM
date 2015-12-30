package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Partner;
import db.retail.interfaces.ICRUDController;
import db.retail.interfaces.IMasterDetail;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class MD_Partner_Controller implements IMasterDetail<Partner>, ICRUDController<Partner> {

    private static DBHandler_RETAIL dbh;

    public MD_Partner_Controller(DBHandler_RETAIL dbh) {
        MD_Partner_Controller.dbh = dbh;
    }

    @Override
    public List getDetails(Partner master) {
        return dbh.get_Partner_Ugovori(master);
    }

    @Override
    public List<Partner> getAllDetails() {
        return dbh.getAll_Partner(true);
    }

    @Override
    public void add(Partner newPartner) throws Exception {
        dbh.addNew_Partner(newPartner);
    }

    @Override
    public void update(Partner partner) throws Exception {
        dbh.updatePartner(partner);
    }

}

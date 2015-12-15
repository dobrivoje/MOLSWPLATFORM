package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.Partner;
import db.retail.ent.criteria.PUSearch;
import db.retail.interfaces.IMasterDetail;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author DPrtenjak
 */
public class MD_Partner_Controller implements IMasterDetail<Partner, PUSearch> {

    private static DBHandler_RETAIL dbh;

    public MD_Partner_Controller(DBHandler_RETAIL dbh) {
        MD_Partner_Controller.dbh = dbh;
    }

    @Override
    public Set getDetails(Partner master) {
        return dbh.getDetails_Ugovor_Partner(master);
    }

    @Override
    public Map<Partner, Set> getRootNodes(PUSearch criteria) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

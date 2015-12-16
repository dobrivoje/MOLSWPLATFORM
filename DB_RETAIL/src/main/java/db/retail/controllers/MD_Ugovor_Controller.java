package db.retail.controllers;

import db.retail.DBHandler_RETAIL;
import db.retail.ent.FS;
import db.retail.ent.Ugovor;
import db.retail.ent.criteria.PUSearch;
import db.retail.interfaces.ICRUDController;
import db.retail.interfaces.IMasterDetail;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dobri
 */
public class MD_Ugovor_Controller implements IMasterDetail<FS, PUSearch>, ICRUDController<Ugovor> {

    private static DBHandler_RETAIL dbh;

    public MD_Ugovor_Controller(DBHandler_RETAIL dbh) {
        MD_Ugovor_Controller.dbh = dbh;
    }

    @Override
    public List getDetails(FS master) {
        return dbh.get_FS_Ugovori(master);
    }

    @Override
    public List<FS> getAllDetails() {
        List<FS> fss = new ArrayList(dbh.getAll_FS());

        for (FS f : fss) {
            f.setUgovorList(dbh.get_FS_Ugovori(f));
        }

        return fss;
    }

    @Override
    public void add(Ugovor newItem) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
        // dbh.addNew_(newItem);
    }

    @Override
    public void update(Ugovor newItem) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
        //dbh.updatePartner(partner);
    }

}

package db.retail;

import db.retail.ent.FS;
import db.retail.ent.Mapping;
import db.retail.ent.reports.ObracunFinal;
import db.retail.ent.reports.Specifikacija;
import db.retail.ent.CompositeSellReport;
import db.retail.ent.GrupniNaziv;
import db.retail.ent.Kategorija;
import db.retail.ent.Partner;
import db.retail.ent.ReportDetails;
import db.retail.ent.Ugovor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DBHandler_RETAIL extends DBHandler {

    //<editor-fold defaultstate="collapsed" desc="System definitions">
    private static DBHandler_RETAIL instance;

    private DBHandler_RETAIL() {
        super("retail_DB.PU");
    }

    public static DBHandler_RETAIL getDefault() {
        return instance == null ? instance = new DBHandler_RETAIL() : instance;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="FS">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<FS> getAll_FS() {
        try {
            return (List<FS>) getEm().createNamedQuery("FS.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public FS getByID_FS(String FSID) {
        try {
            return (FS) getEm().createNamedQuery("FS.findByCode")
                    .setParameter("code", FSID)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    public void addNew_FS(FS newFS) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.persist(newFS);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("New FS Addition Failed");
        }
    }

    public void addNew_FS(String naziv, String code, String model) throws Exception {
        FS newFS = new FS();

        newFS.setNaziv(naziv);
        newFS.setCode(code);
        newFS.setModel(model);

        addNew_FS(newFS);
    }

    public void updateFS(FS fs) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(fs);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("FS Update Failed");
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="COMPOSITE SELL REPORT">
    //<editor-fold defaultstate="collapsed" desc="Read data">
    public List<CompositeSellReport> getAll_CSR() {
        try {
            return (List<CompositeSellReport>) getEm()
                    .createNamedQuery("CompositeSellReport.findAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<CompositeSellReport> getBy_FSID(FS IDFs) {
        try {
            return (List<CompositeSellReport>) getEm().createNamedQuery("CompositeSellReport.findByIDFS")
                    .setParameter("IDFS", IDFs)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<CompositeSellReport> getBy_FSID(String FSCode) {
        try {
            return (List<CompositeSellReport>) getEm().createNamedQuery("CompositeSellReport.findByFSCode")
                    .setParameter("FSCode", FSCode)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //public List<CompositeSellReport> getBy_Criteria(String FSCode, Date datumOD, Date datumDO)
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MAPPING">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<Mapping> getAll_Mapping() {
        try {
            return (List<Mapping>) getEm().createNamedQuery("Mapping.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Mapping> getByID_Mapping(String mappingCode) {
        try {
            return getEm().createNamedQuery("Mapping.findByCode")
                    .setParameter("code", mappingCode)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    public void addNew_Mapping(Mapping newMapping) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.persist(newMapping);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("New FS Addition Failed");
        }
    }

    public void addNew_Mapping(String naziv, String code, boolean aktivan, boolean report,
            Date datumUnosa, GrupniNaziv grupniNaziv, Kategorija kategorija,
            ReportDetails reportDetails) throws Exception {

        addNew_Mapping(new Mapping(naziv, code, aktivan, report, datumUnosa,
                grupniNaziv, kategorija, null, reportDetails, null));
    }

    public void update_Mapping(Mapping mapping) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(mapping);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("Mapping Update Failed");
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GRUPNI NAZIV">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<GrupniNaziv> getAll_GN() {
        try {
            return (List<GrupniNaziv>) getEm()
                    .createNamedQuery("GrupniNaziv.findAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<GrupniNaziv> getByName_GN(String GNaziv) {
        try {
            return getEm().createNamedQuery("GrupniNaziv.findByGNaziv")
                    .setParameter("gNaziv", GNaziv)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="KATEGORIJA">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<Kategorija> getAll_KATEG() {
        try {
            return (List<Kategorija>) getEm()
                    .createNamedQuery("Kategorija.findAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Kategorija> getByName_KATEG(String naziv) {
        try {
            return getEm().createNamedQuery("Kategorija.findByNaziv")
                    .setParameter("naziv", naziv)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PARTNER - UGOVOR">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<Partner> getAll_Partner() {
        try {
            return (List<Partner>) getEm().createNamedQuery("Partner.findAll").getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Ugovor> get_FS_Ugovori(FS fs) {
        try {
            return getEm().createNamedQuery("Ugovor.findByFS")
                    .setParameter("IDFS", fs)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Ugovor> get_Partner_Ugovori(Partner partner) {
        try {
            return getEm().createNamedQuery("Ugovor.findByIDP")
                    .setParameter("IDP", partner)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public Partner getMD_Partner_Ugovori(Partner partner) {
        Set<Ugovor> ugovori = new LinkedHashSet<>();

        try {
            ugovori.addAll(getEm().createNamedQuery("Ugovor.findByIDP")
                    .setParameter("IDP", partner)
                    .getResultList());

            partner.setUgovorList(new ArrayList<>(ugovori));

            return partner;
        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    public void addNew_Partner(Partner newPartner) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.persist(newPartner);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("New Partner Addition Failed");
        }
    }

    public void addNew_FS(String naziv, String kompanija) throws Exception {
        addNew_Partner(new Partner(naziv, kompanija));
    }

    public void updatePartner(Partner partner) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(partner);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction("Partner Update Failed");
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="SQL Server functions calls">
    public synchronized List<ObracunFinal> get_ObracunFinal(String DatumOD, String DatumDO, String FSCode) {
        ObracunFinal of;
        List<ObracunFinal> lista = new ArrayList<>();

        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Connection c = em.unwrap(java.sql.Connection.class);

            CallableStatement cs = c.prepareCall("{call [COCA].[dbo].[sp_OBRACUN_FINAL] (?1, ?2, ?3)} ");
            cs.setString(1, DatumOD);
            cs.setString(2, DatumDO);
            cs.setString(3, FSCode);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                of = new ObracunFinal(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getDouble(7),
                        rs.getDouble(8),
                        rs.getDouble(9),
                        rs.getBoolean(10),
                        rs.getString(11),
                        rs.getDouble(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        rs.getString(21),
                        rs.getString(22),
                        rs.getDouble(23)
                );

                lista.add(of);

                of = null;
            }

            em.getTransaction().commit();

            return lista;

        } catch (SQLException e1) {
            em.getTransaction().rollback();
            return null;
        } catch (Exception e2) {
            em.getTransaction().rollback();
            return null;
        } finally {
            try {
                getEm().flush();
                getEm().close();
            } catch (Exception ex) {
            }
        }
    }

    public synchronized List<Specifikacija> get_Specifikacija(String DatumOD, String DatumDO, String FSCode) {
        Specifikacija of;
        List<Specifikacija> lista = new ArrayList<>();

        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }

            Connection c = em.unwrap(java.sql.Connection.class);

            CallableStatement cs = c.prepareCall("{call [COCA].[dbo].[sp_SPECIFIKACIJA] (?1, ?2, ?3)} ");
            cs.setString(1, DatumOD);
            cs.setString(2, DatumDO);
            cs.setString(3, FSCode);

            ResultSet rs = cs.executeQuery();

            while (rs.next()) {
                of = new Specifikacija(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDouble(6),
                        rs.getString(7),
                        rs.getDouble(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13)
                );

                lista.add(of);

                of = null;
            }

            em.getTransaction().commit();

            return lista;

        } catch (SQLException e1) {
            em.getTransaction().rollback();
            return null;
        } catch (Exception e2) {
            em.getTransaction().rollback();
            return null;
        } finally {
            try {
                getEm().flush();
                getEm().close();
            } catch (Exception ex) {
            }
        }
    }
    //</editor-fold>

}

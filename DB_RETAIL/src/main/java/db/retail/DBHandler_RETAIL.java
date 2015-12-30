package db.retail;

import db.retail.ent.FS;
import db.retail.ent.Mapping;
import db.retail.ent.reports.ObracunFinal;
import db.retail.ent.reports.Specifikacija;
import db.retail.ent.CompositeSellReport;
import db.retail.ent.Document;
import db.retail.ent.DocumentType;
import db.retail.ent.Gallery;
import db.retail.ent.GrupniNaziv;
import db.retail.ent.Kategorija;
import db.retail.ent.Koef;
import db.retail.ent.Partner;
import db.retail.ent.ReportDetails;
import db.retail.ent.Ugovor;
import db.retail.ent.reports.KeyDist;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public List<FS> getAll_FS(boolean initSubList) {
        List<FS> L;
        try {
            L = getEm().createNamedQuery("FS.findAll").getResultList();

            if (initSubList) {

                L.stream().forEach((f) -> {
                    f.setUgovorList(get_FS_Ugovori(f));
                });
            }

            return L;
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
            rollBackTransaction(ADD_FAILED);
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
            rollBackTransaction(UPDATE_FAILED);
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
            rollBackTransaction(ADD_FAILED);
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
            rollBackTransaction(UPDATE_FAILED);
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GRUPNI NAZIV, KOEF, KLJUČ RASPODELE">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<GrupniNaziv> getAll_GRUPNINAZIV() {
        try {
            return (List<GrupniNaziv>) getEm()
                    .createNamedQuery("GrupniNaziv.findAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<GrupniNaziv> getByName_GRUPNINAZIV(String GNaziv) {
        try {
            return getEm().createNamedQuery("GrupniNaziv.findByGNaziv")
                    .setParameter("naziv", "%" + GNaziv + "%")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Koef> getALL_GRUPAKOEF() {
        try {
            return getEm().createNamedQuery("GrupaKoef.findAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Koef> getALL_KOEF() {
        try {
            return getEm().createNamedQuery("Koef.findAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<Koef> getALL_GRUPAKOEF(Koef k) {
        try {
            return getEm().createNamedQuery("Koef.findByIdkfs")
                    .setParameter("idkfs", k)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<KeyDist> getALL_KEYDIST() {
        try {
            return getEm().createNamedQuery("KljucRaspodele.AdaptSelectAll")
                    .getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<KeyDist> getByID_KEYDIST(FS fs) {
        try {
            return getEm().createNamedQuery("KljucRaspodele.AdaptSelectByFS")
                    .setParameter("IDFS", fs)
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
    public List<Partner> getAll_Partner(boolean initSubList) {
        List<Partner> L;

        try {
            L = getEm().createNamedQuery("Partner.findAll").getResultList();

            if (initSubList) {
                L.stream().forEach((p) -> {
                    p.setUgovorList(get_Partner_Ugovori(p));
                });
            }

            return L;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<Ugovor> get_FS_Ugovori(FS fs) {
        List<Ugovor> L;

        try {
            L = getEm().createNamedQuery("Ugovor.findByFS")
                    .setParameter("IDFS", fs)
                    .getResultList();

            fs.setUgovorList(L);

            return L;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<Ugovor> get_Partner_Ugovori(Partner partner) {
        List<Ugovor> L;

        try {
            L = getEm().createNamedQuery("Ugovor.findByIDP")
                    .setParameter("IDP", partner)
                    .getResultList();

            partner.setUgovorList(L);

            return L;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<Ugovor> getAll_UGOVOR() {
        try {
            return getEm().createNamedQuery("Ugovor.findAll")
                    .getResultList();
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
            rollBackTransaction(ADD_FAILED);
        }
    }

    public void addNew_FS(String naziv, String kompanija) throws Exception {
        addNew_Partner(new Partner(naziv, kompanija));
    }

    public void addNew_Ugovor(String brojUgovora, Date datumPotpisivanja, Date datumPreuzimanja, Double fiksniIznos, String bu1, String bu2, String bu3, FS fkIdfs, Partner fkIdp) throws Exception {
        addNew_Ugovor(
                new Ugovor(brojUgovora, datumPotpisivanja, datumPreuzimanja, fiksniIznos, bu1, bu2, bu3, fkIdfs, fkIdp)
        );
    }

    public void addNew_Ugovor(Ugovor item) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(item);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction(ADD_FAILED);
        }
    }

    public void updateUgovor(Ugovor item) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(item);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction(UPDATE_FAILED);
        }
    }

    public void updatePartner(Partner partner) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.merge(partner);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction(UPDATE_FAILED);
        }
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GALLERY - DOCUMENTS">
    //<editor-fold defaultstate="collapsed" desc="Read Data">
    public List<Document> getByID_Gallery(Gallery g) {
        try {
            List docs = (List<Document>) getEm()
                    .createNamedQuery("Document.findByGallery")
                    .setParameter("IDG", g)
                    .getResultList();

            g.setDocumentList(docs);

            return docs;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<Gallery> getAll_Gallery() {
        List<Gallery> G;

        try {
            G = (List<Gallery>) getEm().createNamedQuery("Gallery.findAll").getResultList();

            G.stream().forEach((g) -> {
                g.setDocumentList(getByID_Gallery(g));
            });

            return G;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<Document> getByID_DocType(DocumentType dt) {

        try {
            List docs = (List<Document>) getEm()
                    .createNamedQuery("Document.findByDocType")
                    .setParameter("IDDT", dt)
                    .getResultList();

            dt.setDocumentList(docs);

            return docs;

        } catch (Exception ex) {
            return null;
        }
    }

    public List<DocumentType> getAll_DocumentType() {
        List<DocumentType> DT;
        try {
            DT = (List<DocumentType>) getEm().createNamedQuery("DocumentType.findAll").getResultList();

            DT.stream().forEach((dt) -> {
                dt.setDocumentList(getByID_DocType(dt));
            });

            return DT;

        } catch (Exception ex) {
            return null;
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Add/update data">
    public void addNew_Document(Document newItem) throws Exception {
        try {
            getEm().getTransaction().begin();
            em.persist(newItem);
            getEm().getTransaction().commit();
        } catch (Exception ex) {
            rollBackTransaction(ADD_FAILED);
        }
    }

    public void addNew_Document(String name, Serializable docData, String path, Date uploadDate, DocumentType docType, Gallery gallery) throws Exception {
        addNew_Document(new Document(name, null, path, uploadDate, docType, gallery));
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
                        rs.getInt(1), // Integer idfs;
                        rs.getString(2), // String fsName;
                        rs.getString(3), // String fsCode;
                        rs.getString(4), // String reportName;
                        rs.getString(5), // String volType;
                        rs.getDouble(6), // Double prodato;
                        rs.getDouble(7), // Double rKoef;
                        rs.getDouble(8), // Double plan;
                        rs.getDouble(9), // Double ostvarenje;
                        // rs.getDouble(10),   // Double ostvarenje1;
                        rs.getBoolean(10), // Boolean obavezan;
                        rs.getString(11), // String koefNaziv;
                        rs.getDouble(12), // Double total;
                        rs.getInt(13), // Integer idrd;
                        rs.getInt(14), // Integer rbrReport;
                        rs.getInt(15), // Integer rbrKoef;
                        rs.getString(16), // String startObracuna;
                        rs.getString(17), // String krajObracuna;
                        rs.getString(18), // String partner;
                        rs.getString(19), // String brUgovora;
                        rs.getString(20), // String bu1;
                        rs.getString(21), // String bu2;
                        rs.getString(22), // String bu3;
                        rs.getDouble(23) // Double fiksniIznos;
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

    public Map<String, List<String>> get_FS_Performance_Detailed(String DatumOD, String DatumDO, String FSCode) {

        Map<String, List<String>> M = new LinkedHashMap<>();

        for (ObracunFinal o : get_ObracunFinal(DatumOD, DatumDO, FSCode)) {
            if (!M.containsKey(o.getReportName())) {
                M.put(
                        o.getReportName(),
                        new LinkedList<>(Arrays.asList(o.getKoefNaziv() + ", " + new DecimalFormat("0.000").format(o.getOstvarenje())))
                );
            } else {
                M.get(o.getReportName())
                        .add(
                                o.getKoefNaziv()
                                + ", " + new DecimalFormat("0.000").format(o.getOstvarenje())
                        );
            }
        }

        return M;

    }

    public Map<String, String> get_FS_Performance(String DatumOD, String DatumDO, String FSCode) {

        Map<String, String> M = new LinkedHashMap<>();

        for (ObracunFinal o : get_ObracunFinal(DatumOD, DatumDO, FSCode)) {
            String s = (new DecimalFormat("###,###").format(o.getProdato()))
                    .concat("/")
                    .concat(new DecimalFormat("###,###").format(o.getPlan()))
                    .concat(" -> ")
                    .concat(new DecimalFormat("0.0").format(100 * o.getProdato() / o.getPlan()).concat("%"));

            if (!M.containsKey(o.getReportName())) {
                M.put(o.getReportName(), s);
            } else {
                M.replace(o.getReportName(), s);
            }
        }

        return M;

    }

    //</editor-fold>
}

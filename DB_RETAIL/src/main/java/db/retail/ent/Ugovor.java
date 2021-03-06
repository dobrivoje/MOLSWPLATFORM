/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.retail.ent;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dobri
 */
@Entity
@Table(name = "UGOVOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ugovor.findAll", query = "SELECT u FROM Ugovor u"),
    @NamedQuery(name = "Ugovor.findByIdu", query = "SELECT u FROM Ugovor u WHERE u.idu = :idu"),
    @NamedQuery(name = "Ugovor.findByBrojUgovora", query = "SELECT u FROM Ugovor u WHERE u.brojUgovora = :brojUgovora"),
    @NamedQuery(name = "Ugovor.findByDatumPotpisivanja", query = "SELECT u FROM Ugovor u WHERE u.datumPotpisivanja = :datumPotpisivanja"),
    @NamedQuery(name = "Ugovor.findByDatumPreuzimanja", query = "SELECT u FROM Ugovor u WHERE u.datumPreuzimanja = :datumPreuzimanja"),

    @NamedQuery(name = "Ugovor.findByIDP",
            query = "SELECT u FROM Ugovor u WHERE u.fkIdp = :IDP"),

    @NamedQuery(name = "Ugovor.findByFS",
            query = "SELECT u FROM Ugovor u WHERE u.fkIdfs = :IDFS"),

    @NamedQuery(name = "Ugovor.findByFiksniIznos", query = "SELECT u FROM Ugovor u WHERE u.fiksniIznos = :fiksniIznos"),
    @NamedQuery(name = "Ugovor.findByBu1", query = "SELECT u FROM Ugovor u WHERE u.bu1 = :bu1"),
    @NamedQuery(name = "Ugovor.findByBu2", query = "SELECT u FROM Ugovor u WHERE u.bu2 = :bu2"),
    @NamedQuery(name = "Ugovor.findByBu3", query = "SELECT u FROM Ugovor u WHERE u.bu3 = :bu3")})
public class Ugovor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDU")
    private Integer idu;
    @Column(name = "BrojUgovora")
    private String brojUgovora;
    @Column(name = "DatumPotpisivanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPotpisivanja;
    @Column(name = "DatumPreuzimanja")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumPreuzimanja;
    @Column(name = "DatumPrekidaUgovora")
    @Temporal(TemporalType.DATE)
    private Date datumPrekidaUgovora;
    @Column(name = "UgovorAktivan")
    private boolean ugovorAktivan;
    @Column(name = "Fiksni_Iznos")
    private Double fiksniIznos;
    @Column(name = "BU1")
    private String bu1;
    @Column(name = "BU2")
    private String bu2;
    @Column(name = "BU3")
    private String bu3;
    @JoinColumn(name = "FK_IDFS", referencedColumnName = "IDFS")
    @ManyToOne
    private FS fkIdfs;
    @JoinColumn(name = "FK_IDP", referencedColumnName = "IDP")
    @ManyToOne
    private Partner fkIdp;

    public Ugovor() {
    }

    public Ugovor(String brojUgovora, Date datumPotpisivanja, Date datumPreuzimanja, Double fiksniIznos, String bu1, String bu2, String bu3, FS fkIdfs, Partner fkIdp) {
        this.brojUgovora = brojUgovora;
        this.datumPotpisivanja = datumPotpisivanja;
        this.datumPreuzimanja = datumPreuzimanja;
        this.fiksniIznos = fiksniIznos;
        this.bu1 = bu1;
        this.bu2 = bu2;
        this.bu3 = bu3;
        this.fkIdfs = fkIdfs;
        this.fkIdp = fkIdp;
    }

    public Ugovor(String brojUgovora, Date datumPotpisivanja, Date datumPreuzimanja, Date datumPrekidaUgovora, boolean ugovorAktivan, Double fiksniIznos, String bu1, String bu2, String bu3, FS fkIdfs, Partner fkIdp) {
        this(brojUgovora, datumPotpisivanja, datumPreuzimanja, fiksniIznos, bu1, bu2, bu3, fkIdfs, fkIdp);

        this.datumPrekidaUgovora = datumPrekidaUgovora;
        this.ugovorAktivan = ugovorAktivan;
    }

    public Ugovor(Integer idu) {
        this.idu = idu;
    }

    public Integer getIdu() {
        return idu;
    }

    public void setIdu(Integer idu) {
        this.idu = idu;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
    }

    public Date getDatumPotpisivanja() {
        return datumPotpisivanja;
    }

    public String getDatumPotpisivanja1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(datumPotpisivanja);
        } catch (Exception e) {
            return "";
        }
    }

    public void setDatumPotpisivanja(Date datumPotpisivanja) {
        this.datumPotpisivanja = datumPotpisivanja;
    }

    public Date getDatumPreuzimanja() {
        return datumPreuzimanja;
    }

    public String getDatumPreuzimanja1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(datumPreuzimanja);
        } catch (Exception e) {
            return "";
        }
    }

    public void setDatumPreuzimanja(Date datumPreuzimanja) {
        this.datumPreuzimanja = datumPreuzimanja;
    }

    public Date getDatumPrekidaUgovora() {
        return datumPrekidaUgovora;
    }

    public String getDatumPrekidaUgovora1() {
        try {
            return new SimpleDateFormat("dd.MM.yyyy").format(datumPrekidaUgovora);
        } catch (Exception e) {
            return "";
        }
    }

    public void setDatumPrekidaUgovora(Date datumPrekidaUgovora) {
        this.datumPrekidaUgovora = datumPrekidaUgovora;
    }

    public boolean isUgovorAktivan() {
        return ugovorAktivan;
    }

    public void setUgovorAktivan(boolean ugovorAktivan) {
        this.ugovorAktivan = ugovorAktivan;
    }

    public Partner getFkIdp() {
        return fkIdp;
    }

    public void setFkIdp(Partner fkIdp) {
        this.fkIdp = fkIdp;
    }

    public Double getFiksniIznos() {
        return fiksniIznos;
    }

    public void setFiksniIznos(Double fiksniIznos) {
        this.fiksniIznos = fiksniIznos;
    }

    public String getBu1() {
        return bu1;
    }

    public void setBu1(String bu1) {
        this.bu1 = bu1;
    }

    public String getBu2() {
        return bu2;
    }

    public void setBu2(String bu2) {
        this.bu2 = bu2;
    }

    public String getBu3() {
        return bu3;
    }

    public void setBu3(String bu3) {
        this.bu3 = bu3;
    }

    public FS getFkIdfs() {
        return fkIdfs;
    }

    public void setFkIdfs(FS fkIdfs) {
        this.fkIdfs = fkIdfs;
    }

    // koristimo ih unutar tabele za renderovanje kolona
    // i još važnije, za mogućnost sortiranja po nazivu kolone !
    // pozivanje u tabeli je : setVisibleColumns(..."FS1", ...)
    public String getFS1() {
        return getFkIdfs().toString();
    }

    // pozivanje u tabeli je : setVisibleColumns(..."partner1", ...)
    public String getPartner1() {
        return getFkIdp().getNaziv();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idu != null ? idu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ugovor)) {
            return false;
        }
        Ugovor other = (Ugovor) object;
        return !((this.idu == null && other.idu != null) || (this.idu != null && !this.idu.equals(other.idu)));
    }

    @Override
    public String toString() {
        return getBrojUgovora() + ", "
                + getFkIdp() + ", "
                + getFkIdfs();
    }

}

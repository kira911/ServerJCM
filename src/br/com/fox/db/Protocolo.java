/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.db;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dvr
 */
@Entity
@Table(name = "protocolo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Protocolo.findAll", query = "SELECT p FROM Protocolo p"),
    @NamedQuery(name = "Protocolo.findByIdprotocolo", query = "SELECT p FROM Protocolo p WHERE p.idprotocolo = :idprotocolo"),
    @NamedQuery(name = "Protocolo.findByEvento", query = "SELECT p FROM Protocolo p WHERE p.evento = :evento"),
    @NamedQuery(name = "Protocolo.findByDescricao", query = "SELECT p FROM Protocolo p WHERE p.descricao = :descricao"),
    @NamedQuery(name = "Protocolo.findByTipo", query = "SELECT p FROM Protocolo p WHERE p.tipo = :tipo")})
public class Protocolo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprotocolo")
    private Integer idprotocolo;
    @Column(name = "evento")
    private String evento;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "cuc", referencedColumnName = "idcuc")
    @ManyToOne
    private Cuc cuc;
    @OneToMany(mappedBy = "idprotocolo")
    private List<Alarme> alarmeList;

    public Protocolo() {
    }

    public Protocolo(Integer idprotocolo) {
        this.idprotocolo = idprotocolo;
    }

    public Integer getIdprotocolo() {
        return idprotocolo;
    }

    public void setIdprotocolo(Integer idprotocolo) {
        this.idprotocolo = idprotocolo;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Cuc getCuc() {
        return cuc;
    }

    public void setCuc(Cuc cuc) {
        this.cuc = cuc;
    }

    @XmlTransient
    public List<Alarme> getAlarmeList() {
        return alarmeList;
    }

    public void setAlarmeList(List<Alarme> alarmeList) {
        this.alarmeList = alarmeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprotocolo != null ? idprotocolo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Protocolo)) {
            return false;
        }
        Protocolo other = (Protocolo) object;
        if ((this.idprotocolo == null && other.idprotocolo != null) || (this.idprotocolo != null && !this.idprotocolo.equals(other.idprotocolo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.Protocolo[ idprotocolo=" + idprotocolo + " ]";
    }
    
}

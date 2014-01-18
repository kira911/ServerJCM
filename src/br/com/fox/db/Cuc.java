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
import javax.persistence.Lob;
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
@Table(name = "cuc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuc.findAll", query = "SELECT c FROM Cuc c"),
    @NamedQuery(name = "Cuc.findByIdcuc", query = "SELECT c FROM Cuc c WHERE c.idcuc = :idcuc"),
    @NamedQuery(name = "Cuc.findBySigla", query = "SELECT c FROM Cuc c WHERE c.sigla = :sigla"),
    @NamedQuery(name = "Cuc.findByDescricao", query = "SELECT c FROM Cuc c WHERE c.descricao = :descricao"),
    @NamedQuery(name = "Cuc.findByPrioridade", query = "SELECT c FROM Cuc c WHERE c.prioridade = :prioridade"),
    @NamedQuery(name = "Cuc.findByTipoEvento", query = "SELECT c FROM Cuc c WHERE c.tipoEvento = :tipoEvento"),
    @NamedQuery(name = "Cuc.findByAuxiliar", query = "SELECT c FROM Cuc c WHERE c.auxiliar = :auxiliar")})
public class Cuc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuc")
    private Integer idcuc;
    @Column(name = "sigla")
    private String sigla;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "prioridade")
    private String prioridade;
    @Column(name = "tipo_evento")
    private String tipoEvento;
    @Lob
    @Column(name = "som")
    private String som;
    @Column(name = "auxiliar")
    private String auxiliar;
    @OneToMany(mappedBy = "cuc")
    private List<Protocolo> protocoloList;

    public Cuc() {
    }

    public Cuc(Integer idcuc) {
        this.idcuc = idcuc;
    }

    public Integer getIdcuc() {
        return idcuc;
    }

    public void setIdcuc(Integer idcuc) {
        this.idcuc = idcuc;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getSom() {
        return som;
    }

    public void setSom(String som) {
        this.som = som;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    @XmlTransient
    public List<Protocolo> getProtocoloList() {
        return protocoloList;
    }

    public void setProtocoloList(List<Protocolo> protocoloList) {
        this.protocoloList = protocoloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuc != null ? idcuc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuc)) {
            return false;
        }
        Cuc other = (Cuc) object;
        if ((this.idcuc == null && other.idcuc != null) || (this.idcuc != null && !this.idcuc.equals(other.idcuc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.Cuc[ idcuc=" + idcuc + " ]";
    }
    
}

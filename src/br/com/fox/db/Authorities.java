/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.db;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dvr
 */
@Entity
@Table(name = "authorities")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authorities.findAll", query = "SELECT a FROM Authorities a"),
    @NamedQuery(name = "Authorities.findByIdauthorities", query = "SELECT a FROM Authorities a WHERE a.idauthorities = :idauthorities"),
    @NamedQuery(name = "Authorities.findByAuthority", query = "SELECT a FROM Authorities a WHERE a.authority = :authority")})
public class Authorities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idauthorities")
    private Integer idauthorities;
    @Column(name = "authority")
    private String authority;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Users username;

    public Authorities() {
    }

    public Authorities(Integer idauthorities) {
        this.idauthorities = idauthorities;
    }

    public Integer getIdauthorities() {
        return idauthorities;
    }

    public void setIdauthorities(Integer idauthorities) {
        this.idauthorities = idauthorities;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idauthorities != null ? idauthorities.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authorities)) {
            return false;
        }
        Authorities other = (Authorities) object;
        if ((this.idauthorities == null && other.idauthorities != null) || (this.idauthorities != null && !this.idauthorities.equals(other.idauthorities))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.fox.db.Authorities[ idauthorities=" + idauthorities + " ]";
    }
    
}

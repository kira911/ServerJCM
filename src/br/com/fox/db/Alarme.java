/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fox.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dvr
 */
@Entity
@Table(name = "alarme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alarme.findAll", query = "SELECT a FROM Alarme a"),
    @NamedQuery(name = "Alarme.findByIdalarme", query = "SELECT a FROM Alarme a WHERE a.idalarme = :idalarme"),
    @NamedQuery(name = "Alarme.findByReceiver", query = "SELECT a FROM Alarme a WHERE a.receiver = :receiver"),
    @NamedQuery(name = "Alarme.findByLinha", query = "SELECT a FROM Alarme a WHERE a.linha = :linha"),
    @NamedQuery(name = "Alarme.findByParticao", query = "SELECT a FROM Alarme a WHERE a.particao = :particao"),
    @NamedQuery(name = "Alarme.findByStatus", query = "SELECT a FROM Alarme a WHERE a.status = :status"),
    @NamedQuery(name = "Alarme.findByDuracao", query = "SELECT a FROM Alarme a WHERE a.duracao = :duracao")})
public class Alarme implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idalarme")
    private Integer idalarme;
    @Column(name = "data_recebimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRecebimento;
    @Column(name = "data_espera")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEspera;
    @Column(name = "data_encerramento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEncerramento;
    @Column(name = "receiver")
    private Integer receiver;
    @Column(name = "linha")
    private Integer linha;
    @Column(name = "particao")
    private Integer particao;
    @Lob
    @Column(name = "log")
    private String log;
    @Column(name = "status")
    private String status;
    @Column(name = "duracao")
    private String duracao;
    @Column(name = "zona")
    private Integer zona;
    @Column(name = "check_horario")
    private String checkHorario;
    @JoinColumn(name = "username", referencedColumnName = "username")
    @ManyToOne
    private Users username;
    @JoinColumn(name = "idprotocolo", referencedColumnName = "idprotocolo")
    @ManyToOne
    private Protocolo idprotocolo;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne
    private Cliente idcliente;

    public Alarme() {
    }

    public Alarme(Integer idalarme) {
        this.idalarme = idalarme;
    }

    public Integer getIdalarme() {
        return idalarme;
    }

    public void setIdalarme(Integer idalarme) {
        this.idalarme = idalarme;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }

    public Date getDataEspera() {
        return dataEspera;
    }

    public void setDataEspera(Date dataEspera) {
        this.dataEspera = dataEspera;
    }

    public Date getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(Date dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    public Integer getReceiver() {
        return receiver;
    }

    public void setReceiver(Integer receiver) {
        this.receiver = receiver;
    }

    public Integer getLinha() {
        return linha;
    }

    public void setLinha(Integer linha) {
        this.linha = linha;
    }

    public Integer getParticao() {
        return particao;
    }

    public void setParticao(Integer particao) {
        this.particao = particao;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Integer getZona() {
        return zona;
    }

    public void setZona(Integer zona) {
        this.zona = zona;
    }

    public String getCheckHorario() {
        return checkHorario;
    }

    public void setCheckHorario(String checkHorario) {
        this.checkHorario = checkHorario;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public Protocolo getIdprotocolo() {
        return idprotocolo;
    }

    public void setIdprotocolo(Protocolo idprotocolo) {
        this.idprotocolo = idprotocolo;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idalarme != null ? idalarme.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alarme)) {
            return false;
        }
        Alarme other = (Alarme) object;
        if ((this.idalarme == null && other.idalarme != null) || (this.idalarme != null && !this.idalarme.equals(other.idalarme))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Alarme{" + "idalarme=" + idalarme + ", dataRecebimento=" + dataRecebimento + ", dataEspera=" + dataEspera + ", dataEncerramento=" + dataEncerramento + ", receiver=" + receiver + ", linha=" + linha + ", particao=" + particao + ", log=" + log + ", status=" + status + ", duracao=" + duracao + ", zona=" + zona + ", username=" + username + ", idprotocolo=" + idprotocolo + ", idcliente=" + idcliente + '}';
    }

}

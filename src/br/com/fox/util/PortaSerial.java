package br.com.fox.util;

import com.towel.el.annotation.Resolvable;
import java.io.Serializable;

/**
 *
 * @author Dvr
 */
public class PortaSerial implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Resolvable(colName = "Porta")
    private String porta;
    @Resolvable(colName = "Baud Rate")
    private Integer baudRate;
    @Resolvable(colName = "Nº de Bits")
    private Integer numBits;
    @Resolvable(colName = "Paridade")
    private String paridade;
    @Resolvable(colName = "Stop Bits")
    private Integer stopBits;
    @Resolvable(colName = "Status")
    private boolean status;
    @Resolvable(colName = "Receptora")
    private String receptora;

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public Integer getNumBits() {
        return numBits;
    }

    public void setNumBits(Integer numBits) {
        this.numBits = numBits;
    }

    public String getParidade() {
        return paridade;
    }

    public void setParidade(String paridade) {
        this.paridade = paridade;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public Integer getStopBits() {
        return stopBits;
    }

    public void setStopBits(Integer stopBits) {
        this.stopBits = stopBits;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getReceptora() {
        return receptora;
    }

    public void setReceptora(String receptora) {
        this.receptora = receptora;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PortaSerial other = (PortaSerial) obj;
        if ((this.porta == null) ? (other.porta != null) : !this.porta.equals(other.porta)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.porta != null ? this.porta.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PortaSerial{" + "porta=" + porta + ", baudRate=" + baudRate + ", numBits=" + numBits + ", paridade=" + paridade + ", stopBits=" + stopBits + ", status=" + status + ", receptora=" + receptora + '}';
    }

}

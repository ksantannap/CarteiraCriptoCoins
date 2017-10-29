package com.example.particular.convertbitcoin.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Particular on 01/09/2017.
 */

public class InvestimentoVO {

    private int id;
    private int cdTpMoeda;
    private String dtCompra;
    private BigDecimal vlCompraMoeda;
    private BigDecimal vlInvestimento;
    private BigDecimal qtInvestimento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCdTpMoeda() {
        return cdTpMoeda;
    }

    public void setCdTpMoeda(int cdTpMoeda) {
        this.cdTpMoeda = cdTpMoeda;
    }

    public String getDtCompra() {
        return dtCompra;
    }

    public void setDtCompra(String dtCompra) {
        this.dtCompra = dtCompra;
    }

    public BigDecimal getVlCompraMoeda() {
        return vlCompraMoeda;
    }

    public void setVlCompraMoeda(BigDecimal vlCompraMoeda) {
        this.vlCompraMoeda = vlCompraMoeda;
    }

    public BigDecimal getVlInvestimento() {
        return vlInvestimento;
    }

    public void setVlInvestimento(BigDecimal vlInvestimento) {
        this.vlInvestimento = vlInvestimento;
    }

    public BigDecimal getQtInvestimento() {
        return qtInvestimento;
    }

    public void setQtInvestimento(BigDecimal qtInvestimento) {
        this.qtInvestimento = qtInvestimento;
    }

    @Override
    public String toString() {
        return "InvestimentoVO{" +
                "id=" + id +
                ", cdTpMoeda=" + cdTpMoeda +
                ", dtCompra=" + dtCompra +
                ", vlCompraMoeda=" + vlCompraMoeda +
                ", vlInvestimento=" + vlInvestimento +
                ", qtInvestimento=" + qtInvestimento +
                '}';
    }
}

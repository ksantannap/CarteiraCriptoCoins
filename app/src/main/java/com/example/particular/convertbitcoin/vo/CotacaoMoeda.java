package com.example.particular.convertbitcoin.vo;

import com.example.particular.convertbitcoin.helper.Util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Particular on 02/09/2017.
 */

public class CotacaoMoeda {

    private String high;
    private String low;
    private String vol;
    private String last;
    private String buy;
    private String sell;
    private String date;

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getVol() {
        return vol;
    }

    public void setVol(String vol) {
        this.vol = vol;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.getBuy() + " - " + this.getHigh() + " - " + this.getLow() + " - " +  "R$" + Util.formatarMoeda(this.last);
    }
}

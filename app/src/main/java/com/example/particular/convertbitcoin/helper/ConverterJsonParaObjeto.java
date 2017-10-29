package com.example.particular.convertbitcoin.helper;

import com.example.particular.convertbitcoin.vo.CotacaoMoeda;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

/**
 * Created by Particular on 13/09/2017.
 */

public class ConverterJsonParaObjeto {

    public CotacaoMoeda converter(String string) {
        CotacaoMoeda cotacao = null;
        try {
            cotacao = new CotacaoMoeda();
            JSONObject json = new JSONObject(string);
            JSONObject ticker = json.getJSONObject("ticker");
            cotacao.setHigh(ticker.getString("high"));
            cotacao.setLow(ticker.getString("low"));
            cotacao.setVol(ticker.getString("vol"));
            cotacao.setLast(ticker.getString("last"));
            cotacao.setBuy(ticker.getString("buy"));
            cotacao.setSell(ticker.getString("sell"));
            cotacao.setDate(ticker.getString("date"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cotacao;
    }

}

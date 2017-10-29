package com.example.particular.convertbitcoin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.particular.convertbitcoin.R;
import com.example.particular.convertbitcoin.helper.Util;
import com.example.particular.convertbitcoin.vo.InvestimentoVO;

import java.util.ArrayList;

/**
 * Created by Particular on 01/09/2017.
 */

public class AdapterInvestimento extends BaseAdapter {

    private final Context context;
    private final ArrayList<InvestimentoVO> investimentos;

    public AdapterInvestimento(Context context, ArrayList array) {
        this.context = context;
        this.investimentos = array;
    }

    @Override
    public int getCount() {
        return investimentos.size();
    }

    @Override
    public Object getItem(int i) {
        return investimentos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return investimentos.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        InvestimentoVO investimentoVO = investimentos.get(i);

        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.lista_item_investimento, parent, false);
        }

        TextView data = view.findViewById(R.id.lista_data);
        data.setText(investimentoVO.getDtCompra());

        TextView vlBtc = view.findViewById(R.id.lista_valor_btc);
        vlBtc.setText("R$" + Util.formatarMoeda(investimentoVO.getVlCompraMoeda().toString()));

        TextView vlBrl = view.findViewById(R.id.lista_valor_brl);
        vlBrl.setText("R$" + Util.formatarMoeda(investimentoVO.getVlInvestimento().toString()));

        TextView qtMoeda = view.findViewById(R.id.lista_qt_moeda);
        qtMoeda.setText(investimentoVO.getQtInvestimento().setScale(5).toString().replace(".", ","));

        return view;
    }
}

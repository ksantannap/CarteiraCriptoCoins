package com.example.particular.convertbitcoin.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.particular.convertbitcoin.R;
import com.example.particular.convertbitcoin.adapter.AdapterInvestimento;
import com.example.particular.convertbitcoin.dao.InvestimentoDAO;
import com.example.particular.convertbitcoin.helper.Util;
import com.example.particular.convertbitcoin.vo.InvestimentoVO;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Particular on 02/09/2017.
 */

public class MeusInvestimentos extends AppCompatActivity{

    private ListView listaInvestimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_investimento);

        carregarInvestimentos();

        registerForContextMenu(listaInvestimentos);

    }

    @Override
    protected void onResume() {
        super.onResume();

        carregarInvestimentos();
    }

    private void carregarInvestimentos() {
        InvestimentoDAO dao = new InvestimentoDAO(this);
        ArrayList<InvestimentoVO> investimentos = dao.getInvestimentos();
        listaInvestimentos = (ListView) findViewById(R.id.lista_item_investimento);

        AdapterInvestimento adapter = new AdapterInvestimento(this, investimentos);

        TextView totalBtc = (TextView) findViewById(R.id.lista_rodape_vl_btc);
        TextView totalBrl = (TextView) findViewById(R.id.lista_rodape_vl_brl);
        BigDecimal qtTotalBtc = BigDecimal.ZERO;
        BigDecimal vlTotalBrl = BigDecimal.ZERO;

        for (InvestimentoVO investimentoVO : investimentos) {
            qtTotalBtc = qtTotalBtc.add(investimentoVO.getQtInvestimento());
            vlTotalBrl = vlTotalBrl.add(investimentoVO.getVlInvestimento());
        }

        totalBtc.setText(qtTotalBtc.toString());
        totalBrl.setText("R$" + Util.formatarMoeda(vlTotalBrl.toString()));

        listaInvestimentos.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        final InvestimentoVO investimentoVO = (InvestimentoVO) listaInvestimentos.getItemAtPosition(info.position);

        MenuItem deletar = menu.add("Deletar investimento");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                InvestimentoDAO dao = new InvestimentoDAO(MeusInvestimentos.this);
                dao.deletarInvestimento(investimentoVO);

                Toast.makeText(MeusInvestimentos.this, "Investimento excluido!", Toast.LENGTH_SHORT).show();

                carregarInvestimentos();

                return false;
            }
        });

    }
}

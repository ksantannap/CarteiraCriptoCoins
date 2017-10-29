package com.example.particular.convertbitcoin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.particular.convertbitcoin.R;
import com.example.particular.convertbitcoin.constantes.Constantes;
import com.example.particular.convertbitcoin.dao.InvestimentoDAO;
import com.example.particular.convertbitcoin.vo.InvestimentoVO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastrarInvestimento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_investimento);

        Button botaoCadastrar = (Button) findViewById(R.id.cadastro_botaoCadastrar);
        final TextView vlCompra = (TextView) findViewById(R.id.cadastro_vlCompra);
        final TextView vlInvestimento = (TextView) findViewById(R.id.cadastro_qtComprou);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!vlCompra.getText().toString().isEmpty() && !vlInvestimento.getText().toString().isEmpty()) {

                    BigDecimal qtComprou = new BigDecimal(vlInvestimento.getText().toString()).divide(new BigDecimal(vlCompra.getText().toString()), 5, RoundingMode.HALF_UP);

                    InvestimentoVO investimentoVO = new InvestimentoVO();
                    InvestimentoDAO dao = new InvestimentoDAO(CadastrarInvestimento.this);

                    investimentoVO.setCdTpMoeda(Constantes.TipoMoeda.BITCOIN);
                    investimentoVO.setDtCompra(new SimpleDateFormat("dd/MM/yy").format(new Date()));
                    investimentoVO.setVlCompraMoeda(new BigDecimal(vlCompra.getText().toString()));
                    investimentoVO.setVlInvestimento(new BigDecimal(vlInvestimento.getText().toString()));
                    investimentoVO.setQtInvestimento(qtComprou.setScale(5));

                    dao.cadastrarInvestimento(investimentoVO);

                    Toast.makeText(CadastrarInvestimento.this, "Investimento cadastrado!", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(CadastrarInvestimento.this, "Preencha os campos para cadastro", Toast.LENGTH_SHORT).show();
                }

                finish();

            }
        });
    }
}

package com.example.particular.convertbitcoin.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.particular.convertbitcoin.ConsultaMoedaTask;
import com.example.particular.convertbitcoin.R;
import com.example.particular.convertbitcoin.helper.ConverterJsonParaObjeto;
import com.example.particular.convertbitcoin.helper.Util;
import com.example.particular.convertbitcoin.vo.CotacaoMoeda;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;

public class SimularRendimentoBitCoin extends AppCompatActivity {

    private CotacaoMoeda cotacaoMoeda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simular_rendimento_bit_coin);

        final Button botaoSimular = (Button)  findViewById(R.id.simulador_botaoSimular);

        final EditText vlCompra = (EditText) findViewById(R.id.simulador_vlCompra);
        final EditText vlAtual = (EditText) findViewById(R.id.simulador_vlAtual);
        final EditText vlInvestido = (EditText) findViewById(R.id.simulador_qtComprou);
        final TextView txtLucroOuPerda = (TextView) findViewById(R.id.simulador_txtLucroOuPerda);
        final TextView vlRendimento = (TextView) findViewById(R.id.simulador_vlCalculado);
        final TextView txtReais = (TextView) findViewById(R.id.simulador_txtReais);

        try {
            ConsultaMoedaTask task = (ConsultaMoedaTask) new ConsultaMoedaTask(this, SimularRendimentoBitCoin.this).execute();
            String response = task.get();
            cotacaoMoeda = new ConverterJsonParaObjeto().converter(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        vlAtual.setText(String.valueOf(new BigDecimal(cotacaoMoeda.getLast()).setScale(2, RoundingMode.FLOOR)));

        botaoSimular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!vlAtual.getText().toString().isEmpty() && !vlInvestido.getText().toString().isEmpty()) {
                    BigDecimal vlrCompra = new BigDecimal(vlCompra.getText().toString());

                    BigDecimal vlrAtual = new BigDecimal(vlAtual.getText().toString());
                    BigDecimal vlrInvestido = new BigDecimal(vlInvestido.getText().toString());

                    BigDecimal pcInvestido = vlrInvestido.divide(vlrCompra, 10, RoundingMode.FLOOR);

                    BigDecimal vlrRendimento = pcInvestido.multiply(vlrAtual);

                    txtLucroOuPerda.setText("Você possui");
                    vlRendimento.setText("R$ " + vlrRendimento.setScale(2, RoundingMode.HALF_UP).toString());
                    txtReais.setText("Reais");

                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(botaoSimular.getWindowToken(), 0);

                } else {
                    Toast.makeText(SimularRendimentoBitCoin.this, "Favor preencher os campos para calculo", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

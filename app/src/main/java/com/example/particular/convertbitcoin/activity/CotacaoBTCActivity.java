package com.example.particular.convertbitcoin.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.particular.convertbitcoin.ConsultaMoedaTask;
import com.example.particular.convertbitcoin.R;
import com.example.particular.convertbitcoin.helper.ConverterJsonParaObjeto;
import com.example.particular.convertbitcoin.helper.Util;
import com.example.particular.convertbitcoin.vo.CotacaoMoeda;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

public class CotacaoBTCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_cotacao_moeda);

        CotacaoMoeda cotacaoMoeda = null;

        try {
            ConsultaMoedaTask task = (ConsultaMoedaTask) new ConsultaMoedaTask(this, CotacaoBTCActivity.this).execute();
            String response = task.get();
            cotacaoMoeda = new ConverterJsonParaObjeto().converter(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        TextView data = (TextView) findViewById(R.id.cotacao_data);
        TextView menorPreco = (TextView) findViewById(R.id.cotacao_menor_preco);
        TextView maiorPreco = (TextView) findViewById(R.id.cotacao_maior_preco);
        TextView precoAtual = (TextView) findViewById(R.id.cotacao_preco_atual);

        GregorianCalendar time = new GregorianCalendar();

        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy").format(time.getTimeInMillis());

        if (cotacaoMoeda!=null) {
            menorPreco.setText("R$" + Util.formatarMoeda(cotacaoMoeda.getLow()));
            maiorPreco.setText("R$" + Util.formatarMoeda(cotacaoMoeda.getHigh()));
            precoAtual.setText("R$" + Util.formatarMoeda(cotacaoMoeda.getLast()));
            data.setText(dataFormatada);
        }

    }

    public void avisarValorBtc(final View view) {

        EditText precoParaSerAvisado = (EditText) findViewById(R.id.cotacao_preco_avisar);

        AlertDialog.Builder build = new AlertDialog.Builder(CotacaoBTCActivity.this);
        build.setTitle("Quer ser avisado?")
                .setMessage("Você será avisado quando o valor do Bitcoin chegar em R$"
                        + Util.formatarMoeda(precoParaSerAvisado.getText().toString())
                        +". \nConfirma?")
                .setIcon(R.drawable.notificacao_2)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CotacaoBTCActivity.this, "You are smart!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("Não quero!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog alerta = build.create();
        alerta.show();

    }
}

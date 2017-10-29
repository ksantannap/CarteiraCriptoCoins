package com.example.particular.convertbitcoin.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.particular.convertbitcoin.ConsultaMoedaTask;
import com.example.particular.convertbitcoin.R;
import com.example.particular.convertbitcoin.client.ClientConsultaMoeda;
import com.example.particular.convertbitcoin.constantes.Constantes;
import com.example.particular.convertbitcoin.helper.ConverterJsonParaObjeto;
import com.example.particular.convertbitcoin.helper.Util;
import com.example.particular.convertbitcoin.vo.CotacaoMoeda;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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
}

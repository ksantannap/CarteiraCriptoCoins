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

import com.example.particular.convertbitcoin.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalcularBitCoin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcular_bit_coin);

        final Button botaoConverter = (Button) findViewById(R.id.converter_botao);
        final EditText vlBitcoin = (EditText) findViewById(R.id.converter_vlBitcoin);
        final EditText vlReais = (EditText) findViewById(R.id.converter_vlReais);
        final TextView vlConvertido = (TextView) findViewById(R.id.converter_vlConvertido);
        final TextView txtVocePossui = (TextView) findViewById(R.id.converter_txtVocePossui);
        final TextView txtBitcoins = (TextView) findViewById(R.id.converter_txtBitcoins);

        botaoConverter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!vlBitcoin.getText().toString().isEmpty() && !vlReais.getText().toString().isEmpty()) {
                    BigDecimal vlrBitcoin = new BigDecimal(vlBitcoin.getText().toString());
                    BigDecimal vlrReais = new BigDecimal(vlReais.getText().toString());
                    BigDecimal vlrConvertido = vlrReais.divide(vlrBitcoin, 10, RoundingMode.FLOOR);

                    txtVocePossui.setText("Você possui");
                    vlConvertido.setText(vlrConvertido.toString());
                    txtBitcoins.setText("Bitcoins");

                    ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(botaoConverter.getWindowToken(), 0);

                } else {
                    Toast.makeText(CalcularBitCoin.this, "Favor preencher os campos para calculo", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

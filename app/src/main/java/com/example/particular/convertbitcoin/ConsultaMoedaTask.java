package com.example.particular.convertbitcoin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.example.particular.convertbitcoin.client.ClientConsultaMoeda;

public class ConsultaMoedaTask extends AsyncTask<Object, Object, String> {

    private Context context;
    private Activity activity;
    private ProgressDialog progress;

    public ConsultaMoedaTask(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Aguarde", "Consultando valores...", true, true);
    }

    @Override
    protected String doInBackground(Object... objects) {
        return new ClientConsultaMoeda().consultaBtc();
    }

    @Override
    protected void onPostExecute(String resposta) {
        progress.dismiss();
    }

}

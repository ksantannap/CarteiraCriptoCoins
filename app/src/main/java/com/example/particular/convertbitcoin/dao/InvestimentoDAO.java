package com.example.particular.convertbitcoin.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.particular.convertbitcoin.constantes.Constantes;
import com.example.particular.convertbitcoin.vo.InvestimentoVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Particular on 01/09/2017.
 */

public class InvestimentoDAO extends SQLiteOpenHelper {

    public InvestimentoDAO(Context context) {
        super(context, Constantes.DataBaseName, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE tb_investimento (");
        sql.append("    id INTEGER PRIMARY KEY, ");
        sql.append("    dt_compra TEXT, ");
        sql.append("    cd_tp_moeda INTEGER, ");
        sql.append("    vl_compra_moeda REAL, ");
        sql.append("    vl_investimento REAL, ");
        sql.append("    qt_investimento REAL);");

        db.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        switch (newVersion) {
            case 4:
                String sql = "DROP TABLE tb_investimento";
                db.execSQL(sql);
                onCreate(db);
                System.out.println("Tabela recriada.");
        }

    }

    public void cadastrarInvestimento(InvestimentoVO investimentoVO) {

        System.out.println("Realizando o cadastramento do investimento...");

        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues params = getInvestimento(investimentoVO);
            db.insert("tb_investimento", null, params);

            System.out.println("Cadastro de investimento feito com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    private ContentValues getInvestimento(InvestimentoVO investimentoVO) {
        ContentValues values = new ContentValues();
        values.put("dt_compra", investimentoVO.getDtCompra());
        values.put("cd_tp_moeda", investimentoVO.getCdTpMoeda());
        values.put("vl_compra_moeda", String.valueOf(investimentoVO.getVlCompraMoeda()));
        values.put("vl_investimento", String.valueOf(investimentoVO.getVlInvestimento()));
        values.put("qt_investimento", String.valueOf(investimentoVO.getQtInvestimento()));
        return values;
    }

    public ArrayList<InvestimentoVO> getInvestimentos() {

        System.out.println("Consultando investimentos...");
        String sql = "SELECT * FROM tb_investimento";
        ArrayList<InvestimentoVO> investimentos = new ArrayList<InvestimentoVO>();
        SQLiteDatabase db = getReadableDatabase();

        try {
            Cursor c = db.rawQuery(sql.toString(), null);

            while (c.moveToNext()) {
                InvestimentoVO investimentoVO = new InvestimentoVO();
                investimentoVO.setId(c.getInt(c.getColumnIndex("id")));
                investimentoVO.setDtCompra(c.getString(c.getColumnIndex("dt_compra")));
                investimentoVO.setCdTpMoeda(c.getInt(c.getColumnIndex("cd_tp_moeda")));
                investimentoVO.setVlCompraMoeda(new BigDecimal(c.getString(c.getColumnIndex("vl_compra_moeda"))));
                investimentoVO.setVlInvestimento(new BigDecimal(c.getString(c.getColumnIndex("vl_investimento"))));
                investimentoVO.setQtInvestimento(new BigDecimal(c.getString(c.getColumnIndex("qt_investimento"))));
                investimentos.add(investimentoVO);
            }

            System.out.println("Encontrado " + investimentos.size() + " investimentos.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return investimentos;
    }

    public void deletarInvestimento(InvestimentoVO investimentoVO) {
        SQLiteDatabase db = getWritableDatabase();

        try {
            String[] params = {String.valueOf(investimentoVO.getId())};
            db.delete("tb_investimento", "id = ?", params);
            System.out.println("Investimento excluido com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }
}
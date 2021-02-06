package com.example.gustavo.diabeticlife.Anotacoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControlerAgenda {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    public BancoControlerAgenda(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public String cadastrarAgenda(String anot, String data, String hora){
        ContentValues valores = new ContentValues();
        long resultado;
        db = bancoDeDados.getWritableDatabase();
        valores.put(BancoDeDados.ANOTAGENDA, anot);
        valores.put(BancoDeDados.DATAAGENDA, data);
        valores.put(BancoDeDados.HORAAGENDA, hora);
        resultado = db.insert(BancoDeDados.TABELAAGENDA, null, valores);
        db.close();
        if(resultado == -1)
            return "Erro ao salvar";
        else
            return "Anotação salva";
    }

    public Cursor consultarAgenda(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDAGENDA, bancoDeDados.DATAAGENDA, bancoDeDados.ANOTAGENDA, bancoDeDados.HORAAGENDA};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAAGENDA, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public void alteraAgenda(int id, String dataAgenda, String anot, String hora){
        ContentValues valores;
        String where;

        db = bancoDeDados.getWritableDatabase();

        where = BancoDeDados.IDAGENDA + "=" + id;

        valores = new ContentValues();
        valores.put(BancoDeDados.DATAAGENDA, dataAgenda);
        valores.put(BancoDeDados.ANOTAGENDA, anot);
        valores.put(BancoDeDados.HORAAGENDA, hora);

        db.update(BancoDeDados.TABELAAGENDA,valores,where,null);
        db.close();
    }

    public void deletarAgenda(int id){
        String where = BancoDeDados.IDAGENDA + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        db.delete(BancoDeDados.TABELAAGENDA,where,null);
        db.close();
    }

}

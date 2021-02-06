package com.example.gustavo.diabeticlife.Anotacoes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControllerAnotacao {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    public BancoControllerAnotacao(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public String cadastrarAnotacao(String anotacao, String horario){

        ContentValues valores = new ContentValues();
        long resultado;

        db = bancoDeDados.getWritableDatabase();
        valores.put(BancoDeDados.ANOTACAO, anotacao);
        valores.put(BancoDeDados.HORAANOTACAO, horario);

        resultado = db.insert(BancoDeDados.TABELAANOTACAO, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao salvar anotações";
        else
            return "Anotação salva";

    }

    public Cursor consultarAnotados(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDANOTACAO, bancoDeDados.HORAANOTACAO, bancoDeDados.ANOTACAO};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAANOTACAO, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public Cursor carregarAnotacaoPorID(int id){
        Cursor cursor;
        String[] campos = {bancoDeDados.HORAANOTACAO, bancoDeDados.ANOTACAO};
        String where = BancoDeDados.IDANOTACAO + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(BancoDeDados.TABELAANOTACAO, campos, where, null, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public void alteraAnotacao(int id, String anotacao, String horario){
        ContentValues valores;
        String where;

        db = bancoDeDados.getWritableDatabase();

        where = BancoDeDados.IDANOTACAO + "=" + id;

        valores = new ContentValues();
        valores.put(BancoDeDados.ANOTACAO, anotacao);
        valores.put(BancoDeDados.HORAANOTACAO, horario);

        db.update(BancoDeDados.TABELAANOTACAO,valores,where,null);
        db.close();
    }

    public void deletarAnotacao(int id){
        String where = BancoDeDados.IDANOTACAO + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        db.delete(BancoDeDados.TABELAANOTACAO,where,null);
        db.close();
    }

}

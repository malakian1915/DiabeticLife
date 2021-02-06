package com.example.gustavo.diabeticlife.Grafico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControllerGrafico {

    SQLiteDatabase db;
    BancoDeDados banco;

    public BancoControllerGrafico(Context context) {
        banco = new BancoDeDados(context);
    }

    public String cadastrarDados(float eixoX, float eixoY, String hora){
        ContentValues valores = new ContentValues();
        long resultado;
        db = banco.getWritableDatabase();
        valores.put(BancoDeDados.EIXOXGRAFICO, eixoX);
        valores.put(BancoDeDados.EIXOYGRAFICO, eixoY);
        valores.put(BancoDeDados.HORARIOGRAFICO, hora);
        resultado = db.insert(BancoDeDados.TABELAGRAFICO, null, valores);
        if(resultado == -1)
            return "Erro ao salvar dados";
        else
            return "Dados salvos";
    }

    public Cursor consultarDados(){
        Cursor cursor;
        String[] campos =  {banco.IDGRAFICO,banco.EIXOXGRAFICO, banco.EIXOYGRAFICO, banco.HORARIOGRAFICO};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.TABELAGRAFICO, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor retornaUltimoEixoX(){
        Cursor cursor;
        String[] campo = {banco.EIXOXGRAFICO};
        db = banco.getWritableDatabase();
        cursor = db.query(banco.TABELAGRAFICO, campo, null, null, null, null, null, null);

        if(cursor != null)
            cursor.moveToLast();

        db.close();
        return cursor;
    }

    public void deletarTodosDados(){
        db = banco.getWritableDatabase();
        db.delete(BancoDeDados.TABELAGRAFICO, null, null);
        db.close();
    }

}

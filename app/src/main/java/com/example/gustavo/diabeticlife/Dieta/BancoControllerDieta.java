package com.example.gustavo.diabeticlife.Dieta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControllerDieta {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    public BancoControllerDieta(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public String cadastrarDieta(String tipoDieta, String frutas, String carnes, String cereais, String verduras, String outros, String bebidas, String maisAlimentos, String data, String hora){

        ContentValues valores = new ContentValues();
        long resultado;

        db = bancoDeDados.getWritableDatabase();
        valores.put(BancoDeDados.TIPODIETA, tipoDieta);
        valores.put(BancoDeDados.FRUTAS, frutas);
        valores.put(BancoDeDados.CARNES, carnes);
        valores.put(BancoDeDados.CEREAISEGRAOS, cereais);
        valores.put(BancoDeDados.VERDURASELEGUMES, verduras);
        valores.put(BancoDeDados.OUTROS, outros);
        valores.put(BancoDeDados.BEBIDAS, bebidas);
        valores.put(BancoDeDados.MAISALIMENTOS, maisAlimentos);
        valores.put(BancoDeDados.DATADIETA, data);
        valores.put(BancoDeDados.HORADIETA, hora);

        resultado = db.insert(BancoDeDados.TABELADIETA, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao salvar dieta";
        else
            return "Dieta salva";

    }

    public Cursor consultarDietas(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDDIETA, bancoDeDados.TIPODIETA, bancoDeDados.FRUTAS, bancoDeDados.CARNES, bancoDeDados.CEREAISEGRAOS, bancoDeDados.VERDURASELEGUMES, bancoDeDados.OUTROS, bancoDeDados.BEBIDAS, bancoDeDados.MAISALIMENTOS, bancoDeDados.DATADIETA, bancoDeDados.HORADIETA};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELADIETA, campos, null, null, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public void deletarDieta(int id){
        String where = BancoDeDados.IDDIETA + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        db.delete(BancoDeDados.TABELADIETA,where,null);
        db.close();
    }

}

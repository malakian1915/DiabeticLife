package com.example.gustavo.diabeticlife.Sintomas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControllerSintomas {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    public BancoControllerSintomas(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public String cadastrarSintomas(String sintomas, String horario, String maisSintomas){
        ContentValues valores = new ContentValues();
        long resultado;

        db = bancoDeDados.getWritableDatabase();
        valores.put(BancoDeDados.SINTOMAS, sintomas);
        valores.put(BancoDeDados.HORARIOSINTOMAS, horario);
        valores.put(BancoDeDados.MAISSINTOMAS, maisSintomas);
        resultado = db.insert(BancoDeDados.TABELASINTOMAS, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao salvar sintomas";
        else
            return "Sintomas salvos";
    }

    public Cursor consultarSintomas(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDSINTOMAS, bancoDeDados.SINTOMAS, bancoDeDados.HORARIOSINTOMAS, bancoDeDados.MAISSINTOMAS};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELASINTOMAS, campos, null, null, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public void deletarSintomas(int id){
        String where = BancoDeDados.IDSINTOMAS + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        db.delete(BancoDeDados.TABELASINTOMAS,where,null);
        db.close();
    }

}

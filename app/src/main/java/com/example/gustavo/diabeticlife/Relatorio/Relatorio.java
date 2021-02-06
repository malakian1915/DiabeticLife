package com.example.gustavo.diabeticlife.Relatorio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class Relatorio {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    public Relatorio(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public Cursor pegarSintomas(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDSINTOMAS, bancoDeDados.SINTOMAS, bancoDeDados.MAISSINTOMAS, bancoDeDados.HORARIOSINTOMAS};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELASINTOMAS, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public Cursor pegarMedicamentos(){
        Cursor cursor;

        String[] campos = {bancoDeDados.IDMEDICAMENTOS, bancoDeDados.MEDICAMENTOS, bancoDeDados.DATAMEDICAMENTOS, bancoDeDados.HORAMEDICAMENTOS, bancoDeDados.MAISMEDICAMENTOS};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAMEDICAMENTOS, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public Cursor pegarGrafico(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDGRAFICO, bancoDeDados.EIXOYGRAFICO, bancoDeDados.HORARIOGRAFICO};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAGRAFICO, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public Cursor pegarDadosUsuario(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDUSUARIO, bancoDeDados.NOMEUSUARIO, bancoDeDados.IDADEUSUARIO, bancoDeDados.TIPODIABETES};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAUSUARIO, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

}

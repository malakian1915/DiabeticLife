package com.example.gustavo.diabeticlife.Medicamentos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControllerMedicamentos {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    public BancoControllerMedicamentos(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public String cadastrarMedicamentos(String remedios, String data, String hora, String maisMedicamentos){
        ContentValues valores = new ContentValues();
        long resultado;
        db = bancoDeDados.getWritableDatabase();
        valores.put(BancoDeDados.MEDICAMENTOS, remedios);
        valores.put(BancoDeDados.DATAMEDICAMENTOS, data);
        valores.put(BancoDeDados.HORAMEDICAMENTOS, hora);
        valores.put(BancoDeDados.MAISMEDICAMENTOS, maisMedicamentos);
        resultado = db.insert(BancoDeDados.TABELAMEDICAMENTOS, null, valores);
        db.close();
        if(resultado == -1)
            return "Erro ao salvar medicamentos";
        else
            return "Medicamentos salvos";
    }

    public Cursor consultarMedicamentos(){
        Cursor cursor;
        String[] campos = {bancoDeDados.IDMEDICAMENTOS, bancoDeDados.MEDICAMENTOS, bancoDeDados.DATAMEDICAMENTOS, bancoDeDados.HORAMEDICAMENTOS, bancoDeDados.MAISMEDICAMENTOS};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAMEDICAMENTOS, campos, null, null, null, null,null, null);

        if(cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public void deletarMedicamentos(int id){
        String where = BancoDeDados.IDMEDICAMENTOS + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        db.delete(BancoDeDados.TABELAMEDICAMENTOS,where,null);
        db.close();
    }

}

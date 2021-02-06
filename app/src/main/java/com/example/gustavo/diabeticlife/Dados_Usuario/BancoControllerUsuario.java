package com.example.gustavo.diabeticlife.Dados_Usuario;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;

public class BancoControllerUsuario {

    SQLiteDatabase db;
    BancoDeDados bancoDeDados;

    String verifica;

    public BancoControllerUsuario(Context context) {
        bancoDeDados = new BancoDeDados(context);
    }

    public String cadastrarUsuario(String nome, int idade, String tipo){
        ContentValues valores = new ContentValues();
        long resultado;

        db = bancoDeDados.getWritableDatabase();
        valores.put(BancoDeDados.NOMEUSUARIO, nome);
        valores.put(BancoDeDados.IDADEUSUARIO, idade);
        valores.put(BancoDeDados.TIPODIABETES, tipo);

        resultado = db.insert(BancoDeDados.TABELAUSUARIO, null, valores);
        db.close();

        if(resultado == -1)
            return "Erro ao cadastrar";
        else
            return "Cadastro realizado";
    }

    public String verificaSeCadastro(){
        db = bancoDeDados.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM "+bancoDeDados.TABELAUSUARIO, null);

        if (cursor != null) {
            cursor.moveToFirst();
            if (cursor.getInt (0) == 0)
                this.verifica = "não";
            else
                this.verifica = "sim";
        }
        return this.verifica;
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {bancoDeDados.IDUSUARIO, bancoDeDados.NOMEUSUARIO,bancoDeDados.IDADEUSUARIO,bancoDeDados.TIPODIABETES};
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(bancoDeDados.TABELAUSUARIO, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {bancoDeDados.IDUSUARIO,bancoDeDados.NOMEUSUARIO,bancoDeDados.IDADEUSUARIO,bancoDeDados.TIPODIABETES};
        String where = BancoDeDados.IDUSUARIO + "=" + id;
        db = bancoDeDados.getReadableDatabase();
        cursor = db.query(BancoDeDados.TABELAUSUARIO,campos,where, null, null, null, null, null);

        if(cursor!=null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public String alteraRegistro(int id, String nome, int idade, String tipo){
        ContentValues valores;
        String where;
        long resultado;

        db = bancoDeDados.getWritableDatabase();

        where = BancoDeDados.IDUSUARIO + "=" + id;

        valores = new ContentValues();
        valores.put(BancoDeDados.NOMEUSUARIO, nome);
        valores.put(BancoDeDados.IDADEUSUARIO, idade);
        valores.put(BancoDeDados.TIPODIABETES, tipo);

        resultado = db.update(BancoDeDados.TABELAUSUARIO,valores,where,null);
        db.close();

        if(resultado == -1)
            return "Erro ao alterar registro";
        else
            return "Registro alterado";
    }

}

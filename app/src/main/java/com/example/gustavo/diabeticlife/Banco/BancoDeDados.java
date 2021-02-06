package com.example.gustavo.diabeticlife.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoDeDados extends SQLiteOpenHelper {

    //NOME E VERSAO DO BANCO
    public final static String NOMEBANCO = "bdDiabetes";
    public static int VERSAO = 1;

    //DADOS TABELA ANOTACAO
    public final static String TABELAANOTACAO = "anotacao";
    public final static String IDANOTACAO = "_id";
    public final static String ANOTACAO = "_anotacao";
    public final static String HORAANOTACAO = "_horario";
    //DADOS TABELA USUARIO
    public final static String TABELAUSUARIO = "usuario";
    public final static String IDUSUARIO = "_id";
    public final static String NOMEUSUARIO = "_usuario";
    public final static String IDADEUSUARIO = "_idade";
    public final static String TIPODIABETES = "_tipo";
    //DADOS TABELA DIETA
    public final static String TABELADIETA = "dieta";
    public final static String IDDIETA = "_id";
    public final static String TIPODIETA = "_tipodieta";
    public final static String FRUTAS = "_frutas";
    public final static String CARNES = "_carnes";
    public final static String CEREAISEGRAOS = "_cereaisegraos";
    public final static String VERDURASELEGUMES = "_verduraselegumes";
    public final static String OUTROS = "_outros";
    public final static String BEBIDAS = "_bebidas";
    public final static String MAISALIMENTOS = "_maisalimentos";
    public final static String DATADIETA = "_data";
    public final static String HORADIETA = "_horario";
    //DADOS TABELA SINTOMAS
    public final static String TABELASINTOMAS = "sintomas";
    public final static String IDSINTOMAS = "_id";
    public final static String SINTOMAS = "_sintomas";
    public final static String HORARIOSINTOMAS = "_horario";
    public final static String MAISSINTOMAS = "_maissintomas";
    //DADOS TABELA MEDICAMENTOS
    public final static String TABELAMEDICAMENTOS = "medicamentos";
    public final static String IDMEDICAMENTOS = "_id";
    public final static String MEDICAMENTOS = "_medicamentos";
    public final static String DATAMEDICAMENTOS = "_data";
    public final static String HORAMEDICAMENTOS = "_horario";
    public final static String MAISMEDICAMENTOS = "_maismedicamentos";
    //DADOS TABELA AGENDA
    public final static String TABELAAGENDA = "agenda";
    public final static String IDAGENDA = "_id";
    public final static String ANOTAGENDA = "_anotacao";
    public final static String DATAAGENDA = "_data";
    public final static String HORAAGENDA = "_horario";
    //DADOS TABELA GRAFICO
    public static final String TABELAGRAFICO = "grafico";
    public static final String IDGRAFICO = "_id";
    public static final String EIXOXGRAFICO = "_eixoX";
    public static final String EIXOYGRAFICO = "_eixoY";
    public static final String HORARIOGRAFICO = "_hora";

    public BancoDeDados(Context context) {
        super(context, NOMEBANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CRIAR TABELA ANOTACAO
        String sqlCreateTableAnotacao = "CREATE TABLE "+TABELAANOTACAO+"("+IDANOTACAO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ANOTACAO+" TEXT, "+HORAANOTACAO+" TEXT)";
        db.execSQL(sqlCreateTableAnotacao);
        //CRIAR TABELA USUARIO
        String sqlCreateTableUsuario = "CREATE TABLE "+TABELAUSUARIO+"("+IDUSUARIO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NOMEUSUARIO+" TEXT, "+IDADEUSUARIO+" INTEGER, "+TIPODIABETES+" TEXT)";
        db.execSQL(sqlCreateTableUsuario);
        //CRIAR TABELA DIETA
        String sqlCreateTableDieta = "CREATE TABLE "+TABELADIETA+"("+IDDIETA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+TIPODIETA+" TEXT, "+FRUTAS+" TEXT, "+CARNES+" TEXT, "+CEREAISEGRAOS+" TEXT, "+VERDURASELEGUMES+" TEXT, "+OUTROS+" TEXT, "+BEBIDAS+" TEXT, "+MAISALIMENTOS+" TEXT, "+DATADIETA+" TEXT, "+HORADIETA+" TEXT)";
        db.execSQL(sqlCreateTableDieta);
        //CRIAR TABELA SINTOMAS
        String sqlCreateTableSintomas = "CREATE TABLE "+TABELASINTOMAS+"("+IDSINTOMAS+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SINTOMAS+" TEXT, "+HORARIOSINTOMAS+" TEXT, "+MAISSINTOMAS+" TEXT)";
        db.execSQL(sqlCreateTableSintomas);
        //CRIAR TABELA MEDICAMENTOS
        String sqlCreateTableMedicamentos = "CREATE TABLE "+TABELAMEDICAMENTOS+"("+IDMEDICAMENTOS+" INTEGER PRIMARY KEY AUTOINCREMENT, "+MEDICAMENTOS+" TEXT, "+DATAMEDICAMENTOS+" TEXT, "+HORAMEDICAMENTOS+" TEXT, "+MAISMEDICAMENTOS+" TEXT)";
        db.execSQL(sqlCreateTableMedicamentos);
        //CRIAR TABELA AGENDA
        String sqlCreateTableAgenda = "CREATE TABLE "+TABELAAGENDA+"("+IDAGENDA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+ANOTAGENDA+" TEXT, "+DATAAGENDA+" TEXT, "+HORAAGENDA+" TEXT)";
        db.execSQL(sqlCreateTableAgenda);
        //CRIAR TABELA GRAFICO
        String sqlCreateTableGrafico = "CREATE TABLE "+TABELAGRAFICO+"("+IDGRAFICO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EIXOXGRAFICO+" REAL, "+EIXOYGRAFICO+" REAL, "+HORARIOGRAFICO+" TEXT)";
        db.execSQL(sqlCreateTableGrafico);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //EXCLUIR TABELA ANOTACAO
        String sqlDropTableAnotacao = "DROP TABLE IF EXISTS "+TABELAANOTACAO;
        db.execSQL(sqlDropTableAnotacao);
        //EXCLUIR TABELA USUARIO
        String sqlDropTableUsuario = "DROP TABLE IF EXISTS "+TABELAUSUARIO;
        db.execSQL(sqlDropTableUsuario);
        //EXCLUIR TABELA DIETA
        String sqlDropTableDieta = "DROP TABLE IF EXISTS "+TABELADIETA;
        db.execSQL(sqlDropTableDieta);
        //EXCLUIR TABELA SINTOMAS
        String sqlDropTableSintomas = "DROP TABLE IF EXISTS "+TABELASINTOMAS;
        db.execSQL(sqlDropTableSintomas);
        //EXCLUIR TABELA MEDICAMENTOS
        String sqlDropTableMedicamentos = "DROP TABLE IF EXISTS "+TABELAMEDICAMENTOS;
        db.execSQL(sqlDropTableMedicamentos);
        //EXCLUIR TABELA AGENDA
        String sqlDropTableAgenda = "DROP TABLE IF EXISTS "+TABELAAGENDA;
        db.execSQL(sqlDropTableAgenda);
        //CHAMAR onCreate() PARA RECRIAR TABELAS E ATUALIZAR VERSAO DO BANCO
        VERSAO++;
        onCreate(db);
    }
}

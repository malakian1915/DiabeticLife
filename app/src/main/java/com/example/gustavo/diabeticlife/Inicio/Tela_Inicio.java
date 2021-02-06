package com.example.gustavo.diabeticlife.Inicio;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Alarme.Tela_Lembretes;
import com.example.gustavo.diabeticlife.Anotacoes.Tela_Anotacoes;
import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.Dados_Usuario.Tela_Alterar_Dados_Usuario;
import com.example.gustavo.diabeticlife.Dicas.Tela_Dicas;
import com.example.gustavo.diabeticlife.Dieta.Tela_Escolher_Tipo_Dieta;
import com.example.gustavo.diabeticlife.Grafico.Tela_Grafico;
import com.example.gustavo.diabeticlife.Medicamentos.Tela_Medicamentos;
import com.example.gustavo.diabeticlife.R;
import com.example.gustavo.diabeticlife.Relatorio.Relatorio;
import com.example.gustavo.diabeticlife.Sintomas.Tela_Sintomas;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class Tela_Inicio extends AppCompatActivity {

    ArrayList listaSintomas = new ArrayList();
    ArrayList listaMedicamentos = new ArrayList();
    ArrayList listaGrafico = new ArrayList();

    String nomeUsu, idadeUsu, tipoDiabUsu;
    int linhasSintomas = 0, linhasMedicamentos = 0, linhasGraficos = 0;

    private InterstitialAd mInterstitialAd;

    String tipoBotao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AdView ads = new AdView(this);
        ads.setAdSize(AdSize.SMART_BANNER);
        ads.setAdUnitId("ca-app-pub-1897388706744089/6031145452");

        RelativeLayout rl = (RelativeLayout)findViewById(R.id.rltInicio);
        rl.addView(ads);

        final AdRequest request = new AdRequest.Builder().build();
        ads.loadAd(request);

        AdView ads2 = (AdView)this.findViewById(R.id.adsInicio);
        ads2.loadAd(request);

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

    }

    //inicio dos metodos que chamam a tela qnd clica no botao
    public void abrirDieta(View v){
        //Intent abrirTelaDieta = new Intent(Tela_Inicio.this, Tela_Escolher_Tipo_Dieta.class);
        //startActivity(abrirTelaDieta);
        tipoBotao = "dieta";
        showInterstitial();
    }

    public void abrirLembretes(View v){
        //Intent abrirTelaLembretes = new Intent(Tela_Inicio.this, Tela_Lembretes.class);
        //startActivity(abrirTelaLembretes);
        tipoBotao = "alarme";
        showInterstitial();
    }

    public void abrirAnotacao(View v){
        //Intent abrirTelaAnotacao = new Intent(Tela_Inicio.this, Tela_Anotacoes.class);
        //startActivity(abrirTelaAnotacao);
        tipoBotao = "anotacao";
        showInterstitial();
    }

    public void abrirSintomas(View v){
        //Intent abrirTelaSintomas = new Intent(Tela_Inicio.this, Tela_Sintomas.class);
        //startActivity(abrirTelaSintomas);
        tipoBotao = "sintomas";
        showInterstitial();
    }

    public void abrirDicas(View v){
        //Intent abrirTelaDicas = new Intent(Tela_Inicio.this, Tela_Dicas.class);
        //startActivity(abrirTelaDicas);
        tipoBotao = "dicas";
        showInterstitial();
    }

    public void abrirGrafico(View v){
        //Intent abrirTelaGrafico = new Intent(Tela_Inicio.this, Tela_Grafico.class);
        //startActivity(abrirTelaGrafico);
        tipoBotao = "grafico";
        showInterstitial();
    }

    public void abrirRelatorio(View v){
        limparInformacoes();
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            AlertDialog.Builder msg = new AlertDialog.Builder(this);
            msg.setTitle("BAIXAR RELATÓRIO");
            msg.setMessage("Deseja baixar um relatório em forma PDF?\n" +
                    "(OBS: o relatório é gerado a partir de informações salvas no aplicativo pelo usuário)");
            msg.setNegativeButton("NÃO", null);
            msg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        montarParametroUsuario();
                        montarParametroSintomas();
                        montarParametroMedicamentos();
                        montarParametroGrafico();
                        String parametros = "?nome=" + nomeUsu + "&idade=" + idadeUsu + "&tipo=" + tipoDiabUsu + "&sintomas=" + listaSintomas + "&medicamentos=" + listaMedicamentos + "&glicemia=" + listaGrafico + "&linhasSintomas=" + linhasSintomas + "&linhasMedicamentos=" + linhasMedicamentos + "&linhasGlicemia=" + linhasGraficos;
                        String url = "http://dev-android.hol.es/GustavoPDF/GerarPDF/pdf.php" + parametros;
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);
                    } catch (Exception e){
                        Toast.makeText(Tela_Inicio.this, "Não foi possível conectar com o servidor", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            msg.show();
        }else{
            Toast.makeText(this, "Você precisa estar conectado a Internet para continuar nessa função", Toast.LENGTH_LONG).show();
        }
    }

    public void abriUsuario(View v){
        //Intent intent = new Intent(Tela_Inicio.this, Tela_Alterar_Dados_Usuario.class);
        //startActivity(intent);
        tipoBotao = "usuario";
        showInterstitial();
    }

    public void abriMedicamentos(View v){
        //Intent intent = new Intent(Tela_Inicio.this, Tela_Medicamentos.class);
        //startActivity(intent);
        tipoBotao = "medicamentos";
        showInterstitial();
    }
    //fim dos metodos que chamam a tela quando clica no botao

    public void montarParametroSintomas(){
        Relatorio relatorio = new Relatorio(getBaseContext());
        Cursor cursor = relatorio.pegarSintomas();
        if(cursor.getCount() != 0) {
            do{
                listaSintomas.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.HORARIOSINTOMAS)));
                listaSintomas.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.SINTOMAS)));
                listaSintomas.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.MAISSINTOMAS))+";");
                listaSintomas.add("\n");
                linhasSintomas++;
            }while (cursor.moveToNext());
        }
    }

    public void montarParametroUsuario(){
        Relatorio relatorio = new Relatorio(getBaseContext());
        Cursor cursor = relatorio.pegarDadosUsuario();
        nomeUsu = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.NOMEUSUARIO));
        idadeUsu = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDADEUSUARIO));
        tipoDiabUsu = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.TIPODIABETES));
    }

    public void montarParametroMedicamentos(){
        Relatorio relatorio = new Relatorio(getBaseContext());
        Cursor cursor = relatorio.pegarMedicamentos();
        if(cursor.getCount() != 0) {
            do{
                listaMedicamentos.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.DATAMEDICAMENTOS)));
                listaMedicamentos.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.HORAMEDICAMENTOS)));
                listaMedicamentos.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.MEDICAMENTOS)));
                listaMedicamentos.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.MAISMEDICAMENTOS))+";");
                listaMedicamentos.add("\n");
                linhasMedicamentos++;
            }while (cursor.moveToNext());
        }
    }

    public void montarParametroGrafico(){
        Relatorio relatorio = new Relatorio(getBaseContext());
        Cursor cursor = relatorio.pegarGrafico();
        if(cursor.getCount() != 0) {
            do{
                listaGrafico.add(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.HORARIOGRAFICO))+".");
                listaGrafico.add(" Nível de Glicemia: "+cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.EIXOYGRAFICO))+";");
                listaGrafico.add("\n");
                linhasGraficos++;
            }while (cursor.moveToNext());
        }
    }

    public void limparInformacoes(){
        linhasSintomas = 0;
        linhasMedicamentos = 0;
        linhasGraficos = 0;
        listaSintomas.clear();
        listaMedicamentos.clear();
        listaGrafico.clear();
        nomeUsu = null;
        idadeUsu = null;
        tipoDiabUsu = null;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    //ANUNCIO INTERSTITAL
    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-1897388706744089/7277219453");
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                verTipoBotao();
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            verTipoBotao();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    private void verTipoBotao(){
        if(tipoBotao.equals("dieta")){
            Intent abrirTelaDieta = new Intent(Tela_Inicio.this, Tela_Escolher_Tipo_Dieta.class);
            startActivity(abrirTelaDieta);
        } else if(tipoBotao.equals("alarme")){
            Intent abrirTelaLembretes = new Intent(Tela_Inicio.this, Tela_Lembretes.class);
            startActivity(abrirTelaLembretes);
        } else if(tipoBotao.equals("anotacao")){
            Intent abrirTelaAnotacao = new Intent(Tela_Inicio.this, Tela_Anotacoes.class);
            startActivity(abrirTelaAnotacao);
        } else if(tipoBotao.equals("sintomas")){
            Intent abrirTelaSintomas = new Intent(Tela_Inicio.this, Tela_Sintomas.class);
            startActivity(abrirTelaSintomas);
        } else if(tipoBotao.equals("dicas")){
            Intent abrirTelaDicas = new Intent(Tela_Inicio.this, Tela_Dicas.class);
            startActivity(abrirTelaDicas);
        } else if(tipoBotao.equals("grafico")){
            Intent abrirTelaGrafico = new Intent(Tela_Inicio.this, Tela_Grafico.class);
            startActivity(abrirTelaGrafico);
        } else if(tipoBotao.equals("medicamentos")){
            Intent intent = new Intent(Tela_Inicio.this, Tela_Medicamentos.class);
            startActivity(intent);
        } else if(tipoBotao.equals("usuario")){
            Intent intent = new Intent(Tela_Inicio.this, Tela_Alterar_Dados_Usuario.class);
            startActivity(intent);
        }
    }

    //MENU TIRAR ANUNCIOS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tela__inicio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.mniAnuncio) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.gustavo.diabetes_mellitus"));
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
package com.example.gustavo.diabeticlife.Dieta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gustavo.diabeticlife.R;

public class Tela_Escolher_Tipo_Dieta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__escolher__tipo__dieta);
    }

    public void abrirCafeDaManha(View v){
        Intent abrirCafe = new Intent(Tela_Escolher_Tipo_Dieta.this, Tela_Dieta.class);
        String txt = "Café da manhã";
        Bundle bundle = new Bundle();
        bundle.putString("txt", txt);
        abrirCafe.putExtras(bundle);
        startActivity(abrirCafe);
        finish();
    }

    public void abrirAlmoco(View v){
        Intent abrirAlmoco = new Intent(Tela_Escolher_Tipo_Dieta.this, Tela_Dieta.class);
        String txt = "Almoço";
        Bundle bundle = new Bundle();
        bundle.putString("txt", txt);
        abrirAlmoco.putExtras(bundle);
        startActivity(abrirAlmoco);
        finish();
    }

    public void abrirLancheDaTarde(View v){
        Intent abrirLancheDaTarde = new Intent(Tela_Escolher_Tipo_Dieta.this, Tela_Dieta.class);
        String txt = "Lanche da tarde";
        Bundle bundle = new Bundle();
        bundle.putString("txt", txt);
        abrirLancheDaTarde.putExtras(bundle);
        startActivity(abrirLancheDaTarde);
        finish();
    }

    public void abrirJantar(View v){
        Intent abrirJantar = new Intent(Tela_Escolher_Tipo_Dieta.this, Tela_Dieta.class);
        String txt = "Jantar";
        Bundle bundle = new Bundle();
        bundle.putString("txt", txt);
        abrirJantar.putExtras(bundle);
        startActivity(abrirJantar);
        finish();
    }

    public void abrirLancheDaNoite(View v){
        Intent abrirLancheDaNoite = new Intent(Tela_Escolher_Tipo_Dieta.this, Tela_Dieta.class);
        String txt = "Lanche da noite";
        Bundle bundle = new Bundle();
        bundle.putString("txt", txt);
        abrirLancheDaNoite.putExtras(bundle);
        startActivity(abrirLancheDaNoite);
        finish();
    }

    public void abrirDietasSalvas(View v){
        Intent abrirDietasSalvas = new Intent(Tela_Escolher_Tipo_Dieta.this, Tela_Ver_Dietas.class);
        startActivity(abrirDietasSalvas);
        finish();
    }

}

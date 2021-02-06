package com.example.gustavo.diabeticlife.Sintomas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.R;

public class Tela_Sintomas extends AppCompatActivity {

    CheckBox chkAcantose, chkVisaoTurva, chkAumentoDePeso, chkBocaDoce, chkFadiga, chkFeridas, chkFome,
             chkFormigamento, chkInfeccao, chkHumor, chkVomitos, chkNervosismo, chkPerdaDePeso, chkSede,
             chkTontura, chkUrinar;
    EditText edtMaisSintomas;
    Button btnSalvarSintomas, btnVerSintomas;

    String[] sintomas = {"","","","","","","","","","","","","","","",""};

    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__sintomas);

        chkAcantose = (CheckBox)findViewById(R.id.chkAcantose);
        chkVisaoTurva = (CheckBox)findViewById(R.id.chkAlteracaoVisual);
        chkAumentoDePeso = (CheckBox)findViewById(R.id.chkAumentoDePeso);
        chkBocaDoce = (CheckBox)findViewById(R.id.chkBocaDoce);
        chkFadiga = (CheckBox)findViewById(R.id.chkFadiga);
        chkFeridas = (CheckBox)findViewById(R.id.chkFeridasDemoradasCicatrizar);
        chkFome = (CheckBox)findViewById(R.id.chkFome);
        chkFormigamento = (CheckBox)findViewById(R.id.chkFormigamentoEFurunculo);
        chkInfeccao = (CheckBox)findViewById(R.id.chkInfeccoes);
        chkHumor = (CheckBox)findViewById(R.id.chkMudancasDeHumor);
        chkVomitos = (CheckBox)findViewById(R.id.chkVomitos);
        chkNervosismo = (CheckBox)findViewById(R.id.chkNervosismo);
        chkPerdaDePeso = (CheckBox)findViewById(R.id.chkPerdaDePeso);
        chkSede = (CheckBox)findViewById(R.id.chkSede);
        chkTontura = (CheckBox)findViewById(R.id.chkTontura);
        chkUrinar = (CheckBox)findViewById(R.id.chkUrinar);

        edtMaisSintomas = (EditText)findViewById(R.id.edtMaisSintomas);
        btnSalvarSintomas = (Button)findViewById(R.id.btnSalvarSintomas);
        btnVerSintomas = (Button)findViewById(R.id.btnVerSintomas);

        verificaCheckBox();

        btnSalvarSintomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont == 0 && edtMaisSintomas.getText().toString().isEmpty()) {
                    Toast.makeText(Tela_Sintomas.this, "Selecione ou escreva um sintoma para salvar", Toast.LENGTH_SHORT).show();
                } else{
                    BancoControllerSintomas crud = new BancoControllerSintomas(getBaseContext());
                    StringBuilder strSintomas = new StringBuilder();
                    strSintomas.append(sintomas[0] + sintomas[1] + sintomas[2] + sintomas[3] + sintomas[4] + sintomas[5] + sintomas[6] + sintomas[7] + sintomas[8] + sintomas[9] + sintomas[10] + sintomas[11] + sintomas[12] + sintomas[13] + sintomas[14] + sintomas[15]);
                    String horario = android.text.format.DateFormat.format("dd/MM/yy          hh:mm a", new java.util.Date()).toString();
                    String maisSintomas = edtMaisSintomas.getText().toString();
                    String resultado;
                    resultado = crud.cadastrarSintomas(strSintomas.toString(), horario, maisSintomas);
                    Toast.makeText(Tela_Sintomas.this, resultado, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        btnVerSintomas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verSintomas = new Intent(Tela_Sintomas.this, Tela_Ver_Sintomas.class);
                startActivity(verSintomas);
            }
        });

    }

    public void verificaCheckBox(){
        chkAcantose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAcantose.isChecked()) {
                    chkAcantose.setBackgroundColor(Color.LTGRAY);
                    sintomas[0] = "Pele escureçida. ";
                    cont++;
                }else{
                    chkAcantose.setBackgroundColor(Color.WHITE);
                    sintomas[0] = "";
                    cont--;
                }
            }
        });
        chkVisaoTurva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkVisaoTurva.isChecked()) {
                    chkVisaoTurva.setBackgroundColor(Color.LTGRAY);
                    sintomas[1] = "Visão turva. ";
                    cont++;
                }else{
                    chkVisaoTurva.setBackgroundColor(Color.WHITE);
                    sintomas[1] = "";
                    cont--;
                }
            }
        });
        chkAumentoDePeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAumentoDePeso.isChecked()){
                    chkAumentoDePeso.setBackgroundColor(Color.LTGRAY);
                    sintomas[2] = "Aumento de peso. ";
                    cont++;
                }else{
                    chkAumentoDePeso.setBackgroundColor(Color.WHITE);
                    sintomas[2] = "";
                    cont--;
                }
            }
        });
        chkBocaDoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkBocaDoce.isChecked()){
                    chkBocaDoce.setBackgroundColor(Color.LTGRAY);
                    sintomas[3] = "Boca doce. ";
                    cont++;
                }else{
                    chkBocaDoce.setBackgroundColor(Color.WHITE);
                    sintomas[3] = "";
                    cont--;
                }
            }
        });
        chkFadiga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFadiga.isChecked()){
                    chkFadiga.setBackgroundColor(Color.LTGRAY);
                    sintomas[4] = "Fadiga. ";
                    cont++;
                }else{
                    chkFadiga.setBackgroundColor(Color.WHITE);
                    sintomas[4] = "";
                    cont--;
                }
            }
        });
        chkFeridas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFeridas.isChecked()){
                    chkFeridas.setBackgroundColor(Color.LTGRAY);
                    sintomas[5] = "Cicatrização demorada. ";
                    cont++;
                }else{
                    chkFeridas.setBackgroundColor(Color.WHITE);
                    sintomas[5] = "";
                    cont--;
                }
            }
        });
        chkFome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFome.isChecked()){
                    chkFome.setBackgroundColor(Color.LTGRAY);
                    sintomas[6] = "Fome frequentemente. ";
                    cont++;
                }else{
                    chkFome.setBackgroundColor(Color.WHITE);
                    sintomas[6] = "";
                    cont--;
                }
            }
        });
        chkFormigamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFormigamento.isChecked()){
                    chkFormigamento.setBackgroundColor(Color.LTGRAY);
                    sintomas[7] = "Formigamentos e furúnculos. ";
                    cont++;
                }else{
                    chkFormigamento.setBackgroundColor(Color.WHITE);
                    sintomas[7] = "";
                    cont--;
                }
            }
        });
        chkInfeccao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkInfeccao.isChecked()){
                    chkInfeccao.setBackgroundColor(Color.LTGRAY);
                    sintomas[8] = "Infecções constantes. ";
                    cont++;
                }else{
                    chkInfeccao.setBackgroundColor(Color.WHITE);
                    sintomas[8] = "";
                    cont--;
                }
            }
        });
        chkHumor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkHumor.isChecked()){
                    chkHumor.setBackgroundColor(Color.LTGRAY);
                    sintomas[9] = "Mudanças de humor. ";
                    cont++;
                }else{
                    chkHumor.setBackgroundColor(Color.WHITE);
                    sintomas[9] = "";
                    cont--;
                }
            }
        });
        chkVomitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkVomitos.isChecked()){
                    chkVomitos.setBackgroundColor(Color.LTGRAY);
                    sintomas[10] = "Náuseas e vômitos. ";
                    cont++;
                }else{
                    chkVomitos.setBackgroundColor(Color.WHITE);
                    sintomas[10] = "";
                    cont--;
                }
            }
        });
        chkNervosismo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkNervosismo.isChecked()){
                    chkNervosismo.setBackgroundColor(Color.LTGRAY);
                    sintomas[11] = "Nervosismo. ";
                    cont++;
                }else{
                    chkNervosismo.setBackgroundColor(Color.WHITE);
                    sintomas[11] = "";
                    cont--;
                }
            }
        });
        chkPerdaDePeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkPerdaDePeso.isChecked()){
                    chkPerdaDePeso.setBackgroundColor(Color.LTGRAY);
                    sintomas[12] = "Perda de peso. ";
                    cont++;
                }else{
                    chkPerdaDePeso.setBackgroundColor(Color.WHITE);
                    sintomas[12] = "";
                    cont--;
                }
            }
        });
        chkSede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSede.isChecked()){
                    chkSede.setBackgroundColor(Color.LTGRAY);
                    sintomas[13] = "Sede frequentemente. ";
                    cont++;
                }else{
                    chkSede.setBackgroundColor(Color.WHITE);
                    sintomas[13] = "";
                    cont--;
                }
            }
        });
        chkTontura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkTontura.isChecked()){
                    chkTontura.setBackgroundColor(Color.LTGRAY);
                    sintomas[14] = "Tontura. ";
                    cont++;
                }else{
                    chkTontura.setBackgroundColor(Color.WHITE);
                    sintomas[14] = "";
                    cont--;
                }
            }
        });
        chkUrinar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkUrinar.isChecked()){
                    chkUrinar.setBackgroundColor(Color.LTGRAY);
                    sintomas[15] = "Urinar frequentemente.";
                    cont++;
                }else{
                    chkUrinar.setBackgroundColor(Color.WHITE);
                    sintomas[15] = "";
                    cont--;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}

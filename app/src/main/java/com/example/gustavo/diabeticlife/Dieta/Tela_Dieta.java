package com.example.gustavo.diabeticlife.Dieta;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.R;

public class Tela_Dieta extends AppCompatActivity {

    CheckBox chkMaca, chkMorango, chkCereja, chkAbacate, chkBlueberry, chkLaranja, chkLimao, chkAmeixa, chkTomate,
             chkAbacaxi, chkPessego, chkMelao, chkMamao, chkMelancia, chkBanana, chkManga, chkMexerica, chkAcerola,
             chkGoiaba, chkKiwi, chkCarnePorcoAssada, chkLomboPorcoCozido, chkPresunto, chkArenque, chkFigadoGalinha,
             chkOstras, chkSalmaoCozido, chkSardinhas, chkOvoCozido, chkFrangoAssado, chkAmendoim, chkCastanhaCaju,
             chkCastanhaPara, chkFarinhaTrigoIntegral, chkFlocosCereais, chkMilho, chkGermemTrigo, chkSementeGirassol,
             chkLeiteNinho, chkArroz, chkFeijao, chkAgriao, chkAspargos, chkBrocolis, chkCouveVerde, chkEspinafre, chkRepolho,
             chkRucula, chkAlface, chkCenoura, chkPepino, chkAlho, chkCebola, chkRabanete, chkBatata, chkPaoFrances,
             chkPaoIntegral, chkTorrada, chkOmelete, chkQueijoBranco, chkEspaguete, chkBatataDoce, chkAgua, chkSucos,
             chkVitaminas, chkAguaCoco, chkLeiteDesnatado, chkCafe, chkCha, chkIogurte;
    EditText edtMaisAlimentos;
    Button btnSalvar;

    String[] frutas = {"","","","","","","","","","","","","","","","","","","",""};
    String[] carnes = {"","","","","","","","","",""};
    String[] cereais = {"","","","","","","","","","",""};
    String[] verduras = {"","","","","","","","","","","","","",""};
    String[] outros = {"","","","","","",""};
    String[] bebidas = {"","","","","","","",""};

    int day, month, year, hr, min;
    String data, horario;
    String tipo_da_dieta;

    int cont = 0;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__dieta);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String tipo_dieta = bundle.getString("txt");
        this.tipo_da_dieta = tipo_dieta;

        chkMaca = (CheckBox)findViewById(R.id.chkMaca);
        chkMorango = (CheckBox)findViewById(R.id.chkMorango);
        chkCereja = (CheckBox)findViewById(R.id.chkCereja);
        chkAbacate = (CheckBox)findViewById(R.id.chkAbacate);
        chkBlueberry = (CheckBox)findViewById(R.id.chkBlueberry);
        chkLaranja = (CheckBox)findViewById(R.id.chkLaranja);
        chkLimao = (CheckBox)findViewById(R.id.chkLimao);
        chkAmeixa = (CheckBox)findViewById(R.id.chkAmeixa);
        chkTomate = (CheckBox)findViewById(R.id.chkTomate);
        chkAbacaxi = (CheckBox)findViewById(R.id.chkAbacaxi);
        chkPessego = (CheckBox)findViewById(R.id.chkPessego);
        chkMelao = (CheckBox)findViewById(R.id.chkMelao);
        chkMamao = (CheckBox)findViewById(R.id.chkMamao);
        chkMelancia = (CheckBox)findViewById(R.id.chkMelancia);
        chkBanana = (CheckBox)findViewById(R.id.chkBanana);
        chkManga = (CheckBox)findViewById(R.id.chkManga);
        chkMexerica = (CheckBox)findViewById(R.id.chkMexerica);
        chkAcerola = (CheckBox)findViewById(R.id.chkAcerola);
        chkGoiaba = (CheckBox)findViewById(R.id.chkGoiaba);
        chkKiwi = (CheckBox)findViewById(R.id.chkKiwi);
        chkCarnePorcoAssada = (CheckBox)findViewById(R.id.chkCarneDePorcoAssada);
        chkLomboPorcoCozido = (CheckBox)findViewById(R.id.chkLomboDePorcoCozido);
        chkPresunto = (CheckBox)findViewById(R.id.chkPresuntoDefumadoCru);
        chkArenque = (CheckBox)findViewById(R.id.chkArenqueFresco);
        chkFigadoGalinha = (CheckBox)findViewById(R.id.chkFigadoDeGalinha);
        chkOstras = (CheckBox)findViewById(R.id.chkOstrasCrua);
        chkSalmaoCozido = (CheckBox)findViewById(R.id.chkSalmaoCozido);
        chkSardinhas = (CheckBox)findViewById(R.id.chkSardinhasEnlatadas);
        chkOvoCozido = (CheckBox)findViewById(R.id.chkOvoCozido);
        chkFrangoAssado = (CheckBox)findViewById(R.id.chkFrangoAssado);
        chkAmendoim = (CheckBox)findViewById(R.id.chkAmendoim);
        chkCastanhaCaju = (CheckBox)findViewById(R.id.chkCastanhaDeCajuTorrada);
        chkCastanhaPara = (CheckBox)findViewById(R.id.chkCastanhaDoPara);
        chkFarinhaTrigoIntegral = (CheckBox)findViewById(R.id.chkFarinhaDeTrigoIntegral);
        chkFlocosCereais = (CheckBox)findViewById(R.id.chkFlocosDeCereais);
        chkMilho = (CheckBox)findViewById(R.id.chkMilho);
        chkGermemTrigo = (CheckBox)findViewById(R.id.chkGermemDoTrigo);
        chkSementeGirassol = (CheckBox)findViewById(R.id.chkSementeDeGirasol);
        chkLeiteNinho = (CheckBox)findViewById(R.id.chkLeiteNinhoFortificado);
        chkArroz = (CheckBox)findViewById(R.id.chkArroz);
        chkFeijao = (CheckBox)findViewById(R.id.chkFeijao);
        chkAgriao = (CheckBox)findViewById(R.id.chkAgriao);
        chkAspargos = (CheckBox)findViewById(R.id.chkAspargos);
        chkBrocolis = (CheckBox)findViewById(R.id.chkBrocolis);
        chkCouveVerde = (CheckBox)findViewById(R.id.chkCouveVerde);
        chkEspinafre = (CheckBox)findViewById(R.id.chkEspinafre);
        chkRepolho = (CheckBox)findViewById(R.id.chkRepolho);
        chkRucula = (CheckBox)findViewById(R.id.chkRucula);
        chkAlface = (CheckBox)findViewById(R.id.chkAlface);
        chkCenoura = (CheckBox)findViewById(R.id.chkCenoura);
        chkPepino = (CheckBox)findViewById(R.id.chkPepino);
        chkAlho = (CheckBox)findViewById(R.id.chkAlho);
        chkCebola = (CheckBox)findViewById(R.id.chkCebola);
        chkRabanete = (CheckBox)findViewById(R.id.chkRabanete);
        chkBatata = (CheckBox)findViewById(R.id.chkBatata);
        chkPaoFrances = (CheckBox)findViewById(R.id.chkPaoFrances);
        chkPaoIntegral = (CheckBox)findViewById(R.id.chkPaoIntegral);
        chkTorrada = (CheckBox)findViewById(R.id.chkTorrada);
        chkOmelete = (CheckBox)findViewById(R.id.chkOmelete);
        chkQueijoBranco = (CheckBox)findViewById(R.id.chkQueijoBranco);
        chkEspaguete = (CheckBox)findViewById(R.id.chkEspaguete);
        chkBatataDoce = (CheckBox)findViewById(R.id.chkBatataDoce);
        chkAgua = (CheckBox)findViewById(R.id.chkAgua);
        chkSucos = (CheckBox)findViewById(R.id.chkSucosNaturais);
        chkVitaminas = (CheckBox)findViewById(R.id.chkVitaminasDeFrutas);
        chkAguaCoco = (CheckBox)findViewById(R.id.chkAguaDeCoco);
        chkLeiteDesnatado = (CheckBox)findViewById(R.id.chkLeiteDesnatado);
        chkCafe = (CheckBox)findViewById(R.id.chkCafePretoDescafeinado);
        chkCha = (CheckBox)findViewById(R.id.chkChaSemAcucar);
        chkIogurte = (CheckBox)findViewById(R.id.chkIogurteDesnatado);

        edtMaisAlimentos = (EditText)findViewById(R.id.edtMaisAlimentos);
        btnSalvar = (Button)findViewById(R.id.btnSalvarDieta);

        verificaCheckBox();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont == 0 && edtMaisAlimentos.getText().toString().isEmpty()){
                    Toast.makeText(Tela_Dieta.this, "Selecione ou escreva um alimento para salvar", Toast.LENGTH_SHORT).show();
                } else {
                    pegarDataDieta();
                }
            }
        });

    }

    public void pegarDataDieta(){
        AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Dieta.this);
        final DatePicker datePicker = new DatePicker(Tela_Dieta.this);
        msg.setView(datePicker);
        msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth()+1;
                year = datePicker.getYear();
                data = day+"/"+month+"/"+year;
                pegarHoraDieta();
            }
        });
        msg.setNegativeButton("CANCELAR", null);
        msg.show();
    }

    public void pegarHoraDieta(){
        AlertDialog.Builder mess = new AlertDialog.Builder(Tela_Dieta.this);
        final TimePicker timePicker = new TimePicker(Tela_Dieta.this);
        timePicker.setIs24HourView(true);
        mess.setView(timePicker);
        mess.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hr = timePicker.getCurrentHour();
                min = timePicker.getCurrentMinute();
                horario = hr+":"+min;
                salvarDieta();
            }
        });
        mess.setNegativeButton("CANCELAR", null);
        mess.show();
    }

    public void salvarDieta(){
        BancoControllerDieta crud = new BancoControllerDieta(getBaseContext());
        StringBuilder strFrutas = new StringBuilder();
        StringBuilder strCarnes = new StringBuilder();
        StringBuilder strCereais = new StringBuilder();
        StringBuilder strVerduras = new StringBuilder();
        StringBuilder strOutros = new StringBuilder();
        StringBuilder strBebidas = new StringBuilder();
        strFrutas.append(frutas[0] + frutas[1] + frutas[2] + frutas[3] + frutas[4] + frutas[5] + frutas[6] + frutas[7] + frutas[8] + frutas[9] + frutas[10] + frutas[11] + frutas[12] + frutas[13] + frutas[14] + frutas[15] + frutas[16] + frutas[17] + frutas[18] + frutas[19]);
        strCarnes.append(carnes[0] + carnes[1] + carnes[2] + carnes[3] + carnes[4] + carnes[5] + carnes[6] + carnes[7] + carnes[8] + carnes[9]);
        strCereais.append(cereais[0] + cereais[1] + cereais[2] + cereais[3] + cereais[4] + cereais[5] + cereais[6] + cereais[7] + cereais[8] + cereais[9] + cereais[10]);
        strVerduras.append(verduras[0] + verduras[1] + verduras[2] + verduras[3] + verduras[4] + verduras[5] + verduras[6] + verduras[7] + verduras[8] + verduras[9] + verduras[10] + verduras[11] + verduras[12] + verduras[13]);
        strOutros.append(outros[0] + outros[1] + outros[2] + outros[3] + outros[4] + outros[5] + outros[6]);
        strBebidas.append(bebidas[0] + bebidas[1] + bebidas[2] + bebidas[3] + bebidas[4] + bebidas[5] + bebidas[6] + bebidas[7]);
        String maisAlimentos = edtMaisAlimentos.getText().toString();
        String resultado;
        resultado = crud.cadastrarDieta(tipo_da_dieta, strFrutas.toString(), strCarnes.toString(), strCereais.toString(), strVerduras.toString(), strOutros.toString(), strBebidas.toString(), maisAlimentos, data, horario);
        Toast.makeText(Tela_Dieta.this, resultado, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void verificaCheckBox(){
        chkMaca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMaca.isChecked()) {
                    chkMaca.setBackgroundColor(Color.LTGRAY);
                    frutas[0] = "Maçã. ";
                    cont++;
                }else{
                    chkMaca.setBackgroundColor(Color.WHITE);
                    frutas[0] = "";
                    cont--;
                }
            }
        });
        chkMorango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMorango.isChecked()) {
                    chkMorango.setBackgroundColor(Color.LTGRAY);
                    frutas[1] = "Morango. ";
                    cont++;
                }else{
                    chkMorango.setBackgroundColor(Color.WHITE);
                    frutas[1] = "";
                    cont--;
                }
            }
        });
        chkCereja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCereja.isChecked()){
                    chkCereja.setBackgroundColor(Color.LTGRAY);
                    frutas[2] = "Cereja. ";
                    cont++;
                }else{
                    chkCereja.setBackgroundColor(Color.WHITE);
                    frutas[2] = "";
                    cont--;
                }
            }
        });
        chkAbacate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAbacate.isChecked()){
                    chkAbacate.setBackgroundColor(Color.LTGRAY);
                    frutas[3] = "Abacate. ";
                    cont++;
                }else{
                    chkAbacate.setBackgroundColor(Color.WHITE);
                    frutas[3] = "";
                    cont--;
                }
            }
        });
        chkBlueberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkBlueberry.isChecked()){
                    chkBlueberry.setBackgroundColor(Color.LTGRAY);
                    frutas[4] = "Blueberry. ";
                    cont++;
                }else{
                    chkBlueberry.setBackgroundColor(Color.WHITE);
                    frutas[4] = "";
                    cont--;
                }
            }
        });
        chkLaranja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkLaranja.isChecked()){
                    chkLaranja.setBackgroundColor(Color.LTGRAY);
                    frutas[5] = "Laranja. ";
                    cont++;
                }else{
                    chkLaranja.setBackgroundColor(Color.WHITE);
                    frutas[5] = "";
                    cont--;
                }
            }
        });
        chkLimao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkLimao.isChecked()){
                    chkLimao.setBackgroundColor(Color.LTGRAY);
                    frutas[6] = "Limão. ";
                    cont++;
                }else{
                    chkLimao.setBackgroundColor(Color.WHITE);

                    cont--;
                }
            }
        });
        chkAmeixa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAmeixa.isChecked()){
                    chkAmeixa.setBackgroundColor(Color.LTGRAY);
                    frutas[7] = "Ameixa. ";
                    cont++;
                }else{
                    chkAmeixa.setBackgroundColor(Color.WHITE);
                    frutas[7] = "";
                    cont--;
                }
            }
        });
        chkTomate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkTomate.isChecked()){
                    chkTomate.setBackgroundColor(Color.LTGRAY);
                    frutas[8] = "Tomate. ";
                    cont++;
                }else{
                    chkTomate.setBackgroundColor(Color.WHITE);
                    frutas[8] = "";
                    cont--;
                }
            }
        });
        chkAbacaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAbacaxi.isChecked()){
                    chkAbacaxi.setBackgroundColor(Color.LTGRAY);
                    frutas[9] = "Abacaxi. ";
                    cont++;
                }else{
                    chkAbacaxi.setBackgroundColor(Color.WHITE);
                    frutas[9] = "";
                    cont--;
                }
            }
        });
        chkPessego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkPessego.isChecked()){
                    chkPessego.setBackgroundColor(Color.LTGRAY);
                    frutas[10] = "Pessêgo. ";
                    cont++;
                }else{
                    chkPessego.setBackgroundColor(Color.WHITE);
                    frutas[10] = "";
                    cont--;
                }
            }
        });
        chkMelao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMelao.isChecked()){
                    chkMelao.setBackgroundColor(Color.LTGRAY);
                    frutas[11] = "Melão. ";
                    cont++;
                }else{
                    chkMelao.setBackgroundColor(Color.WHITE);
                    frutas[11] = "";
                    cont--;
                }
            }
        });
        chkMamao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMamao.isChecked()){
                    chkMamao.setBackgroundColor(Color.LTGRAY);
                    frutas[12] = "Mamão. ";
                    cont++;
                }else{
                    chkMamao.setBackgroundColor(Color.WHITE);
                    frutas[12] = "";
                    cont--;
                }
            }
        });
        chkMelancia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMelancia.isChecked()){
                    chkMelancia.setBackgroundColor(Color.LTGRAY);
                    frutas[13] = "Melancia. ";
                    cont++;
                }else{
                    chkMelancia.setBackgroundColor(Color.WHITE);
                    frutas[13] = "";
                    cont--;
                }
            }
        });
        chkBanana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkBanana.isChecked()){
                    chkBanana.setBackgroundColor(Color.LTGRAY);
                    frutas[14] = "Banana. ";
                    cont++;
                }else{
                    chkBanana.setBackgroundColor(Color.WHITE);
                    frutas[14] = "";
                    cont--;
                }
            }
        });
        chkManga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkManga.isChecked()){
                    chkManga.setBackgroundColor(Color.LTGRAY);
                    frutas[15] = "Manga. ";
                    cont++;
                }else{
                    chkManga.setBackgroundColor(Color.WHITE);
                    frutas[15] = "";
                    cont--;
                }
            }
        });
        chkMexerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMexerica.isChecked()){
                    chkMexerica.setBackgroundColor(Color.LTGRAY);
                    frutas[16] = "Mexerica. ";
                    cont++;
                }else{
                    chkMexerica.setBackgroundColor(Color.WHITE);
                    frutas[16] = "";
                    cont--;
                }
            }
        });
        chkAcerola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAcerola.isChecked()){
                    chkAcerola.setBackgroundColor(Color.LTGRAY);
                    frutas[17] = "Acerola. ";
                    cont++;
                }else{
                    chkAcerola.setBackgroundColor(Color.WHITE);
                    frutas[17] = "";
                    cont--;
                }
            }
        });
        chkGoiaba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkGoiaba.isChecked()){
                    chkGoiaba.setBackgroundColor(Color.LTGRAY);
                    frutas[18] = "Goiaba. ";
                    cont++;
                }else{
                    chkGoiaba.setBackgroundColor(Color.WHITE);
                    frutas[18] = "";
                    cont--;
                }
            }
        });
        chkKiwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkKiwi.isChecked()){
                    chkKiwi.setBackgroundColor(Color.LTGRAY);
                    frutas[19] = "Kiwi.";
                    cont++;
                }else{
                    chkKiwi.setBackgroundColor(Color.WHITE);
                    frutas[19] = "";
                    cont--;
                }
            }
        });
        chkCarnePorcoAssada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCarnePorcoAssada.isChecked()){
                    chkCarnePorcoAssada.setBackgroundColor(Color.LTGRAY);
                    carnes[0] = "Carne de porco assada. ";
                    cont++;
                }else{
                    chkCarnePorcoAssada.setBackgroundColor(Color.WHITE);
                    carnes[0] = "";
                    cont--;
                }
            }
        });
        chkLomboPorcoCozido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkLomboPorcoCozido.isChecked()){
                    chkLomboPorcoCozido.setBackgroundColor(Color.LTGRAY);
                    carnes[1] = "Lombo de porco cozido. ";
                    cont++;
                }else{
                    chkLomboPorcoCozido.setBackgroundColor(Color.WHITE);
                    carnes[1] = "";
                    cont--;
                }
            }
        });
        chkPresunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkPresunto.isChecked()){
                    chkPresunto.setBackgroundColor(Color.LTGRAY);
                    carnes[2] = "Presunto defumado cru. ";
                    cont++;
                }else{
                    chkPresunto.setBackgroundColor(Color.WHITE);
                    carnes[2] = "";
                    cont--;
                }
            }
        });
        chkArenque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkArenque.isChecked()){
                    chkArenque.setBackgroundColor(Color.LTGRAY);
                    carnes[3] = "Arenque fresco. ";
                    cont++;
                }else{
                    chkArenque.setBackgroundColor(Color.WHITE);
                    carnes[3] = "";
                    cont--;
                }
            }
        });
        chkFigadoGalinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFigadoGalinha.isChecked()){
                    chkFigadoGalinha.setBackgroundColor(Color.LTGRAY);
                    carnes[4] = "Figado de galinha. ";
                    cont++;
                }else{
                    chkFigadoGalinha.setBackgroundColor(Color.WHITE);
                    carnes[4] = "";
                    cont--;
                }
            }
        });
        chkOstras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkOstras.isChecked()){
                    chkOstras.setBackgroundColor(Color.LTGRAY);
                    carnes[5] = "Ostras cruas. ";
                    cont++;
                }else{
                    chkOstras.setBackgroundColor(Color.WHITE);
                    carnes[5] = "";
                    cont--;
                }
            }
        });
        chkSalmaoCozido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSalmaoCozido.isChecked()){
                    chkSalmaoCozido.setBackgroundColor(Color.LTGRAY);
                    carnes[6] = "Salmão cozido. ";
                    cont++;
                }else{
                    chkSalmaoCozido.setBackgroundColor(Color.WHITE);
                    carnes[6] = "";
                    cont--;
                }
            }
        });
        chkSardinhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSardinhas.isChecked()){
                    chkSardinhas.setBackgroundColor(Color.LTGRAY);
                    carnes[7] = "Sardinhas enlatadas. ";
                    cont++;
                }else{
                    chkSardinhas.setBackgroundColor(Color.WHITE);
                    carnes[7] = "";
                    cont--;
                }
            }
        });
        chkOvoCozido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkOvoCozido.isChecked()){
                    chkOvoCozido.setBackgroundColor(Color.LTGRAY);
                    carnes[8] = "Ovo cozido. ";
                    cont++;
                }else{
                    chkOvoCozido.setBackgroundColor(Color.WHITE);
                    carnes[8] = "";
                    cont--;
                }
            }
        });
        chkFrangoAssado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFrangoAssado.isChecked()){
                    chkFrangoAssado.setBackgroundColor(Color.LTGRAY);
                    carnes[9] = "Frango assado.";
                    cont++;
                }else{
                    chkFrangoAssado.setBackgroundColor(Color.WHITE);
                    carnes[9] = "";
                    cont--;
                }
            }
        });
        chkAmendoim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAmendoim.isChecked()){
                    chkAmendoim.setBackgroundColor(Color.LTGRAY);
                    cereais[0] = "Amendoim. ";
                    cont++;
                }else{
                    chkAmendoim.setBackgroundColor(Color.WHITE);
                    cereais[0] = "";
                    cont--;
                }
            }
        });
        chkCastanhaCaju.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCastanhaCaju.isChecked()){
                    chkCastanhaCaju.setBackgroundColor(Color.LTGRAY);
                    cereais[1] = "Castanha de caju torrada. ";
                    cont++;
                }else{
                    chkCastanhaCaju.setBackgroundColor(Color.WHITE);
                    cereais[1] = "";
                    cont--;
                }
            }
        });
        chkCastanhaPara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCastanhaPara.isChecked()){
                    chkCastanhaPara.setBackgroundColor(Color.LTGRAY);
                    cereais[2] = "Castanha do Pará. ";
                    cont++;
                }else{
                    chkCastanhaPara.setBackgroundColor(Color.WHITE);
                    cereais[2] = "";
                    cont--;
                }
            }
        });
        chkFarinhaTrigoIntegral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFarinhaTrigoIntegral.isChecked()){
                    chkFarinhaTrigoIntegral.setBackgroundColor(Color.LTGRAY);
                    cereais[3] = "Farinha de trigo integral. ";
                    cont++;
                }else{
                    chkFarinhaTrigoIntegral.setBackgroundColor(Color.WHITE);
                    cereais[3] = "";
                    cont--;
                }
            }
        });
        chkFlocosCereais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFlocosCereais.isChecked()){
                    chkFlocosCereais.setBackgroundColor(Color.LTGRAY);
                    cereais[4] = "Flocos de cereais. ";
                    cont++;
                }else{
                    chkFlocosCereais.setBackgroundColor(Color.WHITE);
                    cereais[4] = "";
                    cont--;
                }
            }
        });
        chkMilho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkMilho.isChecked()){
                    chkMilho.setBackgroundColor(Color.LTGRAY);
                    cereais[5] = "Milho. ";
                    cont++;
                }else{
                    chkMilho.setBackgroundColor(Color.WHITE);
                    cereais[5] = "";
                    cont--;
                }
            }
        });
        chkGermemTrigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkGermemTrigo.isChecked()){
                    chkGermemTrigo.setBackgroundColor(Color.LTGRAY);
                    cereais[6] = "Gérmem de trigo. ";
                    cont++;
                }else{
                    chkGermemTrigo.setBackgroundColor(Color.WHITE);
                    cereais[6] = "";
                    cont--;
                }
            }
        });
        chkSementeGirassol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSementeGirassol.isChecked()){
                    chkSementeGirassol.setBackgroundColor(Color.LTGRAY);
                    cereais[7] = "Semente de girassol. ";
                    cont++;
                }else{
                    chkSementeGirassol.setBackgroundColor(Color.WHITE);
                    cereais[7] = "";
                    cont--;
                }
            }
        });
        chkLeiteNinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkLeiteNinho.isChecked()){
                    chkLeiteNinho.setBackgroundColor(Color.LTGRAY);
                    cereais[8] = "Leite ninho fortificado. ";
                    cont++;
                }else{
                    chkLeiteNinho.setBackgroundColor(Color.WHITE);
                    cereais[8] = "";
                    cont--;
                }
            }
        });
        chkArroz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkArroz.isChecked()){
                    chkArroz.setBackgroundColor(Color.LTGRAY);
                    cereais[9] = "Arroz. ";
                    cont++;
                }else{
                    chkArroz.setBackgroundColor(Color.WHITE);
                    cereais[9] = "";
                    cont--;
                }
            }
        });
        chkFeijao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkFeijao.isChecked()){
                    chkFeijao.setBackgroundColor(Color.LTGRAY);
                    cereais[10] = "Feijão.";
                    cont++;
                }else{
                    chkFeijao.setBackgroundColor(Color.WHITE);
                    cereais[10] = "";
                    cont--;
                }
            }
        });
        chkAgriao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAgriao.isChecked()){
                    chkAgriao.setBackgroundColor(Color.LTGRAY);
                    verduras[0] = "Agrião. ";
                    cont++;
                }else{
                    chkAgriao.setBackgroundColor(Color.WHITE);
                    verduras[0] = "";
                    cont--;
                }
            }
        });
        chkAspargos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAspargos.isChecked()){
                    chkAspargos.setBackgroundColor(Color.LTGRAY);
                    verduras[1] = "Aspargos. ";
                    cont++;
                }else{
                    chkAspargos.setBackgroundColor(Color.WHITE);
                    verduras[1] = "";
                    cont--;
                }
            }
        });
        chkBrocolis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkBrocolis.isChecked()){
                    chkBrocolis.setBackgroundColor(Color.LTGRAY);
                    verduras[2] = "Brócolis. ";
                    cont++;
                }else{
                    chkBrocolis.setBackgroundColor(Color.WHITE);
                    verduras[2] = "";
                    cont--;
                }
            }
        });
        chkCouveVerde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCouveVerde.isChecked()){
                    chkCouveVerde.setBackgroundColor(Color.LTGRAY);
                    verduras[3] = "Couve verde. ";
                    cont++;
                }else{
                    chkCouveVerde.setBackgroundColor(Color.WHITE);
                    verduras[3] = "";
                    cont--;
                }
            }
        });
        chkEspinafre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkEspinafre.isChecked()){
                    chkEspinafre.setBackgroundColor(Color.LTGRAY);
                    verduras[4] = "Espinafre. ";
                    cont++;
                }else{
                    chkEspinafre.setBackgroundColor(Color.WHITE);
                    verduras[4] = "";
                    cont--;
                }
            }
        });
        chkRepolho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRepolho.isChecked()){
                    chkRepolho.setBackgroundColor(Color.LTGRAY);
                    verduras[5] = "Repolho. ";
                    cont++;
                }else{
                    chkRepolho.setBackgroundColor(Color.WHITE);
                    verduras[5] = "";
                    cont--;
                }
            }
        });
        chkRucula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRucula.isChecked()){
                    chkRucula.setBackgroundColor(Color.LTGRAY);
                    verduras[6] = "Rúcula. ";
                    cont++;
                }else{
                    chkRucula.setBackgroundColor(Color.WHITE);
                    verduras[6] = "";
                    cont--;
                }
            }
        });
        chkAlface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAlface.isChecked()){
                    chkAlface.setBackgroundColor(Color.LTGRAY);
                    verduras[7] = "Alface. ";
                    cont++;
                }else{
                    chkAlface.setBackgroundColor(Color.WHITE);
                    verduras[7] = "";
                    cont--;
                }
            }
        });
        chkCenoura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCenoura.isChecked()){
                    chkCenoura.setBackgroundColor(Color.LTGRAY);
                    verduras[8] = "Cenoura. ";
                    cont++;
                }else{
                    chkCenoura.setBackgroundColor(Color.WHITE);
                    verduras[8] = "";
                    cont--;
                }
            }
        });
        chkPepino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkPepino.isChecked()){
                    chkPepino.setBackgroundColor(Color.LTGRAY);
                    verduras[9] = "Pepino. ";
                    cont++;
                }else{
                    chkPepino.setBackgroundColor(Color.WHITE);
                    verduras[9] = "";
                    cont--;
                }
            }
        });
        chkAlho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAlho.isChecked()){
                    chkAlho.setBackgroundColor(Color.LTGRAY);
                    verduras[10] = "Alho. ";
                    cont++;
                }else{
                    chkAlho.setBackgroundColor(Color.WHITE);
                    verduras[10] = "";
                    cont--;
                }
            }
        });
        chkCebola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCebola.isChecked()){
                    chkCebola.setBackgroundColor(Color.LTGRAY);
                    verduras[11] = "Cebola. ";
                    cont++;
                }else{
                    chkCebola.setBackgroundColor(Color.WHITE);
                    verduras[11] = "";
                    cont--;
                }
            }
        });
        chkRabanete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkRabanete.isChecked()){
                    chkRabanete.setBackgroundColor(Color.LTGRAY);
                    verduras[12] = "Rabanete. ";
                    cont++;
                }else{
                    chkRabanete.setBackgroundColor(Color.WHITE);
                    verduras[12] = "";
                    cont--;
                }
            }
        });
        chkBatata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkBatata.isChecked()){
                    chkBatata.setBackgroundColor(Color.LTGRAY);
                    verduras[13] = "Batata.";
                    cont++;
                }else{
                    chkBatata.setBackgroundColor(Color.WHITE);
                    verduras[13] = "";
                    cont--;
                }
            }
        });
        chkPaoFrances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkPaoFrances.isChecked()){
                    chkPaoFrances.setBackgroundColor(Color.LTGRAY);
                    outros[0] = "Pão francês. ";
                    cont++;
                }else{
                    chkPaoFrances.setBackgroundColor(Color.WHITE);
                    outros[0] = "";
                    cont--;
                }
            }
        });
        chkPaoIntegral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkPaoIntegral.isChecked()){
                    chkPaoIntegral.setBackgroundColor(Color.LTGRAY);
                    outros[1] = "Pão integral. ";
                    cont++;
                }else{
                    chkPaoIntegral.setBackgroundColor(Color.WHITE);
                    outros[1] = "";
                    cont--;
                }
            }
        });
        chkTorrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkTorrada.isChecked()){
                    chkTorrada.setBackgroundColor(Color.LTGRAY);
                    outros[2] = "Torrada. ";
                    cont++;
                }else{
                    chkTorrada.setBackgroundColor(Color.WHITE);
                    outros[2] = "";
                    cont--;
                }
            }
        });
        chkOmelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkOmelete.isChecked()){
                    chkOmelete.setBackgroundColor(Color.LTGRAY);
                    outros[3] = "Omelete. ";
                    cont++;
                }else{
                    chkOmelete.setBackgroundColor(Color.WHITE);
                    outros[3] = "";
                    cont--;
                }
            }
        });
        chkQueijoBranco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkQueijoBranco.isChecked()){
                    chkQueijoBranco.setBackgroundColor(Color.LTGRAY);
                    outros[4] = "Queijo branco. ";
                    cont++;
                }else{
                    chkQueijoBranco.setBackgroundColor(Color.WHITE);
                    outros[4] = "";
                    cont--;
                }
            }
        });
        chkEspaguete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkEspaguete.isChecked()){
                    chkEspaguete.setBackgroundColor(Color.LTGRAY);
                    outros[5] = "Espaguete. ";
                    cont++;
                }else{
                    chkEspaguete.setBackgroundColor(Color.WHITE);
                    outros[5] = "";
                    cont--;
                }
            }
        });
        chkBatataDoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkBatataDoce.isChecked()){
                    chkBatataDoce.setBackgroundColor(Color.LTGRAY);
                    outros[6] = "Batata docê. ";
                    cont++;
                }else{
                    chkBatataDoce.setBackgroundColor(Color.WHITE);
                    outros[6] = "";
                    cont--;
                }
            }
        });
        chkAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAgua.isChecked()){
                    chkAgua.setBackgroundColor(Color.LTGRAY);
                    bebidas[0] = "Água. ";
                    cont++;
                }else{
                    chkAgua.setBackgroundColor(Color.WHITE);
                    bebidas[0] = "";
                    cont--;
                }
            }
        });
        chkSucos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkSucos.isChecked()){
                    chkSucos.setBackgroundColor(Color.LTGRAY);
                    bebidas[1] = "Sucos naturais. ";
                    cont++;
                }else{
                    chkSucos.setBackgroundColor(Color.WHITE);
                    bebidas[1] = "";
                    cont--;
                }
            }
        });
        chkVitaminas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkVitaminas.isChecked()){
                    chkVitaminas.setBackgroundColor(Color.LTGRAY);
                    bebidas[2] = "Vitaminas de frutas. ";
                    cont++;
                }else{
                    chkVitaminas.setBackgroundColor(Color.WHITE);
                    bebidas[2] = "";
                    cont--;
                }
            }
        });
        chkAguaCoco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkAguaCoco.isChecked()){
                    chkAguaCoco.setBackgroundColor(Color.LTGRAY);
                    bebidas[3] = "Água de coco. ";
                    cont++;
                }else{
                    chkAguaCoco.setBackgroundColor(Color.WHITE);
                    bebidas[3] = "";
                    cont--;
                }
            }
        });
        chkLeiteDesnatado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkLeiteDesnatado.isChecked()){
                    chkLeiteDesnatado.setBackgroundColor(Color.LTGRAY);
                    bebidas[4] = "Leite desnatado. ";
                    cont++;
                }else{
                    chkLeiteDesnatado.setBackgroundColor(Color.WHITE);
                    bebidas[4] = "";
                    cont--;
                }
            }
        });
        chkCafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCafe.isChecked()){
                    chkCafe.setBackgroundColor(Color.LTGRAY);
                    bebidas[5] = "Café preto descafeinado. ";
                    cont++;
                }else{
                    chkCafe.setBackgroundColor(Color.WHITE);
                    bebidas[5] = "";
                    cont--;
                }
            }
        });
        chkCha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkCha.isChecked()){
                    chkCha.setBackgroundColor(Color.LTGRAY);
                    bebidas[6] = "Chá sem açucar. ";
                    cont++;
                }else{
                    chkCha.setBackgroundColor(Color.WHITE);
                    bebidas[6] = "";
                    cont--;
                }
            }
        });
        chkIogurte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chkIogurte.isChecked()){
                    chkIogurte.setBackgroundColor(Color.LTGRAY);
                    bebidas[7] = "Iogurte desnatado.";
                    cont++;
                }else{
                    chkIogurte.setBackgroundColor(Color.WHITE);
                    bebidas[7] = "";
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
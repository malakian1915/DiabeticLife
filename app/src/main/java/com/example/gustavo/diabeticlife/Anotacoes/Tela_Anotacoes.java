package com.example.gustavo.diabeticlife.Anotacoes;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.R;
import com.github.clans.fab.FloatingActionButton;

public class Tela_Anotacoes extends AppCompatActivity {

    FloatingActionButton fabSalvar, fabVerAnotacao;
    EditText edtAnotacao;

    DatePicker dpAgenda;
    FloatingActionButton fabVerAgenda;
    String horaAgenda = null, dataAgenda, anotAgenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__anotacoes);

        TabHost abas = (TabHost) findViewById(R.id.tabhost);
        abas.setup();

        TabHost.TabSpec descritor = abas.newTabSpec("aba1");
        descritor.setContent(R.id.anotacao);
        descritor.setIndicator("Anotações");
        abas.addTab(descritor);

        descritor = abas.newTabSpec("aba2");
        descritor.setContent(R.id.agenda);
        descritor.setIndicator("Agenda");
        abas.addTab(descritor);

        gravarAnotacao();
        verAnotacoes();

        dpAgenda = (DatePicker) findViewById(R.id.dpAgenda);

        dpAgenda.init(dpAgenda.getYear(), dpAgenda.getMonth(), dpAgenda.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view,final int year,final int monthOfYear,final int dayOfMonth) {
                final int  month = monthOfYear + 1;
                AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Anotacoes.this);
                msg.setTitle(dayOfMonth+"/"+month+"/"+year);
                msg.setMessage("Deixe aqui seus compromissos:");
                final EditText edittext = new EditText(Tela_Anotacoes.this);
                msg.setView(edittext);
                msg.setNegativeButton("CANCELAR", null);
                msg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(edittext.getText().toString().isEmpty())
                            Toast.makeText(Tela_Anotacoes.this, "O campo não deve estar vazio", Toast.LENGTH_SHORT).show();
                        else {
                            anotAgenda = edittext.getText().toString();
                            dataAgenda = dayOfMonth + "/" + month + "/" + year;
                            pegarHoraDaAgenda();
                        }
                    }
                });
                msg.show();
            }
        });

        fabVerAgenda = (FloatingActionButton)findViewById(R.id.fabVerAgenda);
        fabVerAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirVerAgenda = new Intent(Tela_Anotacoes.this, Tela_Ver_Agenda.class);
                startActivity(abrirVerAgenda);
            }
        });

    }

    public void gravarAnotacao(){
        fabSalvar = (FloatingActionButton)findViewById(R.id.fabSalvarAnotacao);
        edtAnotacao = (EditText)findViewById(R.id.edtAnotacao);
        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtAnotacao.getText().toString().isEmpty()){
                    edtAnotacao.setError("O campo não deve estar vazio");
                    zeraErroAnotacao();
                } else {
                    BancoControllerAnotacao crud = new BancoControllerAnotacao(getBaseContext());
                    String anotacaoString = edtAnotacao.getText().toString();
                    String horario = android.text.format.DateFormat.format("dd/MM/yy          hh:mm a", new java.util.Date()).toString();
                    String resultado;

                    resultado = crud.cadastrarAnotacao(anotacaoString, horario);
                    edtAnotacao.setText(null);
                    Toast.makeText(Tela_Anotacoes.this, resultado, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void verAnotacoes(){
        fabVerAnotacao = (FloatingActionButton)findViewById(R.id.fabVerAnotacao);
        fabVerAnotacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verAnotacoes = new Intent(Tela_Anotacoes.this, Tela_Ver_Anotacoes.class);
                startActivity(verAnotacoes);
            }
        });
    }

    public void zeraErroAnotacao(){
        int SPLASH_TIME_OUT = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                edtAnotacao.setError(null);
            }
        }, SPLASH_TIME_OUT);
    }

    public void pegarHoraDaAgenda(){
        AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Anotacoes.this);
        final TimePicker timePicker = new TimePicker(Tela_Anotacoes.this);
        timePicker.setIs24HourView(true);
        msg.setView(timePicker);
        msg.setPositiveButton("SALVAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hr = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                horaAgenda = hr+":"+min;
                BancoControlerAgenda crudAgenda = new BancoControlerAgenda(getBaseContext());
                String resultado;
                resultado = crudAgenda.cadastrarAgenda(anotAgenda, dataAgenda, horaAgenda);
                Toast.makeText(Tela_Anotacoes.this, resultado, Toast.LENGTH_SHORT).show();
            }
        });
        msg.setNegativeButton("CANCELAR", null);
        msg.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
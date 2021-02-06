package com.example.gustavo.diabeticlife.Dados_Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Inicio.Tela_Inicio;
import com.example.gustavo.diabeticlife.R;

public class Tela_Dados_Usuario extends AppCompatActivity {

    Spinner cmbTipoDiabetes;
    EditText edtNome, edtIdade;
    Button btnCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__dados__usuario);

        cadastrarUsu();

    }

    public void cadastrarUsu(){
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtIdade = (EditText)findViewById(R.id.edtIdade);
        cmbTipoDiabetes = (Spinner)findViewById(R.id.cmbTipo);
        btnCadastrar = (Button)findViewById(R.id.btnPronto);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNome.getText().toString().isEmpty() || edtIdade.getText().toString().isEmpty() || cmbTipoDiabetes.getSelectedItem().toString().equals(""))
                    Toast.makeText(Tela_Dados_Usuario.this, "Nenhum campo deve estar vazio", Toast.LENGTH_LONG).show();
                else{
                    BancoControllerUsuario crud = new BancoControllerUsuario(getBaseContext());
                    String nome = edtNome.getText().toString().toUpperCase();
                    int idade = Integer.parseInt(edtIdade.getText().toString());
                    String tipoDiabetes = cmbTipoDiabetes.getSelectedItem().toString();
                    String resultado;
                    resultado = crud.cadastrarUsuario(nome, idade, tipoDiabetes);
                    Toast.makeText(Tela_Dados_Usuario.this, resultado, Toast.LENGTH_SHORT).show();

                    Intent abriInicio = new Intent(Tela_Dados_Usuario.this, Tela_Inicio.class);
                    startActivity(abriInicio);
                    finish();
                }
            }
        });
    }


}

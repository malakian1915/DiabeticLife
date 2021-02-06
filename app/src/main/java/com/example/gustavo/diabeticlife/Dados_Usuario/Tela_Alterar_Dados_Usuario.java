package com.example.gustavo.diabeticlife.Dados_Usuario;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.R;

public class Tela_Alterar_Dados_Usuario extends AppCompatActivity {

    Cursor cursor;
    EditText edtNome, edtIdade;
    Spinner cmbTipo;
    Button btnAlterar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__alterar__dados__usuario);

        edtNome = (EditText)findViewById(R.id.edtAlterarNome);
        edtIdade = (EditText)findViewById(R.id.edtAlterarIdade);
        cmbTipo = (Spinner)findViewById(R.id.cmbAlterarTipo);
        btnAlterar = (Button)findViewById(R.id.btnAlterar);

        final BancoControllerUsuario crud = new BancoControllerUsuario(getBaseContext());
        cursor = crud.carregaDados();
        final int idUsuario = cursor.getInt(cursor.getColumnIndexOrThrow(BancoDeDados.IDUSUARIO));
        edtNome.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.NOMEUSUARIO)));
        edtIdade.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDADEUSUARIO)));
        String tipo = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.TIPODIABETES));
        if(tipo.equals("DIABETES TIPO 1"))
            cmbTipo.setSelection(1);
        else if(tipo.equals("DIABETES TIPO 2"))
            cmbTipo.setSelection(2);
        else if(tipo.equals("DIABETES INSIPIDUS"))
            cmbTipo.setSelection(3);
        else if(tipo.equals("DIABETES GESTACIONAL"))
            cmbTipo.setSelection(4);

        cursor = crud.carregaDadoById(idUsuario);

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtNome.getText().toString().isEmpty() || edtIdade.getText().toString().isEmpty() || cmbTipo.getSelectedItem().toString().equals("")){
                    Toast.makeText(Tela_Alterar_Dados_Usuario.this, "Nenhum campo deve estar vazio", Toast.LENGTH_LONG).show();
                } else {
                    crud.alteraRegistro(idUsuario, edtNome.getText().toString().toUpperCase(), Integer.parseInt(edtIdade.getText().toString()), cmbTipo.getSelectedItem().toString());
                    Toast.makeText(Tela_Alterar_Dados_Usuario.this, "Dados atualizados", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }
}
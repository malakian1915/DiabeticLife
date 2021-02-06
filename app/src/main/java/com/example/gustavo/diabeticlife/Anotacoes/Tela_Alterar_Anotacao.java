package com.example.gustavo.diabeticlife.Anotacoes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.R;
import com.github.clans.fab.FloatingActionButton;

public class Tela_Alterar_Anotacao extends AppCompatActivity {

    Cursor cursor;
    BancoControllerAnotacao crud;
    String codigo;
    EditText edtAlterar;
    FloatingActionButton fabDelete, fabUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__alterar__anotacao);

        edtAlterar = (EditText)findViewById(R.id.edtAlterar);

        codigo = this.getIntent().getStringExtra("codigo");
        fabDelete = (FloatingActionButton)findViewById(R.id.fabExcluir);
        fabUpdate = (FloatingActionButton)findViewById(R.id.fabAlterar);

        crud = new BancoControllerAnotacao(getBaseContext());

        cursor = crud.carregarAnotacaoPorID(Integer.parseInt(codigo));
        edtAlterar.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.ANOTACAO)));

        fabUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String horario = android.text.format.DateFormat.format("dd/MM/yy          hh:mm a", new java.util.Date()).toString();
                crud.alteraAnotacao(Integer.parseInt(codigo), edtAlterar.getText().toString(), horario);
                Intent intent = new Intent(Tela_Alterar_Anotacao.this, Tela_Ver_Anotacoes.class);
                startActivity(intent);
                Toast.makeText(Tela_Alterar_Anotacao.this, "Anotação atualizada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletarAnotacao(Integer.parseInt(codigo));
                Intent intent = new Intent(Tela_Alterar_Anotacao.this, Tela_Ver_Anotacoes.class);
                startActivity(intent);
                Toast.makeText(Tela_Alterar_Anotacao.this, "Anotação excluída", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Tela_Alterar_Anotacao.this, Tela_Ver_Anotacoes.class);
        startActivity(intent);
        finish();
    }

}

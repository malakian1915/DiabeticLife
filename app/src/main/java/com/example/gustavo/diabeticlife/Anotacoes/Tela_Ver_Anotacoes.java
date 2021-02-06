package com.example.gustavo.diabeticlife.Anotacoes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.R;

public class Tela_Ver_Anotacoes extends AppCompatActivity {

    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__ver__anotacoes);

        lista = (ListView)findViewById(R.id.lstAnotacoes);

        //CONSULTAR ANOTACOES
        BancoControllerAnotacao crud = new BancoControllerAnotacao(getBaseContext());
        final Cursor cursor = crud.consultarAnotados();
        String[] nomeCampos = new String[] {BancoDeDados.HORAANOTACAO, BancoDeDados.ANOTACAO};
        int[] idViews = new int[] {R.id.txtData, R.id.txtAnotacao};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(),
                R.layout.tela_anotacao_estilizada, cursor, nomeCampos, idViews, 0);
        lista.setAdapter(adaptador);

        //ALTERAR ANOTACOES
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDANOTACAO));
                Intent intent = new Intent(Tela_Ver_Anotacoes.this, Tela_Alterar_Anotacao.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

package com.example.gustavo.diabeticlife.Sintomas;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.R;

public class Tela_Ver_Sintomas extends AppCompatActivity {

    ListView lista;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__ver__sintomas);

        lista = (ListView)findViewById(R.id.lstSintomas);

        final BancoControllerSintomas crud = new BancoControllerSintomas(getBaseContext());
        final Cursor cursor = crud.consultarSintomas();
        String[] nomeCampos = new String[] {BancoDeDados.HORARIOSINTOMAS, BancoDeDados.SINTOMAS, BancoDeDados.MAISSINTOMAS};
        int[] idViews = new int[] {R.id.txtHorarioSintomas, R.id.txtSintomas, R.id.txtMaisSintomas};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.ver_sintomas_estilizado,
                cursor,nomeCampos,idViews,0);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDSINTOMAS));
                final AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Ver_Sintomas.this);
                msg.setTitle("ATENÇÃO");
                msg.setMessage("Deseja excluir esses sintomas?");
                msg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        crud.deletarSintomas(Integer.parseInt(codigo));
                        Toast.makeText(Tela_Ver_Sintomas.this, "Sintomas excluídos", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Tela_Ver_Sintomas.this, Tela_Ver_Sintomas.class);
                        startActivity(intent);
                        finish();
                    }
                });
                msg.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                msg.show();
            }
        });

    }
}

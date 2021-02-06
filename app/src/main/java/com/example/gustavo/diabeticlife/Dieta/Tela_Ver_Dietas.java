package com.example.gustavo.diabeticlife.Dieta;

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

public class Tela_Ver_Dietas extends AppCompatActivity {

    private ListView lista;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__ver__dietas);

        final BancoControllerDieta crud = new BancoControllerDieta(getBaseContext());
        final Cursor cursor = crud.consultarDietas();
        String[] nomeCampos = new String[] {BancoDeDados.TIPODIETA, BancoDeDados.FRUTAS, BancoDeDados.CARNES, BancoDeDados.CEREAISEGRAOS, BancoDeDados.VERDURASELEGUMES, BancoDeDados.OUTROS, BancoDeDados.BEBIDAS, BancoDeDados.MAISALIMENTOS, BancoDeDados.DATADIETA, BancoDeDados.HORADIETA};
        int[] idViews = new int[] {R.id.txtTipoDieta, R.id.txtFrutas, R.id.txtCarnes, R.id.txtCereais, R.id.txtVerduras, R.id.txtOutros, R.id.txtBebidas, R.id.txtMaisAlimentos, R.id.txtDataDieta, R.id.txtHoraDieta};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.tela_ver_dietas_estilizada,
                cursor, nomeCampos, idViews, 0);
        lista = (ListView) findViewById(R.id.lstDietas);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDDIETA));
                final AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Ver_Dietas.this);
                msg.setTitle("ATENÇÃO");
                msg.setMessage("Deseja excluir essa dieta?");
                msg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        crud.deletarDieta(Integer.parseInt(codigo));
                        Toast.makeText(Tela_Ver_Dietas.this, "Dieta excluída", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Tela_Ver_Dietas.this, Tela_Ver_Dietas.class);
                        startActivity(intent);
                        finish();
                    }
                });
                msg.setNegativeButton("NÃO", null);
                msg.show();
            }
        });
    }

}

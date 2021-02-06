package com.example.gustavo.diabeticlife.Medicamentos;

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

public class Tela_Ver_Medicamentos extends AppCompatActivity {

    ListView lstMedicamentos;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__ver__medicamentos);

        lstMedicamentos = (ListView)findViewById(R.id.lstMedicamentos);

        final BancoControllerMedicamentos crud = new BancoControllerMedicamentos(getBaseContext());
        final Cursor cursor = crud.consultarMedicamentos();
        String[] nomeCampos = new String[] {BancoDeDados.MEDICAMENTOS, BancoDeDados.DATAMEDICAMENTOS, BancoDeDados.HORAMEDICAMENTOS, BancoDeDados.MAISMEDICAMENTOS};
        int[] idViews = new int[] {R.id.txtMedicamentos, R.id.txtDataMed, R.id.txtHoraMed, R.id.txtMaisMedicamentos};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.tela_ver_medicamentos_estiizado,
                cursor, nomeCampos, idViews, 0);
        lstMedicamentos.setAdapter(adapter);

        lstMedicamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDMEDICAMENTOS));
                final AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Ver_Medicamentos.this);
                msg.setTitle("ATENÇÃO");
                msg.setMessage("Deseja excluir esses medicamentos?");
                msg.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        crud.deletarMedicamentos(Integer.parseInt(codigo));
                        Toast.makeText(Tela_Ver_Medicamentos.this, "Medicamentos excluídos", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Tela_Ver_Medicamentos.this, Tela_Ver_Medicamentos.class);
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

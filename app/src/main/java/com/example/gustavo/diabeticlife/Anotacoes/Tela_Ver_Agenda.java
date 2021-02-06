package com.example.gustavo.diabeticlife.Anotacoes;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Banco.BancoDeDados;
import com.example.gustavo.diabeticlife.R;

public class Tela_Ver_Agenda extends AppCompatActivity {

    ListView lstAgenda;
    int codigoG;
    String notas;
    String horaAgendaAlterada, dataAgendaAlterada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__ver__agenda);

        lstAgenda = (ListView)findViewById(R.id.lstAgenda);

        final BancoControlerAgenda crud = new BancoControlerAgenda(getBaseContext());
        final Cursor cursor = crud.consultarAgenda();
        String[] nomeCampos = new String[] {BancoDeDados.DATAAGENDA, BancoDeDados.ANOTAGENDA, BancoDeDados.HORAAGENDA};
        int[] idViews = new int[] {R.id.txtDataAgenda, R.id.txtAnotAgenda, R.id.txtHorarioAgenda};
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(getBaseContext(), R.layout.ver_agenda_estilizado,
                cursor, nomeCampos, idViews, 0);
        lstAgenda.setAdapter(adaptador);

        lstAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.IDAGENDA));
                codigoG = Integer.parseInt(codigo);

                AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Ver_Agenda.this);
                final EditText editText = new EditText(Tela_Ver_Agenda.this);
                msg.setTitle("EDITAR AGENDA");
                msg.setView(editText);
                editText.setText(cursor.getString(cursor.getColumnIndexOrThrow(BancoDeDados.ANOTAGENDA)));
                msg.setPositiveButton("ALTERAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notas = editText.getText().toString();
                        pegarDateAlterada();
                    }
                });
                msg.setNegativeButton("EXCLUIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        crud.deletarAgenda(Integer.parseInt(codigo));
                        Intent intent = new Intent(Tela_Ver_Agenda.this, Tela_Ver_Agenda.class);
                        startActivity(intent);
                        Toast.makeText(Tela_Ver_Agenda.this, "Anotação excluída", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                msg.show();
            }
        });

    }

    public void pegarDateAlterada(){
        AlertDialog.Builder mess = new AlertDialog.Builder(Tela_Ver_Agenda.this);
        final DatePicker datePicker = new DatePicker(Tela_Ver_Agenda.this);
        mess.setView(datePicker);
        mess.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth()+1;
                int year = datePicker.getYear();
                dataAgendaAlterada = day+"/"+month+"/"+year;
                pegarHoraAlterada();
            }
        });
        mess.setNegativeButton("CANCELAR", null);
        mess.show();
    }

    public void pegarHoraAlterada(){
        AlertDialog.Builder msg = new AlertDialog.Builder(Tela_Ver_Agenda.this);
        final TimePicker timePicker = new TimePicker(Tela_Ver_Agenda.this);
        msg.setView(timePicker);
        timePicker.setIs24HourView(true);
        msg.setPositiveButton("SALVAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int hr = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                horaAgendaAlterada = hr+":"+min;
                final BancoControlerAgenda crud = new BancoControlerAgenda(getBaseContext());
                crud.alteraAgenda(codigoG, dataAgendaAlterada, notas, horaAgendaAlterada);
                Intent recarregar = new Intent(Tela_Ver_Agenda.this, Tela_Ver_Agenda.class);
                startActivity(recarregar);
                Toast.makeText(Tela_Ver_Agenda.this, "Anotação alterada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        msg.setNegativeButton("CANCELAR", null);
        msg.show();
    }

}

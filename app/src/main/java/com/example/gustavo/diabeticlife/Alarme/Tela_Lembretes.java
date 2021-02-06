package com.example.gustavo.diabeticlife.Alarme;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gustavo.diabeticlife.Inicio.Tela_Inicio;
import com.example.gustavo.diabeticlife.R;

import java.text.DecimalFormat;
import java.util.Calendar;

public class Tela_Lembretes extends AppCompatActivity {

    TimePicker timePicker;
    Button btnSalvar,btnCancelar;
    EditText edtNotas;
    AlarmManager alarm;
    Context context;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__lembretes);

        this.context = this;

        edtNotas = (EditText)findViewById(R.id.edtNotasAlarmes);
        timePicker = (TimePicker) findViewById(R.id.tmpLembretes);
        btnSalvar = (Button)findViewById(R.id.btnSalvarAlarme);
        btnCancelar = (Button)findViewById(R.id.btnCancelarAlarme);
        alarm  = (AlarmManager)getSystemService(ALARM_SERVICE);

        timePicker.setIs24HourView(true);

        final Calendar calendar = Calendar.getInstance();

        final Intent my_intent = new Intent(this.context, Alarm_Receiver.class);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hrA = calendar.get(Calendar.HOUR_OF_DAY);
                int mnA = calendar.get(Calendar.MINUTE);

                int horaS = timePicker.getCurrentHour();
                int minuS = timePicker.getCurrentMinute();

                DecimalFormat df = new DecimalFormat("#0");

                if(minuS < 10){
                    df.format(minuS);
                }

                if(horaS < hrA){
                    Toast.makeText(context, "Defina um horário maior que o horário atual", Toast.LENGTH_SHORT).show();
                }
                else if(horaS == hrA){
                    if(minuS <= mnA){
                        Toast.makeText(context, "Defina um horário maior que o horário atual", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                        calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);

                        my_intent.putExtra("extra", "alarm on");
                        my_intent.putExtra("notas", edtNotas.getText().toString());

                        pendingIntent = pendingIntent.getBroadcast(Tela_Lembretes.this, 0, my_intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                        alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                        Toast.makeText(context, "Novo alarme agendado para "+timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();
                        edtNotas.setText(null);
                    }
                }
                else{
                    calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    my_intent.putExtra("extra", "alarm on");
                    my_intent.putExtra("notas", edtNotas.getText().toString());

                    pendingIntent = pendingIntent.getBroadcast(Tela_Lembretes.this, 0, my_intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);

                    alarm.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                    Toast.makeText(context, "Novo alarme agendado para "+timePicker.getCurrentHour()+":"+timePicker.getCurrentMinute(), Toast.LENGTH_SHORT).show();
                    edtNotas.setText(null);
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alarm.cancel(pendingIntent);
                my_intent.putExtra("extra", "alarm off");
                sendBroadcast(my_intent);
                Toast.makeText(context, "Alarme cancelado", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(Tela_Lembretes.this, Tela_Inicio.class);
        startActivity(it);
        finish();
    }
}
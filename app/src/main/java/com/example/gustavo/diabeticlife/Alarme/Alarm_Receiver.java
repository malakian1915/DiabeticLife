package com.example.gustavo.diabeticlife.Alarme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Alarm_Receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String stateAlarm = intent.getExtras().getString("extra");
        String notas = intent.getExtras().getString("notas");

        //serviço ringtone
        Intent service_intent = new Intent(context, RingtonePlayingService.class);
        service_intent.putExtra("extra", stateAlarm);
        service_intent.putExtra("notas", notas);
        context.startService(service_intent);
    }

}

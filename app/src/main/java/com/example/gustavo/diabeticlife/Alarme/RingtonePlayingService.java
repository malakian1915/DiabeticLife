package com.example.gustavo.diabeticlife.Alarme;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.gustavo.diabeticlife.R;

public class RingtonePlayingService extends Service {

    MediaPlayer somAlarme;
    int startId;
    boolean isMusic = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        try {

            String state = intent.getExtras().getString("extra");
            String notas = intent.getExtras().getString("notas");

            assert state != null;
            switch (state) {
                case "alarm on":
                    startId = 1;
                    break;
                case "alarm off":
                    startId = 0;
                    break;
                default:
                    startId = 0;
                    break;
            }

            //notificacao
            NotificationManager notify_manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Intent intent_main_activity = new Intent(this.getApplicationContext(), Tela_Lembretes.class);
            PendingIntent pending_intent_main_activity = PendingIntent.getActivity(this, 0,
                    intent_main_activity, 0);

            Notification notification_popup = new Notification.Builder(this)
                    .setContentTitle("Alarme diário executado!")
                    .setContentText(notas)
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(pending_intent_main_activity)
                    .setAutoCancel(true)
                    .build();

            //verificar se o alarme nao esta ativo com ringtone e usuario ativou alarme
            if (!this.isMusic && startId == 1) {
                //colocar som no alarme
                somAlarme = MediaPlayer.create(this, R.raw.som);
                somAlarme.start();
                //chamar notificacao
                notify_manager.notify(0, notification_popup);
                vibrar();
                this.isMusic = true;
                this.startId = 0;
            }
            //verificar se o alarme esta ativo com ringtone e ativou alarme
            else if (this.isMusic && startId == 0) {
                //tirar som do alarme
                somAlarme.stop();
                somAlarme.reset();
                this.isMusic = false;
                this.startId = 0;
            }
            //se o usurio pressionou qulquer botao, se tem musica e se parou alarme
            else if (!this.isMusic && startId == 0) {
                this.isMusic = false;
                this.startId = 0;
            }
            //se existe musica e usuario ativou alarme
            else if (this.isMusic && startId == 1) {
                this.isMusic = true;
                this.startId = 1;
            }
            //nao faz nada só pega o evento
            else {

            }
        } catch (Exception e){

        }
        return START_NOT_STICKY;
    }

    public void vibrar(){
        Vibrator vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        super.onDestroy();
        this.isMusic = false;
    }

}

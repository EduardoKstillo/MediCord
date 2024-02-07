package com.unsapp.medicord.services;



import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.unsapp.medicord.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AlarmNotificationService extends Service {

    public static final String CHANNEL_ID = "AlarmChannel";
    private static int NOTIFICATION_ID;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressLint("ForegroundServiceType")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            String nombre = intent.getStringExtra("nombre");
            String riesgo = intent.getStringExtra("riesgo");
            NOTIFICATION_ID = intent.getIntExtra("ID", 0);
            //Logica para los botones de la notificacion
            Intent yesIntent = new Intent(this, NotificationReceiver.class);
            yesIntent.setAction("YES_ACTION");
            PendingIntent yesPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent noIntent = new Intent(this, NotificationReceiver.class);
            noIntent.setAction("NO_ACTION");
            PendingIntent noPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Intent helpIntent = new Intent(this, NotificationReceiver.class);
            helpIntent.setAction("HELP_ACTION");
            PendingIntent helpPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, helpIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            // Llamar a startForeground aquí después de programar la alarma
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Alarma")
                    .setContentText(nombre)
                    .setSmallIcon(R.drawable.ic_alarm)
                    .addAction(R.drawable.ic_check, "Sí", yesPendingIntent)
                    .addAction(R.drawable.ic_cancel, "No", noPendingIntent)
                    .addAction(R.drawable.ic_help, "Ayuda", helpPendingIntent)
                    .build();
            startForeground(NOTIFICATION_ID, notification);
            /*if (nombre != null && hora != null && riesgo != null) {

            }*/
        }else{
            System.out.println("Algo salio mal intento nulo");
        }

        return START_STICKY;
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Alarm Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
    @SuppressLint("ForegroundServiceType")
    private void showNotification() {
        Intent yesIntent = new Intent(this, NotificationReceiver.class);
        yesIntent.setAction("YES_ACTION");
        PendingIntent yesPendingIntent = PendingIntent.getBroadcast(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent noIntent = new Intent(this, NotificationReceiver.class);
        noIntent.setAction("NO_ACTION");
        PendingIntent noPendingIntent = PendingIntent.getBroadcast(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent helpIntent = new Intent(this, NotificationReceiver.class);
        helpIntent.setAction("HELP_ACTION");
        PendingIntent helpPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, helpIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Alarma")
                .setContentText("¿Desea detener la alarma?")
                .setSmallIcon(R.drawable.ic_alarm)
                .addAction(R.drawable.ic_check, "Sí", yesPendingIntent)
                .addAction(R.drawable.ic_cancel, "No", noPendingIntent)
                .addAction(R.drawable.ic_help, "Ayuda", helpPendingIntent)
                .build();

        startForeground(NOTIFICATION_ID, notification);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}




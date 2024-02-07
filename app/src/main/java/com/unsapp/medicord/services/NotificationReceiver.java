package com.unsapp.medicord.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String riesgo = intent.getStringExtra("riesgo");
        if (action != null) {
            switch (action) {
                case "YES_ACTION":
                    // Acción cuando se presiona "Sí"
                    Toast.makeText(context, "Alarma apagada", Toast.LENGTH_SHORT).show();
                    siAlarma(context);
                    break;
                case "NO_ACTION":
                    // Acción cuando se presiona "No"
                    Toast.makeText(context, "Alarma pospuesta", Toast.LENGTH_SHORT).show();
                    noAlarma(context);
                    break;
                case "HELP_ACTION":
                    // Acción cuando se presiona "Help"
                    Toast.makeText(context, "Alarma ayuda", Toast.LENGTH_SHORT).show();
                    llamadaAlarma(context);
                    break;

            }
        }
        Intent stopServiceIntent = new Intent(context, AlarmNotificationService.class);
        context.stopService(stopServiceIntent);
    }
    private void siAlarma(Context context) {
        // Implementa aquí la lógica para apagar la alarma
        // Por ejemplo, puedes detener el servicio que está reproduciendo el sonido de la alarma

    }

    private void noAlarma(Context context) {
        // Implementa aquí la lógica para posponer la alarma
        // Por ejemplo, puedes reprogramar la alarma para que suene más tarde
        // o cancelar la alarma actual y establecer una nueva alarma para una hora diferente
    }
    private void llamadaAlarma(Context context) {
        // Implementa aquí la lógica para posponer la alarma
        // Por ejemplo, puedes reprogramar la alarma para que suene más tarde
        // o cancelar la alarma actual y establecer una nueva alarma para una hora diferente

    }

}


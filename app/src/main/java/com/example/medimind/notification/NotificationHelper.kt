package com.example.medimind.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.medimind.MainActivity
import com.example.medimind.R

object NotificationHelper {

    const val CHANNEL_ID = "medimind_reminders"
    const val NOTIFICATION_ID = 1001
    const val EXTRA_ROUTE = "route"
    const val ROUTE_NOTIFICATION = "notification"
    const val ROUTE_DOSE_CONFIRMED = "dose_confirmed"
    const val ROUTE_SKIP_DOSE = "skip_dose"

    fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Recordatorios de medicamentos",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Notificaciones urgentes de toma de medicamentos"
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
        }
        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(channel)
    }

    fun sendUrgentReminder(context: Context) {
        // Tap notification → open NotificationScreen
        val tapIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_ROUTE, ROUTE_NOTIFICATION)
        }
        val tapPending = PendingIntent.getActivity(
            context, 0, tapIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Action "Confirmar toma" → open DoseConfirmedScreen
        val confirmIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_ROUTE, ROUTE_DOSE_CONFIRMED)
        }
        val confirmPending = PendingIntent.getActivity(
            context, 1, confirmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Action "Saltar dosis" → open SkipDoseScreen
        val skipIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra(EXTRA_ROUTE, ROUTE_SKIP_DOSE)
        }
        val skipPending = PendingIntent.getActivity(
            context, 2, skipIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("🚨 ÚLTIMA OPORTUNIDAD")
            .setContentText("Has pospuesto este medicamento 3 veces. Confirma en los próximos 40 min o se marcará como NO tomado.")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Has pospuesto este medicamento 3 veces. Si no lo confirmas en los próximos 40 minutos, se marcará automáticamente como NO tomado.\n\nPospuestos: 3/3")
            )
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
            .setContentIntent(tapPending)
            .addAction(0, "✓ Confirmar toma", confirmPending)
            .addAction(0, "✗ Saltar dosis", skipPending)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
    }
}

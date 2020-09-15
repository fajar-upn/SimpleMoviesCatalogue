package com.example.submission.ui.Settings

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.submission.R
import com.example.submission.ui.Movies.MoviesFragment
import java.util.*


class NotificationReceiver: BroadcastReceiver() {


    companion object{
        private const val NOTIFICATION_CHANNEL_ID = "channel_01"
        private const val NOTIFICATION_ID = 100
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        sendNotification(
            context,
            context?.getString(R.string.title),
            "Looking For New Movies ?"
        )
    }

    fun setDailyAlarm(context: Context){
        val intent = Intent(context,NotificationReceiver::class.java)

        val calendar:Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,20)
        calendar.set(Calendar.MINUTE,50)
        calendar.set(Calendar.SECOND,0)

        val pendingIntent:PendingIntent = PendingIntent.getBroadcast(context, NOTIFICATION_ID,intent,0)

        (context.getSystemService(Context.ALARM_SERVICE) as AlarmManager)?.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)
    }

    private fun sendNotification(context: Context?, title: String?, description: String) {
        val notificationManager:NotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(context, MoviesFragment::class.java)

        val pendingIntent:PendingIntent = PendingIntent.getActivity(
            context,
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val uriTone:Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.icon_film_camera)
            .setContentTitle(title)
            .setContentText(description)
            .setContentIntent(pendingIntent)
            .setColor(ContextCompat.getColor(context, R.color.yellow_tranparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(uriTone)

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "NOTIFICATION_CHANNEL_NAME",
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.YELLOW
        notificationChannel.enableVibration(true)
        notificationChannel.vibrationPattern = longArrayOf(
            100,
            200,
            300,
            400,
            500,
            400,
            300,
            200,
            400
        )

        builder.setChannelId(NOTIFICATION_CHANNEL_ID)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(getPendingIntent(context))
    }

    private fun getPendingIntent(context: Context): PendingIntent? {
        val intent = Intent(context, MoviesFragment::class.java)
        return PendingIntent.getBroadcast(
            context,
            100,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}




package com.example.prm_02

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat


class Notifier : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        createNotification(context!!)
    }

    //@RequiresApi(Build.VERSION_CODES.O)
    //@SuppressLint("ResourceType")
    private fun createNotification(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            println(resultCode)
            val pendingIntent: PendingIntent = PendingIntent.getActivity(context, resultCode, intent, 0)
            val name = "kanal_1"
            val descriptionText ="notif"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(1.toString(), name, importance)
            mChannel.description = descriptionText
            println(mChannel)
            val mNotifyMgr = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotifyMgr.createNotificationChannel(mChannel)
            val builder = NotificationCompat.Builder(context, 1.toString())
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Blisko miejsca")
                .setContentText("Jestes blisko jednego z miejsc")
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
            mNotifyMgr.notify(1, builder.build())

        }

    }
}
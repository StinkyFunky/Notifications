package com.example.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notification.databinding.ActivityMainBinding
import com.example.notification.databinding.ActivitySecondBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        val intent = Intent(this, MainActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK }
        val firstIntent = Intent(this, FirstActivity::class.java)
        val secondIntent = Intent(this, SecondActivity::class.java)
        val thirdIntent = Intent(this, ThirdActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val pendingIntentFirst = PendingIntent.getActivity(this, 1, firstIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingIntentSecond = PendingIntent.getActivity(this, 2, secondIntent, PendingIntent.FLAG_IMMUTABLE)
        val pendingIntentThird = PendingIntent.getActivity(this, 3, thirdIntent, PendingIntent.FLAG_IMMUTABLE)


        val firstBuilderNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_bookmark_24)
            .setContentTitle("Bookmark")
            .setContentText("Warhammer 40,000 - Warhammer 40,000")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Get full access to Warhammer 40,000: The App with your Warhammer+ subscription."))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_baseline_bookmark_24,
                getString(R.string.msg_open),
                pendingIntentFirst
            )

        val secondBuilderNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_phone_missed_24)
            .setContentTitle("Phone")
            .setContentText("Missed call")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Missing call form User"))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_baseline_phone_missed_24,
                getString(R.string.msg_open),
                pendingIntentSecond
            )

        val thirdBuilderNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_event_note_24)
            .setContentTitle("Note")
            .setContentText("Your event")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Meeting"))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                R.drawable.ic_baseline_event_note_24,
                getString(R.string.msg_open),
                pendingIntentThird
            )

        binding.btn.setOnClickListener {
            NotificationManagerCompat.from(this).notify(1, firstBuilderNotification.build())
        }

        binding.btn2.setOnClickListener {
            NotificationManagerCompat.from(this).notify(2, secondBuilderNotification.build())
        }

        binding.btn3.setOnClickListener {
            NotificationManagerCompat.from(this).notify(3, thirdBuilderNotification.build())
        }
    }

    companion object {
        const val CHANNEL_ID = "CHANNEL"
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.msg_channel_name)
            val descriptionText = getString(R.string.msg_channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}


package com.example.messagingapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {//represents the message that was recieved
        String title = message.getNotification().getTitle(); //to get message title
        String text = message.getNotification().getBody();//get notification body
        final String CHANNEL_ID="HEADS_UP_NOTIFICATION";
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                "Heads up notification",
                NotificationManager.IMPORTANCE_HIGH
        ); //to enstablish a connection between the app and the android device and we use it to set the behaviour of the message
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        Notification.Builder notification =new Notification.Builder(this,CHANNEL_ID)//To build a notification
                .setContentText(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1,notification.build());
        /*id for the notification, object from the notification we want to send*/
        super.onMessageReceived(message);
    }
}
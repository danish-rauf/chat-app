package com.example.chatapp.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.chatapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage message) {

        String text = message.getData().get("message");

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String channelId = "chat_channel";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(channelId, "Chat", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setContentTitle("New Message")
                        .setContentText(text)
                        .setSmallIcon(R.drawable.ic_launcher_foreground);

        manager.notify(1, builder.build());
    }
}
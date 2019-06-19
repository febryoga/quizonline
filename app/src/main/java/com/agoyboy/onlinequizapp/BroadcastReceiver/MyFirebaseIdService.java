package com.agoyboy.onlinequizapp.BroadcastReceiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.agoyboy.onlinequizapp.MainActivity;
import com.agoyboy.onlinequizapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseIdService extends FirebaseMessagingService {
    public void onMessageReceived(RemoteMessage rMsg) {
        sendNotification(rMsg.getNotification());
    }

    private void sendNotification(RemoteMessage.Notification rNotfy) {
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent rintent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, rintent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(rNotfy.getTitle())
                .setContentText(rNotfy.getBody())
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setSound(soundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
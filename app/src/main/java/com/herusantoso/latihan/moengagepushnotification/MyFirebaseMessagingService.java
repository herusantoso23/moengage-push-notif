package com.herusantoso.latihan.moengagepushnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.text.TextUtils;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.moengage.push.PushManager;
import com.moengage.pushbase.push.MoEngageNotificationUtils;

import java.util.Map;

/**
 * Created by santoso on 5/31/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> pushPayload = remoteMessage.getData();
        if (pushPayload != null) {
            if (MoEngageNotificationUtils.isFromMoEngagePlatform(pushPayload)) {
                PushManager.getInstance().getPushHandler().handlePushPayload(getApplicationContext(), pushPayload);
            } else {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(pushPayload.get("title"))
                                .setContentText(pushPayload.get("message"))
                                .setAutoCancel(true);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                Intent homeIntent = new Intent(this, MainActivity.class);
                stackBuilder.addNextIntent(homeIntent);

                /*
                Intent destination = null;
                if (pushPayload.get("defaultAction") != null) {
                    String actionStr = pushPayload.get("defaultAction");
                    JsonElement actionElement = new JsonParser().parse(actionStr);
                    JsonObject actionObject = actionElement.getAsJsonObject();

                    String value = actionObject.get("value").getAsString();
                    switch (actionObject.get("type").getAsString()) {
                        case "navigation_home_week":
                            if (TextUtils.isEmpty(value))
                                destination = HomeActivity.getCallingIntent(getBaseContext(), PageNavigation.Page.HOME);
                            else
                                destination = HomeActivity.getCallingIntent(getBaseContext(), PageNavigation.Page.HOME, Integer.parseInt(value));
                            break;
                    }
                }
                */

                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                mBuilder.setContentIntent(resultPendingIntent);
                Notification notif = mBuilder.build();
                notif.defaults |= Notification.DEFAULT_VIBRATE;
                int mNotificationId = 001;
                NotificationManager mNotifyMgr =
                        (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                mNotifyMgr.notify(mNotificationId, notif);

            }
        }
    }
}

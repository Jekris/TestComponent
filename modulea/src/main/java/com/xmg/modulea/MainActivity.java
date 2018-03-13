package com.xmg.modulea;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/module/activity1")
public class MainActivity extends Activity {

    private String mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modulea_activity_main);

        mData = getIntent().getStringExtra("data");
        Log.i("TAG", "ModuleA ---MainActivity===" + mData);

        openNotification();

        expandedNotification();

        updateNotification();

    }

    /**
     * 通知
     */
    private int mId = 0;

    private void openNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.notifications)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(mId, mBuilder.build());
    }


    /**
     * 将扩展布局应用于通知
     */
    public void expandedNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Event tracker")
                .setContentText("Events received");
        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
// Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");
// Moves events into the expanded layout
        for (int i = 0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
// Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(mId, mBuilder.build());
    }

    /**
     * 更新通知
     */
    public void updateNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// Sets an ID for the notification, so it can be updated
        int notifyID = 1;
        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle("New Message")
                .setContentText("You've received new messages.")
                .setSmallIcon(R.drawable.content_update);
        int numMessages = 0;
// Start of a loop that processes data and then notifies the user
        mNotifyBuilder.setContentText(mData)
                .setNumber(++numMessages);
        // Because the ID remains unchanged, the existing notification is
        // updated.
        mNotificationManager.notify(
                notifyID,
                mNotifyBuilder.build());
    }

    /**
     * 在锁定屏幕上控制媒体播放
     */
  /*  public void setMedia() {
        Notification notification = new Notification.Builder(this)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.player_star)
                // Add media control buttons that invoke intents in your media service
                .addAction(R.drawable.pre, "Previous", prevPendingIntent) // #0
                .addAction(R.drawable.pause, "Pause", pausePendingIntent)  // #1
                .addAction(R.drawable.next, "Next", nextPendingIntent)     // #2
                // Apply the media style template
                .setStyle(new Notification.MediaStyle()
                        .setShowActionsInCompactView(1 *//* #1: pause button *//*)
                        .setMediaSession(mMediaSession.getSessionToken())
                        .setContentTitle("Wonderful music")
                        .setContentText("My Awesome Band")
                        .setLargeIcon(albumArtBitmap)
                        .build();
    }*/
}

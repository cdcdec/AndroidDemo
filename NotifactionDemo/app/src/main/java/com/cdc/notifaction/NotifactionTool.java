package com.cdc.notifaction;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.cdc.notifactiondemo.R;

/**
 * Created by cdc on 2018/3/9.
 */

public class NotifactionTool {
    public static void createSimpleNotifaction(Context context){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");

// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, NotifactionResultActivity.class);

        Notification notification=mBuilder.build();
        Bundle bundle=new Bundle();
        bundle.putParcelable("notifaction",notification);
        resultIntent.putExtras(bundle);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(NotifactionResultActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(100, mBuilder.build());
    }

    public static void createNotifaction2(Context context,String channelId){
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(context,channelId);
        mBuilder.setSmallIcon(R.drawable.notification_icon);
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_enhanced_encryption_black));
        mBuilder.setContentTitle("新通知Title2");
        mBuilder.setContentText("新通知内容2");
        mBuilder.setAutoCancel(true);

        Intent resultIntent = new Intent(context, NotifactionResultActivity.class);
        Notification notification=mBuilder.build();

        Bundle bundle=new Bundle();
        bundle.putParcelable("notifaction",notification);
        resultIntent.putExtras(bundle);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotifactionResultActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        1,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(101, mBuilder.build());
    }

    public static void createNotifaction3(Context context,String channelId){
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(context,channelId);
        mBuilder.setSmallIcon(R.drawable.notification_icon);
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_enhanced_encryption_black));
        mBuilder.setContentTitle("新通知Title3");
        mBuilder.setContentText("新通知内容3");
        mBuilder.setAutoCancel(true);


        NotificationCompat.InboxStyle inboxStyle =
                new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        events[0]="第一行，第一行，第一行，第一行，第一行，第一行，第一行";
        events[1]="第一行";
        events[2]="第二行";
        events[3]="第三行";
        events[4]="第四行";
        events[5]="第五行";
// Sets a title for the Inbox in expanded layout
        inboxStyle.setBigContentTitle("Event tracker details:");

// Moves events into the expanded layout
        for (int i=0; i < events.length; i++) {

            inboxStyle.addLine(events[i]);
        }
// Moves the expanded layout object into the notification object.
        mBuilder.setStyle(inboxStyle);

        //禁止滑动删除
        mBuilder.setOngoing(true);
        //取消右上角的时间显示
        mBuilder.setShowWhen(false);

        Intent resultIntent = new Intent(context, NotifactionResultActivity.class);
        Notification notification=mBuilder.build();
        Bundle bundle=new Bundle();
        bundle.putParcelable("notifaction",notification);
        resultIntent.putExtras(bundle);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotifactionResultActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        1,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(101, mBuilder.build());
    }

    public static void simpleNotify(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"simpleNotify");
        //Ticker是状态栏显示的提示
        builder.setTicker("简单Notification");
        //第一行内容  通常作为通知栏标题
        builder.setContentTitle("标题");
        //第二行内容 通常是通知正文
        builder.setContentText("通知内容");
        //第三行内容 通常是内容摘要什么的 在低版本机器上不一定显示
        builder.setSubText("这里显示的是通知第三行内容！");
        //ContentInfo 在通知的右侧 时间的下面 用来展示一些其他信息
        //builder.setContentInfo("2");
        builder.setAutoCancel(true);
        builder.setNumber(2);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.push));
        Intent intent = new Intent(context,NotifactionResultActivity.class);
        Notification notification=builder.build();
        Bundle bundle=new Bundle();
        bundle.putParcelable("notifaction",notification);
        intent.putExtras(bundle);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        //设置震动
        //long[] vibrate = {100,200,100,200};
        //builder.setVibrate(vibrate);

        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(102,builder.build());
    }

    public static void bigTextStyle(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"bigTextStyle");
        builder.setContentTitle("BigTextStyle");
        builder.setContentText("BigTextStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.notification));
        android.support.v4.app.NotificationCompat.BigTextStyle style = new android.support.v4.app.NotificationCompat.BigTextStyle();
        style.bigText("这里是点击通知后要显示的正文，可以换行可以显示很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长");
        style.setBigContentTitle("点击后的标题");
        style.setSummaryText("末尾只一行的文字内容");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(context,NotifactionResultActivity.class);
        Notification notification=builder.build();
        Bundle bundle=new Bundle();
        bundle.putParcelable("notifaction",notification);
        intent.putExtras(bundle);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(103,builder.build());
    }

    /**
     * 最多显示五行 再多会有截断
     */
    public static void inBoxStyle(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"inBoxStyle");
        builder.setContentTitle("InboxStyle");
        builder.setContentText("InboxStyle演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.notification));
        android.support.v4.app.NotificationCompat.InboxStyle style = new android.support.v4.app.NotificationCompat.InboxStyle();
        style.setBigContentTitle("BigContentTitle")
                .addLine("第一行，第一行，第一行，第一行，第一行，第一行，第一行")
                .addLine("第二行")
                .addLine("第三行")
                .addLine("第四行")
                .addLine("第五行")
                .setSummaryText("SummaryText");
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(context,NotifactionResultActivity.class);
        Notification notification=builder.build();
        Bundle bundle=new Bundle();
        bundle.putParcelable("notifaction",notification);
        intent.putExtras(bundle);
        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(104,builder.build());
    }

    public static void bigPictureStyle(Context context){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"bigPictureStyle");
        builder.setContentTitle("BigPictureStyle");
        builder.setContentText("BigPicture演示示例");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.notification));
        android.support.v4.app.NotificationCompat.BigPictureStyle style = new android.support.v4.app.NotificationCompat.BigPictureStyle();
        style.setBigContentTitle("BigContentTitle");
        style.setSummaryText("SummaryText");
        style.bigPicture(BitmapFactory.decodeResource(context.getResources(),R.drawable.small));
        builder.setStyle(style);
        builder.setAutoCancel(true);
        Intent intent = new Intent(context,NotifactionResultActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(context,1,intent,0);
        builder.setContentIntent(pIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(105,builder.build());
    }



}

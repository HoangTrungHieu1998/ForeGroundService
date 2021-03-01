package com.example.foregroundservice;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    String MY_CHANNEL ="MY_CHANNEL";
    NotificationManager notificationManager;
    int REQUEST_CODE = 123;
    NotificationCompat.Builder mNotification;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager  = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = createNotification(this);
        startForeground(1,mNotification.build());
        Log.d("BBB","onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("BBB","onStarCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    // Hàm tạo notification
    private NotificationCompat.Builder createNotification(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("message","Hello Main");
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,REQUEST_CODE , intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder notification = new NotificationCompat.Builder(context,MY_CHANNEL)
                .setContentTitle("Ban cap nhap moi")
                .setContentText("Phien ban app 15.0")
                .setShowWhen(true)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.cho))
                .addAction(R.drawable.ic_launcher_background,"Open App",pendingIntent);

        //Kiểm tra phiên bản máy
        // Máy 26 trở lên mới có
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(
                    MY_CHANNEL, "CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        return notification;
    }
}

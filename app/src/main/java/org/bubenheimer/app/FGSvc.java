/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.app;

import android.Manifest;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class FGSvc extends Service {
    @Override
    public void onCreate() {
        super.onCreate();

        final Notification notification =
                new Notification.Builder(this, getString(R.string.channelid))
                        .setOngoing(true)
                        .setContentTitle("Foreground service in progress")
                        .setSmallIcon(R.drawable.ic_stat_name)
                        .build();
        startForeground(R.id.notification_id_service, notification);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Log.i("FGSvc", "Location permission granted");
                    } else {
                        Log.i("FGSvc", "Location permission denied");
                    }

                    SystemClock.sleep(10_000L);
                }
            }
        }).start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(
            final Intent intent
    ) {
        return null;
    }
}

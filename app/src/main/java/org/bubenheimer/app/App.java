/*
 * Copyright (c) 2015-2016 Uli Bubenheimer. All rights reserved.
 */

package org.bubenheimer.app;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        final NotificationChannel channel = new NotificationChannel(
                getString(R.string.channelid),
                "Bad channel",
                NotificationManager.IMPORTANCE_LOW);
        channel.setDescription("Very bad channel");
        channel.enableLights(false);
        channel.enableVibration(false);
        channel.setShowBadge(true);
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
}

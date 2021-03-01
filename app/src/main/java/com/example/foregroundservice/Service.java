package com.example.foregroundservice;

import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class Service extends android.app.Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

package com.xyz.indicator;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * created by shenyonghui on 2020/6/2
 */
public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

    }
}

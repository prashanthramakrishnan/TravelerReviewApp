package com.prashanth.travelerreviewapp;

import android.app.Application;
import com.prashanth.travelerreviewapp.di.AppDaggerGraph;
import com.prashanth.travelerreviewapp.di.ApplicationModule;
import com.prashanth.travelerreviewapp.di.DaggerAppDaggerGraph;
import com.prashanth.travelerreviewapp.di.NetworkDaggerModule;

public class TravelerReviewBaseApplication extends Application {

    public static AppDaggerGraph component;

    protected DaggerAppDaggerGraph.Builder daggerComponent(TravelerReviewBaseApplication application) {
        return DaggerAppDaggerGraph.builder()
                .networkDaggerModule(new NetworkDaggerModule(BuildConfig.NETWORK_URL))
                .applicationModule(new ApplicationModule(this));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = daggerComponent(this).build();
    }
}

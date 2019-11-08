package com.prashanth.traverlerreviewapp;

import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import timber.log.Timber;

public class TravelerReviewApplication extends TravelerReviewBaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
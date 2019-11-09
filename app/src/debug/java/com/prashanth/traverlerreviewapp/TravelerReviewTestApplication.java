package com.prashanth.traverlerreviewapp;

import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import timber.log.Timber;

public class TravelerReviewTestApplication extends TravelerReviewBaseApplication {

    private static final String apiURL = "http://localhost:8080/";

    @Override
    protected String getUrl() {
        return apiURL;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
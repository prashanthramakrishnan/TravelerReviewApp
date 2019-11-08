package com.prashanth.travelerreviewapp;

import android.app.Application;
import android.content.Context;
import com.prashanth.travelerreviewapp.network.APIFactory;
import com.prashanth.travelerreviewapp.network.TravelerReviewsAPI;

public class TravelerReviewBaseApplication extends Application {

    private TravelerReviewsAPI api;

    private static TravelerReviewBaseApplication get(Context context) {
        return (TravelerReviewBaseApplication) context.getApplicationContext();
    }

    public static TravelerReviewBaseApplication create(Context context) {
        return TravelerReviewBaseApplication.get(context);
    }

    public TravelerReviewsAPI getApi() {
        if (api == null) {
            api = APIFactory.create();
        }
        return api;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
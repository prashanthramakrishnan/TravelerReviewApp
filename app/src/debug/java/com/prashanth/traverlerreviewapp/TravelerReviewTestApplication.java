package com.prashanth.traverlerreviewapp;

import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.di.DaggerAppDaggerGraph;
import com.prashanth.travelerreviewapp.di.NetworkDaggerModule;

public class TravelerReviewTestApplication extends TravelerReviewApplication {

    protected DaggerAppDaggerGraph.Builder daggerComponent(TravelerReviewBaseApplication application) {
        return super.daggerComponent(this)
                .networkDaggerModule(new NetworkDaggerModule("http://localhost:8080/"));
    }
}
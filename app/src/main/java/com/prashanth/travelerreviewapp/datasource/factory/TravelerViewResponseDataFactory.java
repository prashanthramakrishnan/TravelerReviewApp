package com.prashanth.travelerreviewapp.datasource.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.datasource.TravelerReviewResponseDataSource;
import org.jetbrains.annotations.NotNull;

public class TravelerViewResponseDataFactory extends DataSource.Factory {

    private MutableLiveData<TravelerReviewResponseDataSource> mutableLiveData;

    private TravelerReviewBaseApplication travelerReviewBaseApplication;

    public TravelerViewResponseDataFactory(TravelerReviewBaseApplication travelerReviewBaseApplication) {
        this.travelerReviewBaseApplication = travelerReviewBaseApplication;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NotNull
    @Override
    public DataSource create() {
        TravelerReviewResponseDataSource travelerReviewResponseDataSource = new TravelerReviewResponseDataSource(travelerReviewBaseApplication);
        mutableLiveData.postValue(travelerReviewResponseDataSource);
        return travelerReviewResponseDataSource;
    }

    public MutableLiveData<TravelerReviewResponseDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

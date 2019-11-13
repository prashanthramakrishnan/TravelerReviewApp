package com.prashanth.travelerreviewapp.datasource.factory;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.datasource.TravelerReviewResponseDataSource;
import javax.inject.Inject;
import org.jetbrains.annotations.NotNull;

public class TravelerViewResponseDataFactory extends DataSource.Factory {

    MutableLiveData<TravelerReviewResponseDataSource> mutableLiveData = new MutableLiveData<>();

    @Inject
    TravelerReviewResponseDataSource travelerReviewResponseDataSource;

    @Inject
    public TravelerViewResponseDataFactory() {
        TravelerReviewBaseApplication.component.inject(this);
    }

    @NotNull
    @Override
    public DataSource create() {
        mutableLiveData.postValue(travelerReviewResponseDataSource);
        return travelerReviewResponseDataSource;
    }

    public MutableLiveData<TravelerReviewResponseDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}

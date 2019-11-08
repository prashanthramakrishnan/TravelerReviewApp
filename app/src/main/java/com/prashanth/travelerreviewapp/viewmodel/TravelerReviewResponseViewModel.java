package com.prashanth.travelerreviewapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.datasource.TravelerReviewResponseDataSource;
import com.prashanth.travelerreviewapp.datasource.factory.TravelerViewResponseDataFactory;
import com.prashanth.travelerreviewapp.model.Review;
import com.prashanth.travelerreviewapp.utils.DataFetchState;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TravelerReviewResponseViewModel extends ViewModel {

    private LiveData<DataFetchState> dataFetchState;

    private LiveData<PagedList<Review>> reviewLiveData;

    private TravelerReviewBaseApplication travelerReviewBaseApplication;

    public TravelerReviewResponseViewModel(@NonNull TravelerReviewBaseApplication travelerReviewBaseApplication) {
        this.travelerReviewBaseApplication = travelerReviewBaseApplication;
        init();
    }

    private void init() {
        Executor executor = Executors.newFixedThreadPool(5);

        TravelerViewResponseDataFactory travelerViewResponseDataFactory = new TravelerViewResponseDataFactory(travelerReviewBaseApplication);
        dataFetchState = Transformations.switchMap(travelerViewResponseDataFactory.getMutableLiveData(),
                (Function<TravelerReviewResponseDataSource, LiveData<DataFetchState>>) TravelerReviewResponseDataSource::getDataFetchState);

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(0)
                        .setPageSize(10).build();

        reviewLiveData = (new LivePagedListBuilder(travelerViewResponseDataFactory, pagedListConfig))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<DataFetchState> getDataFetchState() {
        return dataFetchState;
    }

    public LiveData<PagedList<Review>> getReviewLiveData() {
        return reviewLiveData;
    }

}

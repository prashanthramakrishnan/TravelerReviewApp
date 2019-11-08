package com.prashanth.travelerreviewapp.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;
import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.model.Review;
import com.prashanth.travelerreviewapp.model.TravelerReviewAPIResponse;
import com.prashanth.travelerreviewapp.utils.DataFetchState;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TravelerReviewResponseDataSource extends PageKeyedDataSource<Long, Review> {

    private MutableLiveData dataFetchState;

    private MutableLiveData initialLoading;

    private TravelerReviewBaseApplication travelerReviewBaseApplication;

    private CompositeDisposable compositeDisposable;

    public TravelerReviewResponseDataSource(TravelerReviewBaseApplication travelerReviewBaseApplication) {
        this.travelerReviewBaseApplication = travelerReviewBaseApplication;
        this.dataFetchState = new MutableLiveData();
        this.initialLoading = new MutableLiveData();
        this.compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData getDataFetchState() {
        return dataFetchState;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Review> callback) {
        initialLoading.postValue(DataFetchState.LOADING);
        dataFetchState.postValue(DataFetchState.LOADING);

        Disposable disposable = travelerReviewBaseApplication.getApi().getReviews(10, params.requestedLoadSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TravelerReviewAPIResponse>() {
                    @Override
                    public void onNext(TravelerReviewAPIResponse travelerReviewAPIResponse) {
                        callback.onResult(travelerReviewAPIResponse.getReviewList(), null, 10L);
                        initialLoading.postValue(DataFetchState.LOADED);
                        dataFetchState.postValue(DataFetchState.LOADED);
                    }

                    @Override
                    public void onError(Throwable e) {
                        initialLoading.postValue(new DataFetchState(DataFetchState.Status.FAILURE, e.getMessage()));
                        dataFetchState.postValue(new DataFetchState(DataFetchState.Status.FAILURE, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        //do nothing
                    }
                });
        compositeDisposable.add(disposable);

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Review> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Review> callback) {
        dataFetchState.postValue(DataFetchState.LOADING);
        Disposable disposable = travelerReviewBaseApplication.getApi().getReviews(params.requestedLoadSize, params.key.intValue())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<TravelerReviewAPIResponse>() {
                    @Override
                    public void onNext(TravelerReviewAPIResponse travelerReviewAPIResponse) {
                        long nextKey = params.key + 10;
                        callback.onResult(travelerReviewAPIResponse.getReviewList(), nextKey);
                        dataFetchState.postValue(DataFetchState.LOADED);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dataFetchState.postValue(new DataFetchState(DataFetchState.Status.FAILURE, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {
                        //do nothing
                    }
                });
        compositeDisposable.add(disposable);
    }
}

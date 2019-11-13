package com.prashanth.travelerreviewapp.di;

import com.prashanth.travelerreviewapp.datasource.TravelerReviewResponseDataSource;
import com.prashanth.travelerreviewapp.datasource.factory.TravelerViewResponseDataFactory;
import com.prashanth.travelerreviewapp.ui.ReviewPreviewActivity;
import com.prashanth.travelerreviewapp.viewmodel.TravelerReviewResponseViewModel;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {NetworkDaggerModule.class, ApplicationModule.class})
public interface AppDaggerGraph {

    void inject(ReviewPreviewActivity activity);

    void inject(TravelerReviewResponseViewModel viewModel);

    void inject(TravelerReviewResponseDataSource dataSource);

    void inject(TravelerViewResponseDataFactory factory);

}
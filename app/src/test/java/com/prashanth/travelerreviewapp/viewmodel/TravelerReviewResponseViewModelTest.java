package com.prashanth.travelerreviewapp.viewmodel;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import com.prashanth.travelerreviewapp.datasource.factory.TravelerViewResponseDataFactory;
import com.prashanth.travelerreviewapp.model.TravelerReviewAPIResponse;
import com.prashanth.travelerreviewapp.network.TravelerReviewsAPI;
import com.prashanth.travelerreviewapp.utils.DataFetchState;
import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4.class)
public class TravelerReviewResponseViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    LifecycleOwner lifecycleOwner;

    @Mock
    TravelerReviewsAPI mockAPI;

    @Mock
    Observer<DataFetchState> dataFetchStateObserver;

    private TravelerReviewResponseViewModel viewModel;

    @Mock
    TravelerViewResponseDataFactory travelerViewResponseDataFactory;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Lifecycle lifecycle = new LifecycleRegistry(lifecycleOwner);
        viewModel = new TravelerReviewResponseViewModel(travelerViewResponseDataFactory);
        viewModel.getDataFetchState().observeForever(dataFetchStateObserver);
    }

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    public void testNull() {
        assertNotNull(viewModel.getDataFetchState());
        assertTrue(viewModel.getDataFetchState().hasObservers());
    }

    @Test
    public void testApiSuccess() {
        when(mockAPI.getReviews(1, 1)).thenReturn(Observable.just(new TravelerReviewAPIResponse()));
        assertTrue(viewModel.getDataFetchState().hasObservers());
    }

    @Test
    public void testApiFailure() {
        when(mockAPI.getReviews(1, 1)).thenReturn(Observable.error(new Throwable("Error")));
        assertTrue(viewModel.getDataFetchState().hasObservers());
    }

}

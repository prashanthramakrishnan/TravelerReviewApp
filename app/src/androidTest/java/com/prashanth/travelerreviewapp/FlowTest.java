package com.prashanth.travelerreviewapp;

import static androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertTrue;

import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.prashanth.travelerreviewapp.ui.ReviewDetailsActivity;
import com.prashanth.travelerreviewapp.ui.ReviewPreviewActivity;
import com.robotium.solo.Solo;
import java.io.IOException;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import timber.log.Timber;

@RunWith(AndroidJUnit4.class)
public class FlowTest {

    private MockWebServer server;

    private Solo solo;

    @Rule
    public ActivityTestRule<ReviewPreviewActivity> rule = new ActivityTestRule<>(ReviewPreviewActivity.class, false, false);

    @Before
    public void setUp() throws Exception {
        setupServer();
        solo = new Solo(getInstrumentation(), rule.getActivity());
    }

    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        server.shutdown();
    }

    @Test()
    public void launchAppTest() throws Throwable {

        //Launch app with the MockServer running
        rule.launchActivity(new Intent());

        introduceDelay(1000L);

        RecyclerView recyclerView = (RecyclerView) solo.getView(R.id.preview_list);

        //scroll to the 5th item
        runOnUiThread(() -> recyclerView.scrollToPosition(5));

        //click on text "Guy"
        solo.clickOnText("Guy");

        introduceDelay(1000L);

        assertTrue(solo.waitForActivity(ReviewDetailsActivity.class.getSimpleName()));

    }

    private void setupServer() throws Exception {

        server = new MockWebServer();
        Dispatcher dispatcher = new Dispatcher() {
            @Override
            public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
                if (request.getMethod().equals("GET")) {

                    String reviewResponse;
                    try {
                        reviewResponse = IOUtils.toString(getInstrumentation().getContext().getResources().getAssets().open("json/review_response.json"));
                        return new MockResponse().setResponseCode(200).setBody(reviewResponse);
                    } catch (IOException e) {
                        Timber.e(e);
                    }
                }
                return null;
            }
        };

        server.setDispatcher(dispatcher);
        server.start(8080);

    }

    private void introduceDelay(long timeout) {
        synchronized (this) {
            try {
                this.wait(timeout);
            } catch (InterruptedException e) {
                Timber.e(e);
            }
        }
    }

}
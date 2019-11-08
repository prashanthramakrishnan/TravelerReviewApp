package com.prashanth.travelerreviewapp.network;

import com.prashanth.travelerreviewapp.model.TravelerReviewAPIResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TravelerReviewsAPI {

    @Headers({"User-Agent: PostmanRuntime/7.17.1"})
    @GET("/travelers-api-forwarding/travelers/activities/23776/reviews")
    Observable<TravelerReviewAPIResponse> getReviews(@Query("limit") int limit, @Query("offset") int offset);

}
package com.prashanth.travelerreviewapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.prashanth.travelerreviewapp.utils.Utils;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
@Setter
public class TravelerReviewAPIResponse implements Parcelable {

    private transient long id;

    @SerializedName("reviews")
    private List<Review> reviewList;

    @SerializedName("totalCount")
    private int totalCount;

    @SerializedName("averageRating")
    private float averageRating;

    @SerializedName("pagination")
    private PaginationModel paginationModel;

    protected TravelerReviewAPIResponse(Parcel in) {
        id = Utils.getRandomNumber();
        reviewList = in.createTypedArrayList(Review.CREATOR);
        totalCount = in.readInt();
        averageRating = in.readFloat();
        paginationModel = in.readParcelable(PaginationModel.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeTypedList(reviewList);
        dest.writeInt(totalCount);
        dest.writeFloat(averageRating);
        dest.writeParcelable(paginationModel, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TravelerReviewAPIResponse> CREATOR = new Creator<TravelerReviewAPIResponse>() {
        @Override
        public TravelerReviewAPIResponse createFromParcel(Parcel in) {
            return new TravelerReviewAPIResponse(in);
        }

        @Override
        public TravelerReviewAPIResponse[] newArray(int size) {
            return new TravelerReviewAPIResponse[size];
        }
    };

    @NotNull
    @Override
    public String toString() {
        return "TravelerReviewAPIResponse{" +
                "reviewList=" + reviewList +
                ", totalCount=" + totalCount +
                ", paginationModel=" + paginationModel +
                '}';
    }

}

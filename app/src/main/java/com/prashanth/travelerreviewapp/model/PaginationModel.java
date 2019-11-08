package com.prashanth.travelerreviewapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class PaginationModel implements Parcelable {

    @SerializedName("limit")
    private int limit;

    @SerializedName("offset")
    private int offset;

    protected PaginationModel(Parcel in) {
        limit = in.readInt();
        offset = in.readInt();
    }

    public static final Creator<PaginationModel> CREATOR = new Creator<PaginationModel>() {
        @Override
        public PaginationModel createFromParcel(Parcel in) {
            return new PaginationModel(in);
        }

        @Override
        public PaginationModel[] newArray(int size) {
            return new PaginationModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(limit);
        dest.writeInt(offset);
    }

    @NotNull
    @Override
    public String toString() {
        return "PaginationModel{" +
                "limit=" + limit +
                ", offset=" + offset +
                '}';
    }
}

package com.prashanth.travelerreviewapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class Author implements Parcelable {

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("photo")
    private String photo;

    protected Author(Parcel in) {
        fullName = in.readString();
        country = in.readString();
        city = in.readString();
        photo = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        @Override
        public Author createFromParcel(Parcel in) {
            return new Author(in);
        }

        @Override
        public Author[] newArray(int size) {
            return new Author[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullName);
        dest.writeString(country);
        dest.writeString(city);
        dest.writeString(photo);
    }

    @NotNull
    @Override
    public String toString() {
        return "Author{" +
                "fullName='" + fullName + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

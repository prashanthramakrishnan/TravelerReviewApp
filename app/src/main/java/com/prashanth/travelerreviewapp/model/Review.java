package com.prashanth.travelerreviewapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
public class Review implements Parcelable {

    @SerializedName("id")
    private long id;

    @SerializedName("author")
    private Author author;

    @SerializedName("title")
    private String title;

    @SerializedName("message")
    private String message;

    @SerializedName("enjoyment")
    private String enjoyment;

    @SerializedName("isAnonymous")
    private boolean isAnonymous;

    @SerializedName("rating")
    private int rating;

    @SerializedName("created")
    private String createdDate;

    @SerializedName("language")
    private String language;

    @SerializedName("travelerType")
    private String travelerType;

    protected Review(Parcel in) {
        id = in.readLong();
        author = in.readParcelable(Author.class.getClassLoader());
        title = in.readString();
        message = in.readString();
        enjoyment = in.readString();
        isAnonymous = in.readByte() != 0;
        rating = in.readInt();
        createdDate = in.readString();
        language = in.readString();
        travelerType = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeParcelable(author, flags);
        dest.writeString(title);
        dest.writeString(message);
        dest.writeString(enjoyment);
        dest.writeByte((byte) (isAnonymous ? 1 : 0));
        dest.writeInt(rating);
        dest.writeString(createdDate);
        dest.writeString(language);
        dest.writeString(travelerType);
    }

    public static DiffUtil.ItemCallback<Review> DIFF_CALLBACK = new DiffUtil.ItemCallback<Review>() {
        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NotNull
    @Override
    public String toString() {
        return "Review{" +
                "id='" + id + '\'' +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        Review review = (Review) obj;
        return review.id == this.id;
    }
}

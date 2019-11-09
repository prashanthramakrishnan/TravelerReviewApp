package com.prashanth.travelerreviewapp.ui;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.prashanth.travelerreviewapp.R;
import com.prashanth.travelerreviewapp.databinding.ReviewDetailsBinding;
import com.prashanth.travelerreviewapp.model.Review;
import com.prashanth.travelerreviewapp.utils.Utils;

public class ReviewDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReviewDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_review_details);

        Review review = getIntent().getExtras().getParcelable(Utils.REVIEW_DETAILS);

        if (review != null) {

            String photoUrl = review.getAuthor() == null || review.getAuthor().getPhoto() == null ||
                    review.getAuthor().getPhoto().isEmpty() ? null : review.getAuthor().getPhoto();

            String author =
                    review.getAuthor() == null || review.getAuthor().getFullName() == null ||
                            review.getAuthor().getFullName().isEmpty() ? getString(
                            R.string.empty) : review.getAuthor().getFullName();

            String city = review.getAuthor() == null || review.getAuthor().getCity() == null ||
                    review.getAuthor().getCity().isEmpty() ? getString(
                    R.string.empty) : review.getAuthor().getCity();

            String country = review.getAuthor() == null || review.getAuthor().getCountry() == null ||
                    review.getAuthor().getCountry().isEmpty() ? getString(
                    R.string.empty) : review.getAuthor().getCountry();

            String title = review.getTitle() == null || review.getTitle().isEmpty() ? getString(
                    R.string.empty) : review.getTitle();

            String reviewMessage = review.getMessage() == null || review.getMessage().isEmpty() ? getString(
                    R.string.empty) : review.getMessage();

            String enjoyment = review.getEnjoyment() == null || review.getEnjoyment().isEmpty() ? getString(
                    R.string.empty) : review.getEnjoyment();

            String tripType = review.getTravelerType() == null || review.getTravelerType().isEmpty() ? getString(
                    R.string.empty) : review.getTravelerType();

            String reviewedDate = review.getCreatedDate() == null || review.getCreatedDate().isEmpty() ?
                    getString(R.string.empty) : review.getCreatedDate();

            int rating = review.getRating();

            if (photoUrl != null) {
                binding.authorPhoto.setVisibility(View.VISIBLE);

                Glide.with(this)
                        .load(photoUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(binding.authorPhoto);
            }

            binding.authorValue.setText(author);

            if (getString(R.string.empty).equals(reviewMessage)) {
                binding.reviewMessageLayout.setVisibility(View.GONE);
            } else {
                binding.reviewMessageValue.setText(reviewMessage);
            }

            if (getString(R.string.empty).equals(country)) {
                binding.countryLayout.setVisibility(View.GONE);
            } else {
                binding.countryValue.setText(country);
            }

            if (getString(R.string.empty).equals(city)) {
                binding.cityLayout.setVisibility(View.GONE);
            } else {
                binding.cityValue.setText(city);
            }

            if (getString(R.string.empty).equals(title)) {
                binding.titleLayout.setVisibility(View.GONE);
            } else {
                binding.titleValue.setText(title);
            }

            if (getString(R.string.empty).equals(enjoyment)) {
                binding.enjoymentLayout.setVisibility(View.GONE);
            } else {
                binding.enjoymentValue.setText(enjoyment);
            }

            if (getString(R.string.empty).equals(tripType)) {
                binding.travelerTypeLayout.setVisibility(View.GONE);
            } else {
                binding.travelerTypeValue.setText(tripType);
            }

            if (Utils.parseDate(reviewedDate) != null) {
                binding.reviewedDate.setText(Utils.parseDate(reviewedDate));
            }
            binding.detailsRatingStars.setRating(rating);

        }

    }

}

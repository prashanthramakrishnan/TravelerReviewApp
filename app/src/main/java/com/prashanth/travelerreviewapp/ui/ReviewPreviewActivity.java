package com.prashanth.travelerreviewapp.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.prashanth.travelerreviewapp.R;
import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.adapter.TravelerReviewListAdapter;
import com.prashanth.travelerreviewapp.databinding.ReviewPreviewBinding;
import com.prashanth.travelerreviewapp.model.Review;
import com.prashanth.travelerreviewapp.utils.Utils;
import com.prashanth.travelerreviewapp.viewmodel.TravelerReviewResponseViewModel;
import javax.inject.Inject;

public class ReviewPreviewActivity extends AppCompatActivity implements TravelerReviewListAdapter.OnReviewItemClickListener {

    private TravelerReviewListAdapter adapter;

    @Inject
    TravelerReviewResponseViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TravelerReviewBaseApplication.component.inject(this);

        ReviewPreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_review_preview);

        binding.tourReviewHeading.setText(R.string.tour_review_title);

        binding.previewList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TravelerReviewListAdapter(this);
        adapter.setOnReviewItemClickListener(this);

        viewModel.getReviewLiveData().observe(this, pagedList -> adapter.submitList(pagedList));

        viewModel.getDataFetchState().observe(this, dataFetchState -> adapter.setDataFetchState(dataFetchState));

        binding.previewList.setAdapter(adapter);
    }

    @Override
    public void itemClicked(Review review) {
        Intent intent = new Intent(this, ReviewDetailsActivity.class);
        intent.putExtra(Utils.REVIEW_DETAILS, review);
        startActivity(intent);
    }
}

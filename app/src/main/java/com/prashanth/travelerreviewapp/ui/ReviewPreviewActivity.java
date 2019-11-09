package com.prashanth.travelerreviewapp.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.prashanth.travelerreviewapp.R;
import com.prashanth.travelerreviewapp.TravelerReviewBaseApplication;
import com.prashanth.travelerreviewapp.adapter.TravelerReviewListAdapter;
import com.prashanth.travelerreviewapp.databinding.ReviewPreviewBinding;
import com.prashanth.travelerreviewapp.viewmodel.TravelerReviewResponseViewModel;

public class ReviewPreviewActivity extends AppCompatActivity {

    private TravelerReviewListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ReviewPreviewBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_review_preview);

        TravelerReviewResponseViewModel viewModel = new TravelerReviewResponseViewModel(TravelerReviewBaseApplication.create(this));

        binding.tourReviewHeading.setText(R.string.tour_review_title);

        binding.previewList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TravelerReviewListAdapter(this);

        viewModel.getReviewLiveData().observe(this, pagedList -> adapter.submitList(pagedList));

        viewModel.getDataFetchState().observe(this, dataFetchState -> adapter.setDataFetchState(dataFetchState));

        binding.previewList.setAdapter(adapter);
    }
}

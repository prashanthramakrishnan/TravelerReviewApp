package com.prashanth.travelerreviewapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.prashanth.travelerreviewapp.R;
import com.prashanth.travelerreviewapp.databinding.ItemLoadingBinding;
import com.prashanth.travelerreviewapp.databinding.ReviewItemDataBinding;
import com.prashanth.travelerreviewapp.model.Review;
import com.prashanth.travelerreviewapp.utils.DataFetchState;

public class TravelerReviewListAdapter extends PagedListAdapter<Review, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;

    private static final int TYPE_ITEM = 1;

    private Context context;

    private DataFetchState dataFetchState;

    private TravelerReviewListAdapter.OnReviewItemClickListener onReviewItemClickListener;

    public TravelerReviewListAdapter(Context context) {
        super(Review.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            ItemLoadingBinding itemLoadingBinding = ItemLoadingBinding.inflate(layoutInflater, parent, false);
            return new ItemLoadingStateViewHolder(itemLoadingBinding);

        } else {
            ReviewItemDataBinding reviewItemDataBinding = ReviewItemDataBinding.inflate(layoutInflater, parent, false);
            return new ReviewPreviewItemViewHolder(reviewItemDataBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ReviewPreviewItemViewHolder) {
            ((ReviewPreviewItemViewHolder) holder).bindTo(getItem(position));
            holder.itemView.setOnClickListener(v -> {
                if (onReviewItemClickListener != null) {
                    onReviewItemClickListener.itemClicked(getItem(position));
                }
            });
        } else {
            ((ItemLoadingStateViewHolder) holder).bindView(dataFetchState);
        }
    }

    private boolean stillLoading() {
        return dataFetchState != null && dataFetchState != DataFetchState.LOADED;
    }

    @Override
    public int getItemViewType(int position) {
        if (stillLoading() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setDataFetchState(DataFetchState newDataFetchState) {
        DataFetchState previousState = this.dataFetchState;
        boolean previousExtraRow = stillLoading();
        this.dataFetchState = newDataFetchState;
        boolean newExtraRow = stillLoading();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newDataFetchState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

    class ItemLoadingStateViewHolder extends RecyclerView.ViewHolder {

        private ItemLoadingBinding binding;

        ItemLoadingStateViewHolder(ItemLoadingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindView(DataFetchState dataFetchState) {
            if (dataFetchState != null && dataFetchState.getStatus() == DataFetchState.Status.LOADING) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }

            if (dataFetchState != null && dataFetchState.getStatus() == DataFetchState.Status.FAILURE) {
                binding.error.setVisibility(View.VISIBLE);
                binding.error.setText(dataFetchState.getMessage());
            } else {
                binding.error.setVisibility(View.GONE);
            }
        }
    }

    class ReviewPreviewItemViewHolder extends RecyclerView.ViewHolder {

        private ReviewItemDataBinding binding;

        ReviewPreviewItemViewHolder(ReviewItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bindTo(Review review) {
            binding.authorId.setVisibility(View.VISIBLE);
            binding.ratingStars.setVisibility(View.VISIBLE);

            String author =
                    review.getAuthor() == null || review.getAuthor().getFullName() == null || review.getAuthor().getFullName().isEmpty() ? context.getString(
                            R.string.empty) :
                            review.getAuthor().getFullName();
            String reviewMessage = review.getMessage() == null || review.getMessage().isEmpty() ? context.getString(
                    R.string.empty) : review.getMessage();
            int rating = review.getRating();

            binding.ratingStars.setRating(rating);
            binding.authorValue.setText(author);
            binding.reviewMessageValue.setText(reviewMessage);
        }
    }

    public interface OnReviewItemClickListener {

        void itemClicked(Review review);
    }

    public void setOnReviewItemClickListener(TravelerReviewListAdapter.OnReviewItemClickListener onReviewItemClickListener) {
        this.onReviewItemClickListener = onReviewItemClickListener;
    }

}

package com.andrei.traveljournal;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TripDataViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public ImageView mFavourites;
    public TextView mTripName;
    public TextView mDestination;
    public TextView mRatings;

    public TripDataViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.mImageView);
        mFavourites = itemView.findViewById(R.id.mFavourites);
        mTripName = itemView.findViewById(R.id.mTripName);
        mDestination = itemView.findViewById(R.id.mDestination);
        mRatings = itemView.findViewById(R.id.mRatings);
    }

}

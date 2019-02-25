package com.andrei.traveljournal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DecimalFormat;
import java.util.List;

public class TripDataAdapter extends RecyclerView.Adapter<TripDataViewHolder> {

    private List<TripData> mTripCards;
    private final LayoutInflater mInflater;

    /*public TripDataAdapter(List<TripData>mTripCards) {
        this.mTripCards = mTripCards;
    }*/

    public TripDataAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public TripDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.card_trip_item, viewGroup, false);
        return new TripDataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TripDataViewHolder tripDataViewHolder, int i) {
        final TripData currentTripData = mTripCards.get(i);

        DecimalFormat df = new DecimalFormat("#.#");

        tripDataViewHolder.mTripName.setText(currentTripData.getName());
        tripDataViewHolder.mDestination.setText(currentTripData.getDestination());
        tripDataViewHolder.mRatings.setText(df.format(currentTripData.getRating()));
        //tripDataViewHolder.mImageView.setImageDrawable(currentTripData.getmDrawable());

        if (currentTripData.isFavourite()) {
            tripDataViewHolder.mFavourites.setImageResource(R.drawable.ic_turned_in);
        } else {
            tripDataViewHolder.mFavourites.setImageResource(R.drawable.ic_turned_in_not);
        }

        tripDataViewHolder.mFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mTripCards.indexOf(currentTripData);
                if (currentTripData.isFavourite()) {
                    mTripCards.get(position).setFavourite(false);
                } else {
                    mTripCards.get(position).setFavourite(true);
                }
                notifyItemChanged(position);
            }
        });
    }

    void setTripsData(List<TripData> tripsData) {
        mTripCards = tripsData;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTripCards != null)
            return mTripCards.size();
        return 0;
    }
}

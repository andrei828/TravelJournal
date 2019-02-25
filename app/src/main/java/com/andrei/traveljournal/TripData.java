package com.andrei.traveljournal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

@Entity(tableName = "trips_table")
public class TripData {

    @PrimaryKey(autoGenerate = true)
    private int Id;

    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "destination")
    private String Destination;

    @ColumnInfo(name = "rating")
    private double Rating;

    @ColumnInfo(name = "isFavourite")
    private boolean Favourite;

    @Ignore
    private Drawable mDrawable;

    public TripData() { }

    public TripData(String name, String destination, double rating, boolean favourite) {
        Name = name;
        Destination = destination;
        Rating = rating;
        Favourite = favourite;
    }

    public TripData(String Name, String Destination, double Rating, boolean Favourite, Drawable Drawable) {
        this.Name = Name;
        this.Destination = Destination;
        this.Rating = Rating;
        this.Favourite = Favourite;
        this.mDrawable = Drawable;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getDestination() {
        return Destination;
    }

    public double getRating() {
        return Rating;
    }

    public boolean isFavourite() {
        return Favourite;
    }

    public void setFavourite(boolean mFavourite) {
        this.Favourite = mFavourite;
    }

    public Drawable getmDrawable() {
        return mDrawable;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public void setRating(double rating) {
        Rating = rating;
    }

    public void setmDrawable(Drawable mDrawable) {
        this.mDrawable = mDrawable;
    }

    @Override
    public String toString() {
        return "TripData{" +
                "mName='" + Name + '\'' +
                ", mDestination='" + Destination + '\'' +
                ", mRating=" + Rating +
                ", mFavourite=" + Favourite +
                ", mImageView=" + mDrawable.toString() +
                '}';
    }
}

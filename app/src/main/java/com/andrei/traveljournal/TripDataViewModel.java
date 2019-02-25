package com.andrei.traveljournal;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.widget.TableRow;

import java.util.List;

public class TripDataViewModel extends AndroidViewModel {

    private TripDataRepository mRepository;

    private LiveData<List<TripData>> mAllTripsData;

    public TripDataViewModel(Application application) {
        super(application);
        mRepository = new TripDataRepository(application);
        mAllTripsData = mRepository.getAllTripsData();
    }

    LiveData<List<TripData>> getAllTripsData() {
        return mAllTripsData;
    }

    public void insert(TripData tripData) {
        mRepository.insert(tripData);
    }

    public void update(TripData tripData) {
        mRepository.update(tripData);
    }
}

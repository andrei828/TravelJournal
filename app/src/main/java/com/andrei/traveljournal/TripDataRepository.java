package com.andrei.traveljournal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class TripDataRepository {

    private TripDataDao mTripDataDao;
    private LiveData<List<TripData>> mAllTripsData;

    TripDataRepository(Application application) {
        TripDataDatabase db = TripDataDatabase.getDatabase(application);
        mTripDataDao = db.tripDataDao();
        mAllTripsData = mTripDataDao.getTripsData();
    }

    LiveData<List<TripData>> getAllTripsData() {
        return mAllTripsData;
    }

    public void insert(TripData tripData) {
        new insertAsyncTask(mTripDataDao).execute(tripData);
    }

    public void update(TripData tripData) {
        new updateAsyncTask(mTripDataDao).execute(tripData);
    }

    private static class updateAsyncTask extends AsyncTask<TripData, Void, Void> {

        private TripDataDao mAsyncTaskDao;

        updateAsyncTask(TripDataDao dao) { mAsyncTaskDao = dao; }

        @Override
        protected Void doInBackground(final TripData... params) {
            mAsyncTaskDao.updateTripData(params[0]);
            return null;
        }
    }

    private static class insertAsyncTask extends AsyncTask<TripData, Void, Void> {

        private TripDataDao mAsyncTaskDao;

        insertAsyncTask(TripDataDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TripData... params) {
            mAsyncTaskDao.insertTripData(params[0]);
            return null;
        }
    }
}

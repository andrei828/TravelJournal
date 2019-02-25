package com.andrei.traveljournal;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {TripData.class}, version = 1)
public abstract class TripDataDatabase extends RoomDatabase {

    public abstract TripDataDao tripDataDao();

    private static TripDataDatabase INSTANCE;

    public static TripDataDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TripDataDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TripDataDatabase.class, "trips_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TripDataDao mDao;

        PopulateDbAsync(TripDataDatabase db) {
            mDao = db.tripDataDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            /*mDao.deleteAll();
            mDao.insertTripData(new TripData("Holiday 2019", "Pompeii", 4.3, true));
            mDao.insertTripData(new TripData("Summer 2018", "Rome", 2.2, false));
            mDao.insertTripData(new TripData("Autumn 2017", "Luxembourg", 3.9, true));*/
            return null;
        }
    }
}

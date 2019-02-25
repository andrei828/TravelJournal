package com.andrei.traveljournal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecycleViewTripCards;
    private TripDataViewModel mTripDataViewModel;

    public static final int NEW_TRIP_DATA_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManageTrip.class);
                startActivityForResult(intent, NEW_TRIP_DATA_ACTIVITY_REQUEST_CODE);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // get recycler view from xml layout
        mRecycleViewTripCards = findViewById(R.id.recycle_view_trip_cards);

        // define and set layout manager
        mRecycleViewTripCards.setLayoutManager(new LinearLayoutManager(this));

        // get the adapter instance
        final TripDataAdapter tripDataAdapter = new TripDataAdapter(this);

        // set the adapter to the recycler view
        mRecycleViewTripCards.setAdapter(tripDataAdapter);

        mTripDataViewModel = ViewModelProviders.of(this).get(TripDataViewModel.class);
        mTripDataViewModel.getAllTripsData().observe(this, new Observer<List<TripData>>() {
            @Override
            public void onChanged(@Nullable List<TripData> tripsData) {
                // Update the cached copy of the trips in the adapter
                tripDataAdapter.setTripsData(tripsData);
            }
        });

    }

    private List<TripData> GenerateFavouriteTripCards(List<TripData> TripCards)
    {
        List<TripData> favouriteTrips = new ArrayList<>();

        for (TripData Trip: TripCards) {
            if (Trip.isFavourite()) {
                favouriteTrips.add(Trip);
            }
        }

        return favouriteTrips;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TRIP_DATA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            TripData tripData = new TripData(
                    data.getStringExtra(ManageTrip.EXTRA_REPLY_NAME),
                    data.getStringExtra(ManageTrip.EXTRA_REPLY_DESTINATION),
                    data.getDoubleExtra(ManageTrip.EXTRA_REPLY_NUM_OF_STARS, -1),
                    false);

            mTripDataViewModel.insert(tripData);
        } else {
            Toast.makeText(getApplicationContext(), "Empty! Not saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
      /*  int id = item.getItemId();

        // get data source
        mTripCards = GenerateTripCards();

        if (id == R.id.home) {
            // get the adapter instance
            TripDataAdapter tripDataAdapter = new TripDataAdapter(mTripCards);

            // set the adapter to the recycler view
            mRecycleViewTripCards.setAdapter(tripDataAdapter);
        } else if (id == R.id.favourite) {
            // get data source
            mTripCards = GenerateFavouriteTripCards(mTripCards);

            // get the adapter instance
            TripDataAdapter tripDataAdapter = new TripDataAdapter(mTripCards);

            // set the adapter to the recycler view
            mRecycleViewTripCards.setAdapter(tripDataAdapter);
        } else if (id == R.id.about_us) {

        } else if (id == R.id.contact) {

        }
*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

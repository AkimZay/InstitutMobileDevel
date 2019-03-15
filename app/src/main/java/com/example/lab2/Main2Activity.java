package com.example.lab2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            } else {
                getLocation();
            }
        }

        FavBook fragment = new FavBook();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.addBook:
                    AddBook addBook = new AddBook();
                    Main2Activity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, addBook)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.favBook:
                    FavBook favBook = new FavBook();
                    Main2Activity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, favBook)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.infoBook:
                    InfoBook infoBook = new InfoBook();
                    Main2Activity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, infoBook)
                            .addToBackStack(null)
                            .commit();
                    break;
                case R.id.infoAuth:
                    InfoAuth infoAuth = new InfoAuth();
                    Main2Activity.this.getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content_frame, infoAuth)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
            menuItem.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(this,
                                Locale.getDefault());
                        try {
                            Address address =
                                    geocoder.getFromLocation(location.getLatitude(),
                                            location.getLongitude(), 1).get(0);
                            Log.i("Location", "my location is " +
                                    address.getLocality());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

}
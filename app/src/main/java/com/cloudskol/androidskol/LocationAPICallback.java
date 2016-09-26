package com.cloudskol.androidskol;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * @author tham
 */
public class LocationAPICallback implements ConnectionCallbacks,
        OnConnectionFailedListener, LocationListener {

    private final String LOG_TAG = LocationAPICallback.class.getSimpleName();

    private Activity activity;
    private GoogleApiClient googleApiClient;

    private int MY_PERMISSION;

    public LocationAPICallback(Activity activity) {
        this.activity = activity;
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v(LOG_TAG, "onConnected");

//        Log.v(LOG_TAG, "Fine location: " + ActivityCompat.checkSelfPermission(activity,
//                android.Manifest.permission.ACCESS_FINE_LOCATION));
//
//        Log.v(LOG_TAG, "Coarse location: " + ActivityCompat.checkSelfPermission(activity,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION));

//        if (ActivityCompat.checkSelfPermission(context,
//                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Log.v(LOG_TAG, "Inside the permission check");
//            return;
//        }

        requestPermission();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.v(LOG_TAG, "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v(LOG_TAG, "onConnectionFailed");
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v(LOG_TAG, "onLocationChanged");

        final double latitude = location.getLatitude();
        final double longtitude = location.getLongitude();

        Log.v(LOG_TAG, "Latitude: " + latitude);
        Log.v(LOG_TAG, "Longtitude: " + longtitude);
    }

    public void connect() {
        googleApiClient.connect();
    }

    public void disconnect() {
        googleApiClient.disconnect();
    }

    private boolean hasRequiredPermission() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
//        if (hasRequiredPermission()) {
//            return;
//        }



        final LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION);

            Log.v(LOG_TAG, "Permission: " + MY_PERMISSION);
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }
}

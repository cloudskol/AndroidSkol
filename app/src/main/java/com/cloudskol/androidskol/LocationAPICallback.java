package com.cloudskol.androidskol;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.v(LOG_TAG, "onConnected");

        final LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10);

//        LocationServices.FusedLocationApi.removeLocationUpdates()
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
}

package hk.hku.cs.mywomensaftyapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


public class GPSLocation extends Service implements LocationListener {
    LocationManager manager;
    Context context;
    Location location;


    // The minimum distance to change Updates in meters
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

    public GPSLocation(Context context) {
        this.context = context;
        getLocation();
    }

    @SuppressLint("MissingPermission")
    public String getLocation() {
        try {
            manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

            boolean isGPSEnable = manager.isProviderEnabled(manager.GPS_PROVIDER);

            if (isGPSEnable) {
                if (location == null) {
                    manager.requestLocationUpdates(
                            manager.GPS_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                    Log.d("GPS Enabled", "GPS Enabled");
                    if (manager != null) {
                        location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            return "Altitude:" + location.getAltitude() + "  Longitude: " + location.getLongitude();
                        }
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return "Altitude:" + location.getAltitude() + "  Longitude: " + location.getLongitude();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }
}

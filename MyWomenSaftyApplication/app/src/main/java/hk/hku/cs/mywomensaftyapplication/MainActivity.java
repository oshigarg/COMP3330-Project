package hk.hku.cs.mywomensaftyapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView result;
    LocationManager locationManager;
    String locationString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.textView);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnable = locationManager.isProviderEnabled(locationManager.GPS_PROVIDER);

        LocationListener locationListener = new MyLocationListener();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);

    }

    public void showLocation(android.view.View v){
        result.setText(locationString);
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location loc) {
            locationString = loc.getLatitude() + ", " + loc.getLongitude();
//            result.setText(loc.getLongitude() + ", " + loc.getLatitude());
        }
    }

}
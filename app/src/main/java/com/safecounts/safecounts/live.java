package com.safecounts.safecounts;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class live extends AppCompatActivity implements LocationListener{

    MainActivity forDb = new MainActivity();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        this.onLocationChanged(null);
    }

    @Override
    public void onLocationChanged(Location location) {
        TextView speed =(TextView)this.findViewById(R.id.Speed_text);
        if(location==null)
        {
            speed.setText(" -.- kmph");
        }
        else
        {
            float currentSpeed=location.getSpeed();
            if(currentSpeed!=0) {
                double currentLatitude = location.getLatitude();
                double currentLongitude = location.getLongitude();
                String str;
                DecimalFormat df = new DecimalFormat("#.##");
                speed.setText(df.format(currentSpeed) + " kmph");
                Date currentTime = Calendar.getInstance().getTime();
                forDb.myDb.insertData(currentTime.toString(), Float.toString(currentSpeed), Double.toString(currentLatitude), Double.toString(currentLongitude));
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

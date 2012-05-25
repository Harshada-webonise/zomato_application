package com.pack.Zomato;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LocationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener ll = new mylocationlistener();
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, ll);
        
        Intent intent=new Intent(LocationActivity.this,Login.class);
        startActivity(intent);
        
   
    }
    
    
    private class mylocationlistener implements LocationListener {
    	@Override
        public void onLocationChanged(Location location) {
            if (location != null) {
            Log.d("LOCATION CHANGED", location.getLatitude() + "");
            Log.d("LOCATION CHANGED", location.getLongitude() + "");
            Toast.makeText(LocationActivity.this,
                location.getLatitude() + "" + location.getLongitude(),
                Toast.LENGTH_LONG).show();
            }
        }
        
    	@Override
        public void onProviderDisabled(String provider) {
        }
        
        @Override
        public void onProviderEnabled(String provider) {
        }
        
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        }
    
    	
    
    
}
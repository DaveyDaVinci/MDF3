package com.example.photoop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GPSActivity extends Activity implements OnClickListener, LocationListener{

	LocationManager locationManager;
	
	Button getLocationButton;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		getLocationButton = (Button) findViewById(R.id.getLocationButton);
		
		
		
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		if (locationManager == null)
		{
			Toast newToast = Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT);
			newToast.show();
		}
		else
		{
			getLocationButton.setOnClickListener(this);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.g, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.equals(this.getLocationButton))
		{
			long time = 3*1000;
			float speed = 0;
			
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, time, speed, this);
		}
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
		
		String uri = "geo:" + String.valueOf(location.getLatitude()) + "," + 
				String.valueOf(location.getLongitude() + "?z=17");
		
		Intent mapsIntent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
		
		startActivity(mapsIntent);
		
		
		
		
		
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}

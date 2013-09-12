package com.example.photoop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

public class GPSActivity extends Activity implements OnClickListener, LocationListener, SensorEventListener{

	private SensorManager sensorManager;
	
	private Sensor lightSensor;
	private Sensor airPressureSensor;
	private Sensor temperatureSensor;
	private Sensor humiditySensor;
	
	static LocationManager locationManager;
	
	static Button getLocationButton;
	
	static Button getSensorsButton;
	
	static TextView lightValue;
	static TextView airPressureValue;
	static TextView temperatureValue;
	static TextView humidityValue;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		getLocationButton = (Button) findViewById(R.id.getLocationButton);
		
		getSensorsButton = (Button) findViewById(R.id.getSensorsButton);
		getSensorsButton.setOnClickListener(this);
		
		lightValue = (TextView) findViewById(R.id.lightValue);
		airPressureValue = (TextView) findViewById(R.id.airPressureValue);
		temperatureValue = (TextView) findViewById(R.id.temperatureValue);
		humidityValue = (TextView) findViewById(R.id.humidityValue);
		
		
		
		
		
		
		
		
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
		if (v.equals(GPSActivity.getLocationButton))
		{
			long time = 3*1000;
			float speed = 0;
			
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, time, speed, this);
		}
		else if (v.equals(GPSActivity.getSensorsButton))
		{
			sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
			
			lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
			airPressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
			temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
			humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
			
			if (lightSensor == null)
			{
				lightValue.setText("Not available");
			}
			else 
			{
				sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_UI);
			}
			
			if (airPressureSensor == null)
			{
				airPressureValue.setText("Not available");
				
			}
			else 
			{
				sensorManager.registerListener(this, airPressureSensor, SensorManager.SENSOR_DELAY_UI);
			}
			
			if (temperatureSensor == null)
			{
				temperatureValue.setText("Not available");
			}
			else 
			{
				sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_UI);
			}
			
			if (humiditySensor == null)
			{
				humidityValue.setText("Not available");
			}
			else
			{
				sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_UI);
			}
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		
		if (event.sensor == lightSensor)
		{
			lightValue.setText(String.valueOf(event.values[0]));
		}
		else if (event.sensor == airPressureSensor)
		{
			airPressureValue.setText(String.valueOf(event.values[0]));
		}
		else if (event.sensor == temperatureSensor)
		{
			temperatureValue.setText(String.valueOf(event.values[0]));
		}
		else if (event.sensor == humiditySensor)
		{
			humidityValue.setText(String.valueOf(event.values[0]));
		}
	}

}

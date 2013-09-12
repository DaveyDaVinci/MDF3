package com.example.photoop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
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
	static Button getBatteryLevelButton;
	static Button getSensorsButton;
	
	static TextView lightValue;
	static TextView airPressureValue;
	static TextView temperatureValue;
	static TextView humidityValue;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gps);
		
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		getLocationButton = (Button) findViewById(R.id.getLocationButton);
		
		getSensorsButton = (Button) findViewById(R.id.getSensorsButton);
		getSensorsButton.setOnClickListener(this);
		
		getBatteryLevelButton = (Button) findViewById(R.id.batteryTestButton);
		getBatteryLevelButton.setOnClickListener(this);
		
		lightValue = (TextView) findViewById(R.id.lightValue);
		airPressureValue = (TextView) findViewById(R.id.airPressureValue);
		temperatureValue = (TextView) findViewById(R.id.temperatureValue);
		humidityValue = (TextView) findViewById(R.id.humidityValue);
		
		
		
		
		
		
		
		//Creates an instance of the location manager
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		if (locationManager == null)
		{
			//Makes a toast if unable to get location
			Toast newToast = Toast.makeText(this, "Unable to get location", Toast.LENGTH_SHORT);
			newToast.show();
		}
		else
		{
			//Sets on click listener
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
	//On click methods
	public void onClick(View v) {
		//If get location button is clicked
		if (v.equals(GPSActivity.getLocationButton))
		{
			//Creates two variables that are plugged in to the lm request
			long time = 3*1000;
			float speed = 0;
			
			//Requests location updates, passing in the provider, time, speed and context as arguments.  
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, time, speed, this);
		}
		//If its' the sensors button that's clicked
		else if (v.equals(GPSActivity.getSensorsButton))
		{
			//Creates a sensor manager and individual sensors
			sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
			
			lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
			airPressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
			temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
			humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
			
			//Checks to see if the sensors are null, or displays data, respectively
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
		
		//If get battery level button is clicked
		else if (v.equals(GPSActivity.getBatteryLevelButton))
		{
			//Creates and intent filter and an intent that checks for the battery changing.  
			IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
			Intent batteryIntent = this.registerReceiver(null, intentFilter);
			int batteryLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
			int maxLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
			
			if ((batteryLevel > 0) && (maxLevel > 0))
			{
				int batteryPercent = (batteryLevel * 100) / maxLevel;
				changeScreenBrightness(batteryPercent);
			}
			
		}
		
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		
		//Creates a uri to Maps, using the user's location
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
		
		//CHanges the values of the sensors
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
	
	//Method constructed to change screen brightness based on battery level
	private void changeScreenBrightness(int battLevel)
	{
		WindowManager.LayoutParams layout = getWindow().getAttributes();
		
		//Checks battery level, and applies brightness settings accordingly
		if (battLevel > 90)
		{
			layout.screenBrightness = .9F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 80 && battLevel <= 90)
		{
			layout.screenBrightness = .8F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 70 && battLevel <= 80)
		{
			layout.screenBrightness = .7F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 60 && battLevel <= 70)
		{
			layout.screenBrightness = .6F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 50 && battLevel <= 60)
		{
			layout.screenBrightness = .5F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 40 && battLevel <= 50)
		{
			layout.screenBrightness = .4F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 30 && battLevel <= 40)
		{
			layout.screenBrightness = .3F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 20 && battLevel <= 30)
		{
			layout.screenBrightness = .2F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 10 && battLevel <= 20)
		{
			layout.screenBrightness = .1F;
			getWindow().setAttributes(layout);
		}
		else if (battLevel > 0 && battLevel <= 10)
		{
			layout.screenBrightness = 0F;
			getWindow().setAttributes(layout);
		}
	}

}

package com.fullsail.magee_david_launcherproject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import connectionwork.ConnectionWork;

public class LauncherActivity extends Activity {

	
	static Context context;
	
	static boolean connection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher_activity);
		
		context = this;
		
		
		
		connection = ConnectionWork.getStatusOfConnection(context);
				
		if (connection == true)
		{
			//getCardsAndValues();
			
			getxkcdComic();
		}
		else 
		{
			
			//String resultsDataString = SaveClass.readStringData(context, "saveddata");
			
			//String resultsDataString = SaveSingleton.readStringData(context, "saveddata");
			
			//if (resultsDataString != null && !resultsDataString.isEmpty() )
			//{
				//Toast toast = Toast.makeText(context, "NO CONNECTION AVAILABLE, DEFAULT VALUES LOADED",  Toast.LENGTH_SHORT);
				//toast.show();
				
				//parseData(resultsDataString);
			//}
			//else
			//{
				//Toast secondToast = Toast.makeText(context, "NO DEFAULT VALUES TO LOAD",  Toast.LENGTH_SHORT);
				//secondToast.show();
			//}
			
		}
		
		Button launchButton = (Button) findViewById(R.id.launchButton);
		
		launchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent secondApp = new Intent(Intent.ACTION_MAIN);
				secondApp.addCategory(Intent.CATEGORY_LAUNCHER);
				secondApp.setComponent(new ComponentName("com.fullsail.magee_david_secondproject", 
						"com.fullsail.magee_david_secondproject.LaunchedActivity"));
				secondApp.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PackageManager theManager = getPackageManager();
				List<ResolveInfo> activities = theManager.queryIntentActivities(secondApp, 0);
				
				startActivity(secondApp);
				
				
				boolean isIntentSafe = activities.size() > 0;
				
				if (isIntentSafe)
				{
					startActivity(secondApp);
					
				}
				
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	
	@SuppressLint("HandlerLeak")
	private void getxkcdComic()
	{
		String baseURL = "http://xkcd.com/info.0.json";
		@SuppressWarnings("unused")
		String formattedURL;
		try 
		{
			formattedURL = URLEncoder.encode(baseURL, "UTF-8");
		} catch (Exception e)
		{
			Log.e("BAD URL", "ENCODING PROBLEM");
			Toast toast = Toast.makeText(this, "Bad URL, Encoding problem",  Toast.LENGTH_SHORT);
			toast.show();
			formattedURL = "";
		}
		@SuppressWarnings("unused")
		URL finishedURL;
		try
		{
			finishedURL = new URL(baseURL);
			
			
			//TEST HANDLER
			Handler urlRequestHandler = new Handler(){

				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					
					
					
					if (msg.arg1 == RESULT_OK)
					{
						try 
						{
							Log.i("YAY", "Works");
							
							String resultsData = (String) msg.obj;
							parseData(resultsData);
							
							
						}
						catch (Exception e)
						{
							Log.e("HandleMessage", e.getMessage().toString());
							
						}
					}
				}
				
			};
			
			
			
			Messenger urlMessenger = new Messenger(urlRequestHandler);
			
			Intent startURLIntent = new Intent(this, connectionwork.URLService.class);
			startURLIntent.putExtra(connectionwork.URLService.URL_INFORMATION, urlMessenger);
			startURLIntent.putExtra(connectionwork.URLService.BASE_URL, baseURL);
			
			startService(startURLIntent);
			
		} catch (MalformedURLException e)
		{
			Log.e("BAD URL", "MALFORMED URL");
			Toast toast = Toast.makeText(this, "Bad URL, Malformed URL",  Toast.LENGTH_SHORT);
			toast.show();
			finishedURL = null;
		}
	}
	
	
	public void parseData(String result)
	{
		JSONObject jsonResponse;
		try {
			jsonResponse = new JSONObject(result);
			
			String imageUrl = jsonResponse.getString("img");
			
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	

}

package com.example.xkcdcomicviewer;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainView extends Activity {

	
	static Button webViewButton;
	
	static EditText enteredNumber;
	static TextView testText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);
		
		
		String derp1 = "derp1";
		String derp2 = "derp2";
		String derp3 = "derp3";
		
		savestuff.SaveClass.storeJSONStringData(this, derp1);
		savestuff.SaveClass.storeJSONStringData(this, derp2);
		savestuff.SaveClass.storeJSONStringData(this, derp3);
		
		ArrayList<String> testList = savestuff.SaveClass.readArrayData(this, "favorites");
		
		Log.i("test", testList.get(1));
		
		
		
		
		
		webViewButton = (Button) findViewById(R.id.launchButton);
		enteredNumber = (EditText) findViewById(R.id.numberBox);
		testText = (TextView) findViewById(R.id.testText);
	
		webViewButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				getxkcdComic(enteredNumber.getText().toString());
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_view, menu);
		return true;
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.favoritesButton:
	        	Intent startURLIntent = new Intent(this, FavoritesActivity.class);
				
				startActivity(startURLIntent);
				
				
	            return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	    
	}
	
	@SuppressLint("HandlerLeak")
	private void getxkcdComic(String userInput)
	{
		String firstPart = "http://xkcd.com/";
		
		String middlePart = userInput;
		
		String lastPart = "/info.0.json";
		
		String baseURL = firstPart + middlePart + lastPart;
		
		
		Log.i("getxkcdcomic method", "success");
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
			
			Log.i("Test", "Works");
			
			jsonResponse = new JSONObject(result);
			
			String imageUrl = jsonResponse.getString("img");
			
			Intent webView = new Intent(this, ComicView.class);
			webView.putExtra("img", imageUrl);
			
			startActivity(webView);
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			testText.setText("No result found.  Try again");
		}
		
	}

	

}

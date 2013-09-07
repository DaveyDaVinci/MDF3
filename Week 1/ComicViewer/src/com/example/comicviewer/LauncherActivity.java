//David Magee
//Full Sail University
//Week 1 project for MDF3
//September 7th, 2013


package com.example.comicviewer;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebView;

public class LauncherActivity extends Activity {

	
	static Bundle retrievedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//This removes the title from the top of the window.
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_launcher);
		
		//Grabs the intent and the extras I passed inside it
		retrievedData = getIntent().getExtras();
		
		//Locks the screen into landscape mode
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		//If the retrieved data is populated
		if (retrievedData != null)
		{
			//Grabs the extra saved under "img" key and loads the url in the webview with it
			String imageURL = retrievedData.getString("img");
			WebView webview = (WebView) findViewById(R.id.webview);
			webview.loadUrl(imageURL);
			
			
		}
		else 
		{
			//Loads a standard 404 first.  With kitten!
			WebView webview = (WebView) findViewById(R.id.webview);
			webview.loadUrl("http://gaeswf.appspot.com/images/404kitten.jpg");
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launcher, menu);
		return true;
	}
	
	

}

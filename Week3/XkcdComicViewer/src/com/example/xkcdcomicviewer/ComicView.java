package com.example.xkcdcomicviewer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class ComicView extends Activity {
	
	static Bundle retrievedData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_view);
		
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
		getMenuInflater().inflate(R.menu.comic_view, menu);
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

}

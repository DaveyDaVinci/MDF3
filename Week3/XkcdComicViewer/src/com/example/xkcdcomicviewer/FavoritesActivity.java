package com.example.xkcdcomicviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class FavoritesActivity extends Activity {

	static Context context;
	String imageURL;
	
	static Bundle retrievedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorites);
		
		
		context = this;
		
		String savedFave = saveclasses.SaveClass.readFavorites(context, "favorites");
		
		//Locks the screen into landscape mode
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//If the retrieved data is populated
		if (savedFave != null && !savedFave.isEmpty())
		{
			//Grabs the extra saved under "img" key and loads the url in the webview with it
			
			WebView webview = (WebView) findViewById(R.id.webview);
			webview.loadUrl(savedFave);
			
			
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
		getMenuInflater().inflate(R.menu.favorites, menu);
		return true;
	}
	
	//Creates a switch case scenario to see which option is selected in the action bar
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
		switch (item.getItemId()) {
        case R.id.favoritesButton:
        	Intent startURLIntent = new Intent(this, FavoritesActivity.class);
			
			startActivity(startURLIntent);
			
			
            return true;
            
        case R.id.webViewButton:
        	
        	Intent webView = new Intent(this, ComicView.class);
			
        	Set<String> web = saveclasses.SaveClass.readLastComicURL(this, "previouscomic");
        	
        	List<String> webList = new ArrayList<String>(web);
        	
        	webView.putExtra("img", webList.get(0));
			
			startActivity(webView);
			
			return true;
        	
        
        default:
            return super.onOptionsItemSelected(item);
		}
	    
	}
	
	

}

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
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class ComicView extends Activity {
	
	static Bundle retrievedData;
	
	String imageURL;
	
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comic_view);
		
		context = this;
		//Grabs the intent and the extras I passed inside it
				retrievedData = getIntent().getExtras();
				
				//Locks the screen into landscape mode
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				
				//If the retrieved data is populated
				if (retrievedData != null)
				{
					//Grabs the extra saved under "img" key and loads the url in the webview with it
					imageURL = retrievedData.getString("img");
					WebView webview = (WebView) findViewById(R.id.webview);
					webview.loadUrl(imageURL);
					
					
				}
				else 
				{
					//Loads a standard 404 first.  With kitten!
					WebView webview = (WebView) findViewById(R.id.webview);
					webview.loadUrl("http://gaeswf.appspot.com/images/404kitten.jpg");
				}
				
				//Creates a button to add to favorites
				Button favoritesButton = (Button) findViewById(R.id.favoriteButton);
				favoritesButton.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						saveclasses.SaveClass.storeFavorites(context, imageURL);
						
						Toast toast = Toast.makeText(context, "Favorite Saved", Toast.LENGTH_SHORT);
						toast.show();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comic_view, menu);
		return true;
	}
	
	//Creates a switch case scenario to check which button is pressed in hte action bar
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

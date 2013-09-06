package com.fullsail.magee_david_secondproject;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.webkit.WebView;

public class LaunchedActivity extends Activity {

	static Bundle retrievedData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.launched_activity);
		
		
		retrievedData = getIntent().getExtras();
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		if (retrievedData != null)
		{
			String imageURL = retrievedData.getString("img");
			WebView webview = (WebView) findViewById(R.id.webview);
			webview.loadUrl(imageURL);
			
			
			/*
			try {
				
				  String imageURL = retrievedData.getString("img");
				  ImageView comic = (ImageView)findViewById(R.id.comicImage);
				  Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageURL).getContent());
				  comic.setImageBitmap(bitmap); 
				} catch (MalformedURLException e) {
				  e.printStackTrace();
				} catch (IOException e) {
				  e.printStackTrace();
				}
			*/
		}
		else 
		{
			WebView webview = (WebView) findViewById(R.id.webview);
			webview.loadUrl("http://gaeswf.appspot.com/images/404kitten.jpg");
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launched, menu);
		return true;
	}
	
	

}

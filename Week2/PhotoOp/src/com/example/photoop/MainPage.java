//David Magee
//Week 2 project MDF3
//Full Sail University
//Thursday, September 12, 2013

package com.example.photoop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainPage extends Activity {

private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	
	
	
	static Context context;
	
	private String videoPath;
	
	static VideoView videoView;
	
	static ImageView testView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		
		context = this;
		videoPath = null;
		
		testView = (ImageView) findViewById(R.id.imageViewTest);
		videoView = (VideoView) findViewById(R.id.videoView);
		testView.setVisibility(View.INVISIBLE);
		videoView.setVisibility(View.INVISIBLE);
		
		//Creates a button to take a picture
		Button photoButton = (Button) findViewById(R.id.photoButton);
		photoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Creates a camera intent and starts it, using the image code above
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
				
			}
		});
		
		//Button for video
		Button videoButton = (Button) findViewById(R.id.videoButton);
		videoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Creates an intent for the camera to capture video, sets quality, starts intent
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				
				startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
				
			}
		});
		
		//Moves on to the next page, 
		Button nextPageButton = (Button) findViewById(R.id.nextPageButton);
		nextPageButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent nextActivity = new Intent(context, GPSActivity.class);
				startActivity(nextActivity);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_page, menu);
		return true;
	}
	
	//This handles the results from the intents above.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		//If an image and result is okay
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
		{
			//Sets the image in the imageview, converting the pic into a bitmap
			
			
			Bitmap picBit = (Bitmap) data.getExtras().get("data");
			
			testView.setImageBitmap(picBit);
			testView.setVisibility(View.VISIBLE);
			videoView.setVisibility(View.INVISIBLE);
			
			
		}
		else if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
		{
			
			videoView.setVisibility(View.VISIBLE);
			testView.setVisibility(View.INVISIBLE);
			Uri video = data.getData();
			videoPath = getVideoPath(video);
			videoView.setVideoPath(videoPath);
			videoView.setMediaController(new MediaController(context));
			videoView.start();
			
			
		}
	}
	
	public String getVideoPath(Uri contentUri)
	{
		String[] dataStore = { MediaStore.Images.Media.DATA };
	    @SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(contentUri, dataStore, null, null, null);
	    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}

}

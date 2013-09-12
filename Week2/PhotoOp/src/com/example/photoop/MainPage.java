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
	
	private Uri fileURI;
	
	static Context context;
	
	private String videoPath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		
		context = this;
		videoPath = null;
		
		
		Button photoButton = (Button) findViewById(R.id.photoButton);
		photoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
				
			}
		});
		
		Button videoButton = (Button) findViewById(R.id.videoButton);
		videoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
				
				intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
				
				startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
				
			}
		});
		
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
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
		{
			ImageView testView = (ImageView) findViewById(R.id.imageViewTest);
			
			Bitmap picBit = (Bitmap) data.getExtras().get("data");
			
			testView.setImageBitmap(picBit);
			
		}
		else if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
		{
			VideoView videoView = (VideoView) findViewById(R.id.videoView);
			videoView.setVisibility(View.VISIBLE);
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
	    Cursor cursor = managedQuery(contentUri, dataStore, null, null, null);
	    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    cursor.moveToFirst();
	    return cursor.getString(column_index);
	}

}

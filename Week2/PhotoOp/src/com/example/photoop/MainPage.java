package com.example.photoop;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainPage extends Activity {

private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	private Uri fileURI;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
		
		
		
		Button testButton = (Button) findViewById(R.id.photoButton);
		testButton.setOnClickListener(new View.OnClickListener() {
			
		
			
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				
				startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
				
				
				
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
	}

}

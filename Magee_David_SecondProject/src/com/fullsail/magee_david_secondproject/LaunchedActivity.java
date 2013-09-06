package com.fullsail.magee_david_secondproject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class LaunchedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launched);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.launched, menu);
		return true;
	}

}

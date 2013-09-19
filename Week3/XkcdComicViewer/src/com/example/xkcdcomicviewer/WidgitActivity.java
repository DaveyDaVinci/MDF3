package com.example.xkcdcomicviewer;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.RemoteViews;
import android.widget.TextView;

public class WidgitActivity extends Activity {
	
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_widgit);
		
		context = this;
		
		Bundle extras = getIntent().getExtras();
		
		if (extras != null)
		{
			int widgId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, 
					AppWidgetManager.INVALID_APPWIDGET_ID);
			
			if (widgId != AppWidgetManager.INVALID_APPWIDGET_ID)
			{
				RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
				
				Set<String> set = saveclasses.SaveClass.readLastComicURL(context, "previouscomic");
				
				ArrayList<String> list = new ArrayList<String>(set);
				
				Intent webView = new Intent(context, ComicView.class);
				
				remoteViews.setTextViewText(R.id.comicName, list.get(0));
				
				webView.putExtra("img", list.get(1));
				
				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, webView, 0);
				
				remoteViews.setOnClickPendingIntent(R.id.presentButton, pendingIntent);
				
				
				AppWidgetManager.getInstance(context).updateAppWidget(widgId, remoteViews);
				
				Intent finalCall = new Intent();
				finalCall.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgId);
				setResult(RESULT_OK, finalCall);
				finish();
			}
		}
				
			
	} 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.widgit, menu);
		return true;
	}

}

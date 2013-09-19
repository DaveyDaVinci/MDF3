package com.example.xkcdcomicviewer;

import java.util.ArrayList;
import java.util.Set;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

public class ComicWidgetProvider extends AppWidgetProvider {
	
	public void onUpdate(Context context, AppWidgetManager manager, int[] appWidgetIds)
	{
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
		
		Set<String> savedComic = saveclasses.SaveClass.readLastComicURL(context, "previouscomic");
		
		ArrayList<String> savedList = new ArrayList<String>(savedComic);
		
		String savedComicName = savedList.get(0);
		
		remoteViews.setTextViewText(R.id.comicName, savedComicName);
		
		manager.updateAppWidget(appWidgetIds, remoteViews);
		
	}

}

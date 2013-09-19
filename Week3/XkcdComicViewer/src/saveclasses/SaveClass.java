package saveclasses;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class SaveClass {
	
	private static String SAVE_NAME = "favorites";
	
	private static String LAST_VIEWED_URL = "previouscomic";
	
	
	static Set<String> savedSet = null;
	
	static Set<String> previousInfo = null;
	
	
	//Save method.  Saves a set to the editor.  
	public static boolean storeFavorites(Context context, String urlContent)
	{
		SharedPreferences prefs = context.getSharedPreferences(SAVE_NAME, 0);
		
		Editor editor = prefs.edit();
		
		editor.putString(SAVE_NAME, urlContent);
		
		editor.commit();
		
		return true;
	}
	
	
	//read method.  reads a set from sharedPrefs
	public static String readFavorites(Context context, String filename)
	{
		SharedPreferences prefs = context.getSharedPreferences(SAVE_NAME, 0);
		
		String favorite = prefs.getString(SAVE_NAME, "");
		
		return favorite;
	}
	
	
	//Stores last comic viewed
	public static boolean storeLastComic(Context context, String urlContent, String comicName)
	{
		SharedPreferences prefs = context.getSharedPreferences(LAST_VIEWED_URL, 0);
		
		Editor editor = prefs.edit();
		
		Set<String> previous = new HashSet<String>();
		
		previous.add(comicName);
		
		previous.add(urlContent);
		
		editor.putStringSet(LAST_VIEWED_URL, previous);
		
		editor.commit();
		
		return true;
	}
	
	//Reads the last comic viewed
	public static Set<String> readLastComicURL(Context context, String filename)
	{
		SharedPreferences prefs = context.getSharedPreferences(LAST_VIEWED_URL, 0);
				
		Set<String> set = prefs.getStringSet(LAST_VIEWED_URL, new HashSet<String>());
		
		return set;
	}
	
	
}

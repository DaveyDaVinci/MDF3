package savestuff;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveClass {
	
	private static String SAVE_NAME = "favorites";
	
	public static boolean storeJSONStringData(Context context, String urlContent)
	{
		SharedPreferences prefs = context.getSharedPreferences(SAVE_NAME, Activity.MODE_APPEND);
		
		Editor editor = prefs.edit();
		
		ArrayList<String> savedList = readArrayData(context, SAVE_NAME);
		
		savedList.add(urlContent);
		
		Set<String> set = new HashSet<String>(savedList);
		
		editor.putStringSet(SAVE_NAME, set);
		
		return true;
	}
	
	
	
	public static ArrayList<String> readArrayData (Context context, String filename)
	{
		ArrayList<String> savedData = null;
		
		SharedPreferences prefs = context.getSharedPreferences(SAVE_NAME, Activity.MODE_APPEND);
		
		Set<String> set = prefs.getStringSet(SAVE_NAME, null);
		
		savedData = new ArrayList<String>(set);
		
		
		
		return savedData;
	}


}

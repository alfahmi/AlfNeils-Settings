package in.alfneils.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceManager {
	private Context mContext;

	public PreferenceManager(Context c){
		this.mContext = c;
	}

	public Object loadSavedPreferences(int type, String key, Object default_value) {
		SharedPreferences sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(mContext);
		switch (type) {
			case 0:
				boolean checkBoxValue = sharedPreferences.getBoolean(key, (Boolean)default_value);
				return checkBoxValue;
			case 1:
				String name = sharedPreferences.getString(key, (String)default_value);
				return name;
			default:
				break;
		}
		return null;
	}

	public void savePreferences(int type, String key, Object value) {
		SharedPreferences sharedPreferences = android.preference.PreferenceManager.getDefaultSharedPreferences(mContext);
		Editor editor = sharedPreferences.edit();
		switch (type) {
			case 0:
				editor.putBoolean(key, (Boolean)value);
				break;
			case 1:
				editor.putString(key, (String)value);
				break;
			default:
				break;
		}
		editor.commit();
	}
}

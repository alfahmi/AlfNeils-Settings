package in.alfneils.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.SherlockPreferenceActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import com.actionbarsherlock.view.*;
import in.alfneils.view.RainbowTextView;
import android.widget.TextView;

import in.alfneils.R;


public class SettingsActivity extends SherlockPreferenceActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		SharedPreferences sharedPreferences = getSharedPreferences("Alfahmi",Context.MODE_PRIVATE); 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		addPreferencesFromResource(R.xml.alfahmi__settings);
		
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setIcon(android.R.color.transparent);
		getSupportActionBar().setTitle("Setting");
		

        
    	String multi = sharedPreferences.getString("styleApp","viewpager");
    	((ListPreference)findPreference("styleApp")).setSummary(multi);

        ((ListPreference)findPreference("styleApp")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

		@Override
		public boolean onPreferenceChange(Preference preference, Object newValue) {
			String type = (String.valueOf(newValue));
			Intent i = new Intent();
			i.putExtra("multi",type);
			preference.setSummary(type);
			SharedPreferences sharedPreferences = getSharedPreferences("Alfahmi",MODE_PRIVATE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("styleApp", type);
			editor.commit();
			return true;
		}


	});
	
	
		String fab = sharedPreferences.getString("fabSettings","hidden");
    	((ListPreference)findPreference("fabSettings")).setSummary(fab);

        ((ListPreference)findPreference("fabSettings")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("fab",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getSharedPreferences("Alfahmi",MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("fabSettings", type);
					editor.commit();
					return true;
				}


			});
	
	}
	
	public void onBackPressed() {
		Intent settingsIntent = new Intent();
		settingsIntent.setClass(this, MainActivity.class);
		startActivity(settingsIntent);
		finish();
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.undo:
                // app icon in action bar clicked; go home 
                Intent intent = new Intent(this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
				return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }	
	
}

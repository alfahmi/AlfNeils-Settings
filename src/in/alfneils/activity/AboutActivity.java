package in.alfneils.activity;

import android.content.*;
import android.graphics.*;
import android.os.*;
import android.text.style.*;
import android.widget.*;
import com.actionbarsherlock.app.*;
import com.actionbarsherlock.view.*;
import android.preference.*;

import in.alfneils.R;

public class AboutActivity extends SherlockPreferenceActivity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.list);
	addPreferencesFromResource(R.xml.alfahmi__about);
	
	getSupportActionBar().setHomeButtonEnabled(true);
	getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	getSupportActionBar().setIcon(android.R.color.transparent);
    getSupportActionBar().setTitle("About");

		
	setValueSummary("rom_name", "ro.nexaf.rom");
	findPreference("rom_name").setEnabled(true);
    setValueSummary("rom_version", "ro.nexaf.version");
    findPreference("rom_version").setEnabled(true);
	
	}
	
	private void setValueSummary(String preference, String property) {
        try {
            findPreference(preference).setSummary(
				SystemProperties.get(property,
				getResources().getString(R.string.unknown)));
        } catch (RuntimeException e) {

        }
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.undo:
				finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
}

package in.alfneils.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.provider.Settings.System;
import android.support.v4.preference.PreferenceFragment;

import com.actionbarsherlock.app.SherlockPreferenceActivity;

import in.alfneils.R;
import in.alfneils.view.PreferenceManager;

public class ActivityFloating extends SherlockPreferenceActivity implements OnPreferenceChangeListener{
	PreferenceManager mPrManager;
	
	EditTextPreference et1, et2, et3, et4;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.alfahmi__floating);
		
		//this.mPrManager = new PreferenceManager(getActivity());
		
		et1 = (EditTextPreference)findPreference("pref_key_p_left"); et2 = (EditTextPreference)findPreference("pref_key_p_top");
		et3 = (EditTextPreference)findPreference("pref_key_p_right"); et4 = (EditTextPreference)findPreference("pref_key_p_bottom");
		
		et1.setOnPreferenceChangeListener(this); et2.setOnPreferenceChangeListener(this);
		et3.setOnPreferenceChangeListener(this); et4.setOnPreferenceChangeListener(this);
		
		init();
	}
	
	private void init(){
		Resources res = getResources();
		et1.setText((String)mPrManager.loadSavedPreferences(1, "statusbar_p_left", res.getString(R.string.floating_def_left)));
		et2.setText((String)mPrManager.loadSavedPreferences(1, "statusbar_p_top", res.getString(R.string.floating_def_top)));
		et3.setText((String)mPrManager.loadSavedPreferences(1, "statusbar_p_right", res.getString(R.string.floating_def_right)));
		et4.setText((String)mPrManager.loadSavedPreferences(1, "statusbar_p_bottom", res.getString(R.string.floating_def_bottom)));
		
		et1.setSummary(et1.getText()); et2.setSummary(et2.getText());
		et3.setSummary(et3.getText()); et4.setSummary(et4.getText());
	}

	@Override
	public boolean onPreferenceChange(Preference arg0, Object arg1) {
		if(arg0 == et1){
			mPrManager.savePreferences(1, "statusbar_p_left", (String)arg1);
			System.putString(getContentResolver(), "statusbar_p_left", (String)arg1);
		}else if(arg0 == et2){
			mPrManager.savePreferences(1, "statusbar_p_top", (String)arg1);
			System.putString(getContentResolver(), "statusbar_p_top", (String)arg1);
		}else if(arg0 == et3){
			mPrManager.savePreferences(1, "statusbar_p_right", (String)arg1);
			System.putString(getContentResolver(), "statusbar_p_right", (String)arg1);
		}else if(arg0 == et4){
			mPrManager.savePreferences(1, "statusbar_p_bottom", (String)arg1);
			System.putString(getContentResolver(), "statusbar_p_bottom", (String)arg1);
		}
		sendRefreshIntent();
		init();
		return true;
	}
	
	private void sendRefreshIntent(){
		Intent i = new Intent("statusbar_padding_change");
		sendBroadcast(i);
	}
}


/*     ____   __   ________   __  _______   __
 *    / _  | / /  / __/ _  | / /_/ /     | / /
 *   / ___ |/ /__/ _// __  |/ __  / /\/| |/ /
 *  /_/  |_/____/_/ /_/  |_/_/ /_/_/   |_/_/
 */
 
package in.alfneils.fragment;

import android.support.v4.preference.PreferenceFragment;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.widget.EditText;
import android.content.Intent;

import net.margaritov.preference.colorpicker.*;
import android.content.*;

import in.alfneils.R;

public class FragmentStatusBar extends PreferenceFragment implements OnPreferenceChangeListener {

    private static final String PREF_STATUS_BAR_AM_PM = "pref_status_bar_am_pm";

    private static final String PREF_STATUS_BAR_BATTERY = "pref_status_bar_battery";

    private static final String PREF_STATUS_BAR_CLOCK = "pref_status_bar_clock";

    private static final String PREF_STATUS_BAR_CM_SIGNAL = "pref_status_bar_cm_signal";

    private static final String PREF_STATUS_BAR_HEADSET = "pref_status_bar_headset";

    private ListPreference mStatusBarAmPm;

    private ListPreference mStatusBarBattery;

    private ListPreference mStatusBarCmSignal;

    private CheckBoxPreference mStatusBarClock;

    private CheckBoxPreference mStatusBarHeadset;

	String color;
	
	BroadcastReceiver mReceiver;

	String brotkes;
	
	@SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		
        addPreferencesFromResource(R.xml.alfahmi__status_bar);
		
		
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Alfahmi",Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
    	String layoutType = sharedPreferences.getString("type","normal");
		final String backgroundStyle = sharedPreferences.getString("stat_bg","Gradient");

		
		((ListPreference)findPreference("type")).setSummary(layoutType);

        ((ListPreference)findPreference("type")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Alfahmi",Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);
					SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
					editor.putString("type", type); //true or false
					editor.commit();
					Intent i = new Intent();
					i.setAction("com.android.systemui.statusbar.CHANGE_LAYOUT");
					i.putExtra("type",type);
					getActivity().sendBroadcast(i);
					return true;
				}

			}); 
			
		((ListPreference)findPreference("stat_bg")).setSummary(backgroundStyle);
		((ListPreference)findPreference("stat_bg")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String bg = (String.valueOf(newValue));
					Intent i = new Intent();
					i.setAction("in.alfneils.GAYA_BACKGROUND");
					i.putExtra("stat_bg",bg);
					getActivity().sendBroadcast(i);
					preference.setSummary(bg);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Alfahmi",Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
					editor.putString("stat_bg",bg); //true or false
					editor.commit();			
					return true;
				}

			});

		((ColorPickerPreference)findPreference("warnaStatusbar")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					color = (ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(newValue))));
					preference.setSummary(color);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Alfahmi",  Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit(); //opens the editor
					editor.putString("warnaStatusbar", color); //true or false
					editor.commit();
					Intent intent = new Intent();
					intent.setAction("in.alfneils.WARNA_STATUSBAR");
					intent.putExtra("warnaStatusbar",color.toString());
					getActivity().sendBroadcast(intent);				
					return false;
				}

			});
			
		String bgcolor = getActivity().getSharedPreferences("Alfahmi", Context.MODE_PRIVATE).getString("warnaStatusbar", "#ff000000");

	    ((ColorPickerPreference)findPreference("warnaStatusbar")).setDefaultValue(bgcolor);
	    ((ColorPickerPreference)findPreference("warnaStatusbar")).setSummary(bgcolor);
		
		if ("Custom".equals(backgroundStyle)){
			getPreferenceScreen().findPreference("warnaStatusbar").setEnabled(true);
			
		} else {
		 
			getPreferenceScreen().findPreference("warnaStatusbar").setEnabled(false);
			
		 }

		mReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context c, Intent i) {
				brotkes = i.getStringExtra("stat_bg");
			if ("Custom".equals(backgroundStyle)){
			getPreferenceScreen().findPreference("warnaStatusbar").setEnabled(true);
			
		} else {
		 
			getPreferenceScreen().findPreference("warnaStatusbar").setEnabled(false);
			
		 }
		 
		 }
   
		 };      
		getActivity().registerReceiver(mReceiver, new IntentFilter("in.alfneils.GAYA_BACKGROUND"));
	

        PreferenceScreen prefSet = getPreferenceScreen();

        mStatusBarClock = (CheckBoxPreference) prefSet.findPreference(PREF_STATUS_BAR_CLOCK);
        mStatusBarHeadset = (CheckBoxPreference) prefSet
                .findPreference(PREF_STATUS_BAR_HEADSET);

        mStatusBarClock.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_CLOCK, 1) == 1));
        mStatusBarHeadset.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_HEADSET, 1) == 1));
				
        mStatusBarAmPm = (ListPreference) prefSet.findPreference(PREF_STATUS_BAR_AM_PM);
		mStatusBarBattery = (ListPreference) prefSet.findPreference(PREF_STATUS_BAR_BATTERY);
        mStatusBarCmSignal = (ListPreference) prefSet.findPreference(PREF_STATUS_BAR_CM_SIGNAL);

        int statusBarAmPm = Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_AM_PM, 2);
        mStatusBarAmPm.setValue(String.valueOf(statusBarAmPm));
        mStatusBarAmPm.setOnPreferenceChangeListener(this);

        int statusBarBattery = Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_BATTERY, 0);
        mStatusBarBattery.setValue(String.valueOf(statusBarBattery));
        mStatusBarBattery.setOnPreferenceChangeListener(this);

        int signalStyle = Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_CM_SIGNAL_TEXT, 0);
        mStatusBarCmSignal.setValue(String.valueOf(signalStyle));
        mStatusBarCmSignal.setOnPreferenceChangeListener(this);

    }

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mStatusBarAmPm) {
            int statusBarAmPm = Integer.valueOf((String) newValue);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_AM_PM,
                    statusBarAmPm);
            return true;
        } else if (preference == mStatusBarBattery) {
            int statusBarBattery = Integer.valueOf((String) newValue);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_BATTERY,
                    statusBarBattery);
            return true;
        } else if (preference == mStatusBarCmSignal) {
            int signalStyle = Integer.valueOf((String) newValue);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_CM_SIGNAL_TEXT,
                    signalStyle);
            return true;
		}
		return false;
	}

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        /* Preference Screens */
        if (preference == mStatusBarClock) {
            value = mStatusBarClock.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_CLOCK,
                    value ? 1 : 0);
            return true;
        } else if (preference == mStatusBarHeadset) {
            value = mStatusBarHeadset.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_HEADSET,
                    value ? 1 : 0);
            return true;
        }
        return false;
    }
}

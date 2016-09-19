
/*     ____   __   ________   __  _______   __
 *    / _  | / /  / __/ _  | / /_/ /     | / /
 *   / ___ |/ /__/ _// __  |/ __  / /\/| |/ /
 *  /_/  |_/____/_/ /_/  |_/_/ /_/_/   |_/_/
 */
 
package in.alfneils.fragment;

import android.support.v4.preference.PreferenceFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import net.margaritov.preference.colorpicker.ColorPickerPreference;
import android.preference.*;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.widget.EditText;
import android.content.Intent;
import android.widget.*;
import android.text.style.*;

import in.alfneils.R;

public class FragmentNotification extends PreferenceFragment implements OnPreferenceChangeListener {

	private static final String PREF_STATUS_BAR_CARRIER_LABEL = "pref_status_bar_carrier_label";

    private static final String PREF_STATUS_BAR_CARRIER_LABEL_CUSTOM = "pref_status_bar_carrier_label_custom";

    private static final String PREF_STATUS_BAR_COMPACT_CARRIER = "pref_status_bar_compact_carrier";
	
	private ListPreference mStatusBarCarrierLabel;
	
	private CheckBoxPreference mStatusBarCompactCarrier;
	
	private EditTextPreference mStatusBarCarrierLabelCustom;
	
	String clock;
	String color;
	String color1;
	String color2;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.alfahmi__notifications);
	
	
	((ColorPickerPreference)findPreference("warnaTracking")).setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
    
	@Override
	  public boolean onPreferenceChange(Preference paramAnonymousPreference, Object paramAnonymousObject) {
        FragmentNotification.this.color = ColorPickerPreference.convertToARGB(Integer.valueOf(String.valueOf(paramAnonymousObject)).intValue());
		paramAnonymousPreference.setSummary(FragmentNotification.this.color);
        SharedPreferences.Editor localEditor = FragmentNotification.this.getActivity().getSharedPreferences("Alfahmi", 0).edit();
        localEditor.putString("warnaTracking", FragmentNotification.this.color);
        localEditor.commit();
        Intent localIntent = new Intent();
        localIntent.setAction("in.alfneils.WARNA_TRACKING");
		  localIntent.putExtra("kolor", FragmentNotification.this.color.toString());
		FragmentNotification.this.getActivity().sendBroadcast(localIntent);
        return false;
      }
    });
	String str = getActivity().getSharedPreferences("Alfahmi", 0).getString("warnaTracking", "#ff8e979f");
	((ColorPickerPreference)findPreference("warnaTracking")).setDefaultValue(str);
	((ColorPickerPreference)findPreference("warnaTracking")).setSummary(str);
  
	PreferenceScreen prefSet = getPreferenceScreen();
	
	mStatusBarCompactCarrier = (CheckBoxPreference) prefSet
                .findPreference(PREF_STATUS_BAR_COMPACT_CARRIER);
	mStatusBarCompactCarrier.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_COMPACT_CARRIER, 0) == 1));
	mStatusBarCarrierLabel = (ListPreference) prefSet
                .findPreference(PREF_STATUS_BAR_CARRIER_LABEL);
        mStatusBarCarrierLabelCustom = (EditTextPreference) prefSet
                .findPreference(PREF_STATUS_BAR_CARRIER_LABEL_CUSTOM);

        if (mStatusBarCarrierLabelCustom != null) {
            EditText carrierEditText = mStatusBarCarrierLabelCustom.getEditText();

            if (carrierEditText != null) {
                InputFilter lengthFilter = new InputFilter.LengthFilter(20);
                carrierEditText.setFilters(new InputFilter[]{lengthFilter});
                carrierEditText.setSingleLine(true);
            }
        }

        int statusBarCarrierLabel = Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.CARRIER_LABEL_TYPE, 0);
        String statusBarCarrierLabelCustom = Settings.System.getString(getActivity().getContentResolver(),
                Settings.System.CARRIER_LABEL_CUSTOM_STRING);

        if (statusBarCarrierLabelCustom == null) {
            statusBarCarrierLabelCustom = "Alf02";
            Settings.System.putString(getActivity().getContentResolver(),
                    Settings.System.CARRIER_LABEL_CUSTOM_STRING,
                    statusBarCarrierLabelCustom);
        }

        mStatusBarCarrierLabel.setValue(String.valueOf(statusBarCarrierLabel));
        mStatusBarCarrierLabel.setOnPreferenceChangeListener(this);

        mStatusBarCarrierLabelCustom.setText(statusBarCarrierLabelCustom);
        mStatusBarCarrierLabelCustom.setOnPreferenceChangeListener(this);
        mStatusBarCarrierLabelCustom.setEnabled(
                statusBarCarrierLabel == 3);
	
  
	}
	
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		if (preference == mStatusBarCarrierLabel) {
            int carrierLabelType = Integer.valueOf((String) newValue);
            mStatusBarCarrierLabelCustom.setEnabled(carrierLabelType == 3);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.CARRIER_LABEL_TYPE,
                    carrierLabelType);
            return true;
        } else if (preference == mStatusBarCarrierLabelCustom) {
            String carrierLabelCustom = String.valueOf(newValue);
            Settings.System.putString(getActivity().getContentResolver(),
                    Settings.System.CARRIER_LABEL_CUSTOM_STRING,
                    carrierLabelCustom);
            return true;
        }
        return false;
	}
	
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
    boolean value;
		if (preference == mStatusBarCompactCarrier) {
        value = mStatusBarCompactCarrier.isChecked();
        Settings.System.putInt(getActivity().getContentResolver(),
            Settings.System.STATUS_BAR_COMPACT_CARRIER, value ? 1 : 0);
        return true;
		}
		return false;
	}
}

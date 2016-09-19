
/*     ____   __   ________   __  _______   __
 *    / _  | / /  / __/ _  | / /_/ /     | / /
 *   / ___ |/ /__/ _// __  |/ __  / /\/| |/ /
 *  /_/  |_/____/_/ /_/  |_/_/ /_/_/   |_/_/
 */
 
package in.alfneils.fragment;

import android.content.Context;
import android.support.v4.preference.PreferenceFragment;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.provider.CmSystem;
import android.provider.Settings;

import in.alfneils.R;

public class FragmentTablet extends PreferenceFragment implements OnPreferenceChangeListener
{

	Context context;
	
	@Override
	public boolean onPreferenceChange(Preference p1, Object p2)
	{
		return false;
	}
	
    private static final String PREF_STATUS_BAR_BOTTOM = "pref_status_bar_bottom";
    private static final String PREF_STATUS_BAR_DEAD_ZONE = "pref_status_bar_dead_zone";
    private static final String PREF_SOFT_BUTTONS_LEFT = "pref_soft_buttons_left";
    private static final String PREF_GENERAL_CATEGORY = "pref_general_category";
    
    private CheckBoxPreference mStatusBarBottom;
    private CheckBoxPreference mStatusBarDeadZone;
    private CheckBoxPreference mSoftButtonsLeft;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.alfahmi__tablet);
		
		
        PreferenceScreen prefSet = getPreferenceScreen();

        mStatusBarBottom = (CheckBoxPreference) prefSet.findPreference(PREF_STATUS_BAR_BOTTOM);
        mStatusBarDeadZone = (CheckBoxPreference) prefSet.findPreference(PREF_STATUS_BAR_DEAD_ZONE);
        mSoftButtonsLeft = (CheckBoxPreference) prefSet.findPreference(PREF_SOFT_BUTTONS_LEFT);
        
        int defValue;

        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_BOTTOM_STATUS_BAR)==true ? 1 : 0;
        mStatusBarBottom.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_BOTTOM, defValue) == 1));
        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_USE_DEAD_ZONE)==true ? 1 : 0;
        mStatusBarDeadZone.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.STATUS_BAR_DEAD_ZONE, defValue) == 1));
        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_SOFT_BUTTONS_LEFT)==true ? 1 : 0;
        mSoftButtonsLeft.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SOFT_BUTTONS_LEFT, defValue) == 1));
        
        if(!CmSystem.getDefaultBool(getActivity(), CmSystem.CM_HAS_SOFT_BUTTONS)){
            PreferenceCategory cGeneral=(PreferenceCategory) prefSet.findPreference(PREF_GENERAL_CATEGORY);

            cGeneral.removePreference(mSoftButtonsLeft);
  
        }

        updateDependencies();
    }


    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        if (preference == mStatusBarBottom) {
            value = mStatusBarBottom.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_BOTTOM,
                    value ? 1 : 0);
            updateDependencies();
            return true;
        } else if (preference == mStatusBarDeadZone) {
            value = mStatusBarDeadZone.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_DEAD_ZONE,
                    value ? 1 : 0);
            return true;
        } else if (preference == mSoftButtonsLeft) {
            value = mSoftButtonsLeft.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SOFT_BUTTONS_LEFT,
                    value ? 1 : 0);
            return true;
        }

        return false;
    }


    private void updateDependencies() {
        if(!mStatusBarBottom.isChecked()){
            mStatusBarDeadZone.setChecked(false);
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.STATUS_BAR_DEAD_ZONE, 0);
        }

    }
	
}


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
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceScreen;
import android.provider.CmSystem;
import android.provider.Settings;
import android.content.Intent;

import in.alfneils.R;

public class FragmentSoftButtons extends PreferenceFragment {
    private static final String PREF_SOFT_BUTTONS_HOME = "pref_soft_buttons_home";
    private static final String PREF_SOFT_BUTTONS_MENU = "pref_soft_buttons_menu";
    private static final String PREF_SOFT_BUTTONS_BACK = "pref_soft_buttons_back";
    private static final String PREF_SOFT_BUTTONS_SEARCH = "pref_soft_buttons_search";
    private static final String PREF_SOFT_BUTTONS_QUICK_NA = "pref_soft_buttons_quick_na";

    private CheckBoxPreference mSoftButtonsHome;
    private CheckBoxPreference mSoftButtonsMenu;
    private CheckBoxPreference mSoftButtonsBack;
    private CheckBoxPreference mSoftButtonsSearch;
    private CheckBoxPreference mSoftButtonsQuickNa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		addPreferencesFromResource(R.xml.alfahmi__soft_buttons);
        PreferenceScreen prefSet = getPreferenceScreen();

        mSoftButtonsHome = (CheckBoxPreference) prefSet.findPreference(PREF_SOFT_BUTTONS_HOME);
        mSoftButtonsMenu = (CheckBoxPreference) prefSet.findPreference(PREF_SOFT_BUTTONS_MENU);
        mSoftButtonsBack = (CheckBoxPreference) prefSet.findPreference(PREF_SOFT_BUTTONS_BACK);
        mSoftButtonsSearch = (CheckBoxPreference) prefSet.findPreference(PREF_SOFT_BUTTONS_SEARCH);
        mSoftButtonsQuickNa = (CheckBoxPreference) prefSet.findPreference(PREF_SOFT_BUTTONS_QUICK_NA);

        int defValue;

        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_SHOW_SOFT_HOME)==true ? 1 : 0;
        mSoftButtonsHome.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SOFT_BUTTON_SHOW_HOME, defValue) == 1));
        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_SHOW_SOFT_MENU)==true ? 1 : 0;
        mSoftButtonsMenu.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SOFT_BUTTON_SHOW_MENU, defValue) == 1));
        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_SHOW_SOFT_BACK)==true ? 1 : 0;
        mSoftButtonsBack.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SOFT_BUTTON_SHOW_BACK, defValue) == 1));
        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_SHOW_SOFT_SEARCH)==true ? 1 : 0;
        mSoftButtonsSearch.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SOFT_BUTTON_SHOW_SEARCH, defValue) == 1));
        defValue=CmSystem.getDefaultBool(getActivity(), CmSystem.CM_DEFAULT_SHOW_SOFT_QUICK_NA)==true ? 1 : 0;
        mSoftButtonsQuickNa.setChecked((Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.SOFT_BUTTON_SHOW_QUICK_NA, defValue) == 1));
    }

    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;

        /* Preference Screens */
        if (preference == mSoftButtonsHome) {
            value = mSoftButtonsHome.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SOFT_BUTTON_SHOW_HOME,
                    value ? 1 : 0);
            return true;
        } else if (preference == mSoftButtonsMenu) {
            value = mSoftButtonsMenu.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SOFT_BUTTON_SHOW_MENU,
                    value ? 1 : 0);
            return true;
        } else if (preference == mSoftButtonsBack) {
            value = mSoftButtonsBack.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SOFT_BUTTON_SHOW_BACK,
                    value ? 1 : 0);
            return true;
        } else if (preference == mSoftButtonsSearch) {
            value = mSoftButtonsSearch.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SOFT_BUTTON_SHOW_SEARCH,
                    value ? 1 : 0);
            return true;
        } else if (preference == mSoftButtonsQuickNa) {
            value = mSoftButtonsQuickNa.isChecked();
            Settings.System.putInt(getActivity().getContentResolver(), Settings.System.SOFT_BUTTON_SHOW_QUICK_NA,
                    value ? 1 : 0);
            return true;
        }

        return false;
    }
	
}

package in.alfneils.fragment;

import android.os.Bundle;
import android.support.v4.preference.PreferenceFragment;

import in.alfneils.R;


public class Style2 extends PreferenceFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	addPreferencesFromResource(R.xml.test);
	
	}
}

package in.alfneils.view;

import android.content.*;
import android.support.v4.app.*;
import android.text.style.*;
import android.widget.*;
import in.alfneils.*;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 5;
	// Tab Titles
	private String tabtitles[] = new String[] { "StatusBar Tweaks", "Floating SystemUI", "Expanded", "Tablet Tweaks", "Soft Button" };
	Context context;
	
	public ViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {

		case 0:
			in.alfneils.fragment.FragmentStatusBar tab1 = new in.alfneils.fragment.FragmentStatusBar();
			return tab1;

		case 1:
			in.alfneils.fragment.FragmentFloating tab2 = new in.alfneils.fragment.FragmentFloating();
			return tab2;
		
		case 2:
			in.alfneils.fragment.FragmentNotification tab3 = new in.alfneils.fragment.FragmentNotification();
			return tab3;
		
		case 3:
			in.alfneils.fragment.FragmentTablet tab4 = new in.alfneils.fragment.FragmentTablet();
			return tab4;

		case 4:
			in.alfneils.fragment.FragmentSoftButtons tab5 = new in.alfneils.fragment.FragmentSoftButtons();
			return tab5;
		}
		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return tabtitles[position];
	}
}

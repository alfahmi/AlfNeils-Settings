package in.alfneils.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.app.*;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.preference.PreferenceFragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.*;

import in.alfneils.R;
import in.alfneils.view.ViewPagerAdapter;

public class MainActivity extends SherlockFragmentActivity {
	private FragmentManager fm;
	private FragmentTransaction ft;
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SharedPreferences sharedPreferences = getSharedPreferences("Alfahmi",Context.MODE_PRIVATE);    
	    String nel = sharedPreferences.getString("styleApp","viewpager");
		if ("grid".equals(nel)) 
		{	
			setContentView(R.layout.alfahmi__main);
			fm = getSupportFragmentManager();
			if (fm.findFragmentById(R.id.konten) == null) {  
				ft = fm.beginTransaction();
				ft.replace(R.id.konten, new in.alfneils.fragment.Style1());
				ft.commit();
			}  
		}
		else if ("normal".equals(nel))
		{
			setContentView(R.layout.alfahmi__main);
			fm = getSupportFragmentManager();
			if (fm.findFragmentById(R.id.konten) == null) {  
				ft = fm.beginTransaction();
				ft.replace(R.id.konten, new in.alfneils.fragment.Style2());
				ft.commit();
			}  

		}
		else if ("viewpager".equals(nel))
		{
			setContentView(R.layout.alfahmi__view_pager_main);
			ViewPager pager = (ViewPager) findViewById(R.id.pager);
			FragmentManager fm = getSupportFragmentManager();
			ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(fm);
			pager.setAdapter(pagerAdapter);
       
		}
		
		addListenerOnButton();
		
		getSupportActionBar().setIcon(android.R.color.transparent);
		getSupportActionBar().setHomeButtonEnabled(false);
		getSupportActionBar().setTitle("Nexaf");
		
	}
	
	@Override
	public void onBackPressed()
	{
		finish();
	}
	
	public void addListenerOnButton() {
		final Context context = this;
		ImageButton button = (ImageButton) findViewById(R.id.fab);
		button.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
			    Intent intent = new Intent(context, SettingsActivity.class);
                startActivity(intent);
				finish();
			}
 
		});
	
		SharedPreferences sharedPreferences = getSharedPreferences("Alfahmi",Context.MODE_PRIVATE);
		String fab = sharedPreferences.getString("fabSettings","hidden");
		String theme = sharedPreferences.getString("AppTheme","dark");
		if ("light".equals(theme)) {
			button.setImageResource(R.drawable.ic_fab_add);
			int color =Color.parseColor("#ffffff");
			button.setColorFilter(color);
			button.setBackgroundResource(R.drawable.circle_black);

		} else if ("dark".equals(theme)) {
			int color =Color.parseColor("#ffffff");
			button.setColorFilter(color);
			button.setImageResource(R.drawable.ic_fab_add);
			button.setBackgroundResource(R.drawable.fab_dark);

		}
		if ("hidden".equals(fab))
		{
			button.setVisibility(View.GONE);
		} 
		else if ("showed".equals(fab))
		{

			button.setVisibility(View.VISIBLE);
		}
		
		
 
	}

	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	getSupportMenuInflater().inflate(R.menu.main, menu);
	return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			Intent settingsIntent = new Intent();
			settingsIntent.setClass(this, in.alfneils.activity.SettingsActivity.class);
			startActivity(settingsIntent);
			finish();
			return true;
		case R.id.credits:
			this.dialog();
			return true;
		case R.id.menu_about:
			Intent AboutIntent = new Intent();
			AboutIntent.setClass(this, in.alfneils.activity.AboutActivity.class);
			startActivity(AboutIntent);
			return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		
	}
	

	private void dialog ()
	{
		AlertDialog.Builder adb = new AlertDialog.Builder (context);
		adb.setTitle(R.string.alert_title);
		adb.setMessage(R.string.alert_message);
		adb.setCancelable(false);
		adb.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		
		AlertDialog alertDialog = adb.create();
		alertDialog.show () ;
	} 
}


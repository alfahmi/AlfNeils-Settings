package in.alfneils.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.LinearLayout;

public class FloatingPadding extends LinearLayout{
	private Context mContext;
	public FloatingPadding(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;

		int[] paddings = getPadding();
		FloatingPadding.this.setPadding(paddings[0], paddings[1], paddings[2], paddings[3]);

		context.registerReceiver(mReceiver, new IntentFilter("statusbar_padding_change"));
	}

	private int[] getPadding(){
		String temp;
		temp = System.getString(mContext.getContentResolver(), "statusbar_p_left");
		if (temp == null)
			temp = "30";
		if (temp.equals(""))
			temp = "0";
		int left = Integer.valueOf(temp);
		temp = System.getString(mContext.getContentResolver(), "statusbar_p_top");
		if (temp == null)
			temp = "30";
		if (temp.equals(""))
			temp = "0";
		int top = Integer.valueOf(temp);
		temp = System.getString(mContext.getContentResolver(), "statusbar_p_right");
		if (temp == null)
			temp = "30";
		if (temp.equals(""))
			temp = "0";
		int right = Integer.valueOf(temp);
		temp = System.getString(mContext.getContentResolver(), "statusbar_p_bottom");
		if (temp == null)
			temp = "30";
		if (temp.equals(""))
			temp = "0";
		int bottom = Integer.valueOf(temp);

		return new int[] {convertToDip(left), convertToDip(top), convertToDip(right), convertToDip(bottom)};
	}

	private int convertToDip(int i){
		int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
				i, getResources().getDisplayMetrics());
		return value;
	}
	private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			int[] paddings = getPadding();
			FloatingPadding.this.setPadding(paddings[0], paddings[1], paddings[2], paddings[3]);
		}
	};

}

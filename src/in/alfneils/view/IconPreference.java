package in.alfneils.view;
 
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import in.alfneils.R;
import android.graphics.*;
import android.content.*;

public class IconPreference extends Preference {
 
    private Drawable icon = null;
 
    public IconPreference(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setLayoutResource(R.layout.alfahmi__icon_preference);
 
        TypedArray ta = context.obtainStyledAttributes(
                attrs, R.styleable.IconPreference, defStyle, 0);
        icon = ta.getDrawable(R.styleable.IconPreference_icon);
    }
 
    public IconPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
 
    protected void onBindView(View view) {
        super.onBindView(view);
 
		ImageView imageView = (ImageView) view.findViewById(R.id.icon);
		
			int color =Color.parseColor("#000000");
			imageView.setColorFilter(color);
			imageView.setBackgroundResource(R.drawable.circle);
		
        if (imageView != null) {
            if (icon != null) {
                imageView.setImageDrawable(icon);
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
 
	}

    public void setIcon(Drawable icon) {
        if (this.icon == null && icon != null
                || icon != null && !icon.equals(this.icon)) {
            this.icon = icon;
            notifyChanged();
        }
    }
}

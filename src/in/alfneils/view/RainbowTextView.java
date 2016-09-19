package in.alfneils.view;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import java.util.Random;
import android.widget.*;

public class RainbowTextView extends TextView
{
  private final int[] GOOGLE_COLORS;
  private Context mContext;
  private String text;

  public RainbowTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = Color.rgb(3, 169, 244);
    arrayOfInt[1] = Color.rgb(233, 30, 99);
    arrayOfInt[2] = Color.rgb(199, 30, 233);
    arrayOfInt[3] = Color.rgb(132, 30, 233);
    this.GOOGLE_COLORS = arrayOfInt;
    this.mContext = paramContext;
    this.text = getText().toString();
    Random localRandom = new Random();
    int i = 0;
    int j = 0;
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(this.text);
    for (int k = 0; k < this.text.length(); k++)
    {
      while (i == j)
        j = localRandom.nextInt(4);
      i = j;
      localSpannableStringBuilder.setSpan(new ForegroundColorSpan(this.GOOGLE_COLORS[j]), k, k + 1, 18);
    }
    setText(localSpannableStringBuilder);
  }

  public void setRainbowText(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null)
    {
      Random localRandom = new Random();
      int i = 0;
      int j = 0;
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramCharSequence);
      for (int k = 0; k < paramCharSequence.length(); k++)
      {
        while (i == j)
          j = localRandom.nextInt(4);
        i = j;
        localSpannableStringBuilder.setSpan(new ForegroundColorSpan(this.GOOGLE_COLORS[j]), k, k + 1, 18);
      }
      setText(localSpannableStringBuilder);
    }
    while (true)
    {
      return;
      
    }
  }

  public void setRainbowText(String paramString)
  {
    if (paramString != null)
    {
      Random localRandom = new Random();
      int i = 0;
      int j = 0;
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
      for (int k = 0; k < paramString.length(); k++)
      {
        while (i == j)
          j = localRandom.nextInt(4);
        i = j;
        localSpannableStringBuilder.setSpan(new ForegroundColorSpan(this.GOOGLE_COLORS[j]), k, k + 1, 18);
      }
      setText(localSpannableStringBuilder);
    }
    while (true)
    {
      return;
      
    }
  }
}

/* Location:           D:\dex2jar-0.0.9.8\framework_dex2jar.jar
 * Qualified Name:     android.widget.RainbowTextView
 * JD-Core Version:    0.6.2
 */

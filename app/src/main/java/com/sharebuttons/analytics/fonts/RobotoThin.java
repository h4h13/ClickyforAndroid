package com.sharebuttons.analytics.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Monkey D Luffy on 7/14/2015.
 */
public class RobotoThin extends TextView {
    public RobotoThin(Context context) {
        super(context);
        intit();
    }

    public RobotoThin(Context context, AttributeSet attrs) {
        super(context, attrs);
        intit();
    }

    public RobotoThin(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intit();
    }

    private void intit() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Thin.ttf");
            setTypeface(tf);
        }
    }
}

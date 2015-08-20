package com.sharebuttons.analytics.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Monkey D Luffy on 7/14/2015.
 */
public class RobotoLight extends TextView {

    public RobotoLight(Context context) {
        super(context);
        init();
    }

    public RobotoLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Light.ttf");
            setTypeface(tf);
        }
    }


}

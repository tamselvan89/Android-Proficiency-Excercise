package co.proficiency.app.utils;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by ARUN-PC on 19-12-2017.
 */

public class TitleLight extends android.support.v7.widget.AppCompatTextView {
    public TitleLight(Context context) {
        super(context);
        setFont(context);
    }

    public TitleLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public TitleLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        this.setTypeface(GeneralUtil.getRobotoLight(context));
    }

}

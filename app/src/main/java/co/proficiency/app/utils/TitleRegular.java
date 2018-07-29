package co.proficiency.app.utils;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by ARUN-PC on 19-12-2017.
 */

public class TitleRegular extends android.support.v7.widget.AppCompatTextView {
    public TitleRegular(Context context) {
        super(context);
        setFont(context);
    }

    public TitleRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public TitleRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        this.setTypeface(GeneralUtil.getRobotoRegular(context));
    }

}

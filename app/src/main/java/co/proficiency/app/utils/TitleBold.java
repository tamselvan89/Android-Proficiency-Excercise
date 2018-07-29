package co.proficiency.app.utils;

import android.content.Context;
import android.util.AttributeSet;

public class TitleBold extends android.support.v7.widget.AppCompatTextView {
    public TitleBold(Context context) {
        super(context);
        setFont(context);
    }

    public TitleBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }

    public TitleBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(context);
    }

    private void setFont(Context context) {
        this.setTypeface(GeneralUtil.getRobotoBold(context));
    }

}

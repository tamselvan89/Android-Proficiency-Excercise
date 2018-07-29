package co.proficiency.app.generic;

import android.app.Application;
import android.content.Context;

import co.proficiency.app.utils.GeneralUtil;

public class ApplicationManager extends Application {

    public static Context baseContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseContext = getApplicationContext();
        GeneralUtil.setDefaultFont(this, "MONOSPACE", "font/Roboto-Regular.ttf");
    }
}

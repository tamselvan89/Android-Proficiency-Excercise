package co.proficiency.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import co.proficiency.app.R;

public class SplashActivity extends AppCompatActivity {

    public static final String TAG = SplashActivity.class.getSimpleName();

    Context mContext;
    int SPLASH_TIMOUT = 2000;
    Handler mHandler;
    Unbinder mUnbinder;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(mContext, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        process();
    }

    private void init() {
        mUnbinder = ButterKnife.bind(this);
        mContext = SplashActivity.this;
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
    }

    private void process() {
        mHandler = new Handler();
        mHandler.postDelayed(runnable, SPLASH_TIMOUT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}

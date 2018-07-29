package co.proficiency.app.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

import co.proficiency.app.R;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class GeneralUtil {

    public static Dialog progressDialog;
    public static Toast popUpToast;
    static Handler handler;
    static ProgressBar myProgressBar;
    static int state = 0;
    static Context ctxProg;
    
    static Runnable runnableProgress = new Runnable() {
        @Override
        public void run() {
            switch (state) {
                case 0:
                    state = 1;
                    myProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(ctxProg, R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
                    break;
                case 1:
                    state = 2;
                    myProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(ctxProg, R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
                    break;
                case 2:
                    state = 0;
                    myProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(ctxProg, R.color.colorError), android.graphics.PorterDuff.Mode.MULTIPLY);
                    break;
            }
            handler.postDelayed(runnableProgress, 1000);
        }
    };

    public static Typeface getRobotoBlack(final Context context) {
        Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Black.ttf");
        return custom_font;
    }

    public static Typeface getRobotoRegular(final Context context) {
        Typeface custom_regular_font = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Regular.ttf");
        return custom_regular_font;
    }

    public static Typeface getRobotoBold(final Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Bold.ttf");
        return typeface;
    }

    public static Typeface getRobotoLight(final Context context) {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "font/Roboto-Light.ttf");
        return typeface;
    }

    public static void setDefaultFont(Context context, String staticTypefaceFieldName, String fontAssetName) {
        final Typeface regular = Typeface.createFromAsset(context.getAssets(), fontAssetName);
        replaceFont(staticTypefaceFieldName, regular);
    }

    public static void replaceFont(String staticTypefaceFieldName, final Typeface newTypeface) {
        try {
            final Field staticField = Typeface.class.getDeclaredField(staticTypefaceFieldName);
            staticField.setAccessible(true);
            staticField.set(null, newTypeface);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void showProgressDialog(Context ctx, String text) {
        try {
            dismissProgressDialog();
            handler = new Handler();
            ctxProg = ctx;
            progressDialog = new Dialog(ctx, R.style.Dialog);
            LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
            View convertview = inflater.inflate(R.layout.progress_dialog, null);
            TextView textViewProgress = convertview.findViewById(R.id.progresstext);
            myProgressBar = convertview.findViewById(R.id.progressbar);
            textViewProgress.setVisibility(View.VISIBLE);
            if (text.isEmpty())
                textViewProgress.setVisibility(View.GONE);
            textViewProgress.setText(text);
            textViewProgress.setTypeface(getRobotoBlack(ctx));
            progressDialog.setContentView(convertview);
            progressDialog.setCancelable(false);
            handler.post(runnableProgress);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dismissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                handler.removeCallbacks(runnableProgress);
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo network = manager.getActiveNetworkInfo();

        if (network == null || !network.isConnected()) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setMessage(context.getResources().getString(R.string.try_after_sometime));
            alert.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alert.show();
            return false;
        }
        return true;
    }

    public static void ShowLongToast(Context ctx, String msg) {
        if (popUpToast != null)
            popUpToast.cancel();

        popUpToast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
        popUpToast.show();
    }
}

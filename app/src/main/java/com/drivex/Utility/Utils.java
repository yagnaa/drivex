package com.drivex.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.drivex.DriveX;
import com.drivex.R;

/**
 * Created by sujeetmehta on 03/12/15.
 */
public class Utils {

    Context context;

    public Utils(Context context) {
        this.context = context;

    }
    public void setStatusBarColor(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(activity.getResources().getColor(color));
        }
    }

    public void saveStringData(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getSavedString(String key, String defValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, defValue);
    }

    public static SpannableStringBuilder colorfullText(String str, int index, int len) {

        final SpannableStringBuilder stringBuilder = new SpannableStringBuilder(str);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(DriveX.getContext().getResources().getColor(R.color.colorPrimary));
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

        if (index >= 0) {
            Log.i("number", index + ", " + index + len);
            stringBuilder.setSpan(fcs, index, index + len, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            stringBuilder.setSpan(bss, index, index + len, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        } else {
            final ForegroundColorSpan fcs1 = new ForegroundColorSpan(DriveX.getContext().getResources().getColor(R.color.col666));

            stringBuilder.setSpan(fcs1, 0, str.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return stringBuilder;
    }

    public void hideKeyBoard(EditText editText) {
        // TODO Auto-generated method stub
        InputMethodManager imm =
                (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp) {
        Resources resources = DriveX.getContext().getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return px;
    }


    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / (metrics.densityDpi / 160f);
        return dp;
    }
}

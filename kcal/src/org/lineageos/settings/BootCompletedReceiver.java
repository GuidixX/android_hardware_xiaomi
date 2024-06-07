package org.lineageos.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import androidx.preference.PreferenceManager;
import org.lineageos.settings.preferences.FileUtils;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final String KCAL_ENABLE = "/sys/module/msm_drm/parameters/kcal_enable";
    private static final String KCAL_RGB = "/sys/module/msm_drm/parameters/kcal";
    private static final String KCAL_MIN = "/sys/module/msm_drm/parameters/kcal_min";
    private static final String KCAL_SAT = "/sys/module/msm_drm/parameters/kcal_sat";
    private static final String KCAL_VAL = "/sys/module/msm_drm/parameters/kcal_val";
    private static final String KCAL_CONT = "/sys/module/msm_drm/parameters/kcal_cont";
    private static final String KCAL_HUE = "/sys/module/msm_drm/parameters/kcal_hue";

    private static final String PREF_ENABLED = "kcal_enabled";
    private static final String PREF_MINIMUM = "color_minimum";
    private static final String PREF_SATURATION = "saturation";
    private static final String PREF_VALUE = "value";
    private static final String PREF_CONTRAST = "contrast";
    private static final String PREF_HUE = "hue";
    private static final String PREF_GRAYSCALE = "grayscale";

    private static final int MINIMUM_DEFAULT = 35;
    private static final int SATURATION_DEFAULT = 128;
    private static final int VALUE_DEFAULT = 128;
    private static final int CONTRAST_DEFAULT = 128;
    private static final int HUE_DEFAULT = 0;
    private static final int SATURATION_OFFSET = 128;
    private static final int VALUE_OFFSET = 128;
    private static final int CONTRAST_OFFSET = 128;

    @Override
    public void onReceive(final Context context, Intent intent) {
        boolean enabled = Settings.Secure.getInt(context.getContentResolver(), PREF_ENABLED, 0) == 1;
        FileUtils.setValue(KCAL_ENABLE, enabled ? "1" : "0");

        if (enabled) {
            String rgbValue = Settings.Secure.getInt(context.getContentResolver(), PREF_MINIMUM, MINIMUM_DEFAULT)
                    + " " + Settings.Secure.getInt(context.getContentResolver(), PREF_MINIMUM, MINIMUM_DEFAULT)
                    + " " + Settings.Secure.getInt(context.getContentResolver(), PREF_MINIMUM, MINIMUM_DEFAULT);
            FileUtils.setValue(KCAL_RGB, rgbValue);
            FileUtils.setValue(KCAL_MIN, String.valueOf(Settings.Secure.getInt(context.getContentResolver(), PREF_MINIMUM, MINIMUM_DEFAULT)));
            FileUtils.setValue(KCAL_SAT, String.valueOf(Settings.Secure.getInt(context.getContentResolver(), PREF_GRAYSCALE, 0) == 1 ? 128 : Settings.Secure.getInt(context.getContentResolver(), PREF_SATURATION, SATURATION_DEFAULT) + SATURATION_OFFSET));
            FileUtils.setValue(KCAL_VAL, String.valueOf(Settings.Secure.getInt(context.getContentResolver(), PREF_VALUE, VALUE_DEFAULT) + VALUE_OFFSET));
            FileUtils.setValue(KCAL_CONT, String.valueOf(Settings.Secure.getInt(context.getContentResolver(), PREF_CONTRAST, CONTRAST_DEFAULT) + CONTRAST_OFFSET));
            FileUtils.setValue(KCAL_HUE, String.valueOf(Settings.Secure.getInt(context.getContentResolver(), PREF_HUE, HUE_DEFAULT)));
        }
    }
}

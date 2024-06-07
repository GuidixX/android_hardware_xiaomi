package org.lineageos.settings.kcal;

import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.Preference.OnPreferenceChangeListener;
import androidx.preference.PreferenceManager;
import android.provider.Settings;

import org.lineageos.settings.preferences.FileUtils;
import org.lineageos.settings.R;
import org.lineageos.settings.preferences.SecureSettingSwitchPreference;
import org.lineageos.settings.preferences.SecureSettingSeekBarPreference;

public class KcalSettings extends PreferenceFragmentCompat implements OnPreferenceChangeListener {

    private static final String PREF_ENABLED = "kcal_enabled";
    private static final String PREF_MINIMUM = "color_minimum";
    private static final String PREF_RED = "color_red";
    private static final String PREF_GREEN = "color_green";
    private static final String PREF_BLUE = "color_blue";
    private static final String PREF_SATURATION = "saturation";
    private static final String PREF_VALUE = "value";
    private static final String PREF_CONTRAST = "contrast";
    private static final String PREF_HUE = "hue";
    private static final String PREF_GRAYSCALE = "grayscale";

    private static final String KCAL_ENABLE = "/sys/module/msm_drm/parameters/kcal_enable";
    private static final String KCAL_RGB = "/sys/module/msm_drm/parameters/kcal";
    private static final String KCAL_MIN = "/sys/module/msm_drm/parameters/kcal_min";
    private static final String KCAL_SAT = "/sys/module/msm_drm/parameters/kcal_sat";
    private static final String KCAL_VAL = "/sys/module/msm_drm/parameters/kcal_val";
    private static final String KCAL_CONT = "/sys/module/msm_drm/parameters/kcal_cont";
    private static final String KCAL_HUE = "/sys/module/msm_drm/parameters/kcal_hue";

    private static final int MINIMUM_DEFAULT = 35;
    private static final int SATURATION_DEFAULT = 128;
    private static final int VALUE_DEFAULT = 128;
    private static final int CONTRAST_DEFAULT = 128;
    private static final int HUE_DEFAULT = 0;
    private static final int SATURATION_OFFSET = 128;
    private static final int VALUE_OFFSET = 128;
    private static final int CONTRAST_OFFSET = 128;

    private SecureSettingSwitchPreference mEnabled;
    private SecureSettingSeekBarPreference mMin;
    private SecureSettingSeekBarPreference mRed;
    private SecureSettingSeekBarPreference mGreen;
    private SecureSettingSeekBarPreference mBlue;
    private SecureSettingSeekBarPreference mSaturation;
    private SecureSettingSeekBarPreference mValue;
    private SecureSettingSeekBarPreference mContrast;
    private SecureSettingSeekBarPreference mHue;
    private SecureSettingSwitchPreference mGrayscale;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.kcal_preferences);

        mEnabled = findPreference(PREF_ENABLED);
        mEnabled.setOnPreferenceChangeListener(this);

        mMin = findPreference(PREF_MINIMUM);
        mMin.setOnPreferenceChangeListener(this);

        mRed = findPreference(PREF_RED);
        mRed.setOnPreferenceChangeListener(this);

        mGreen = findPreference(PREF_GREEN);
        mGreen.setOnPreferenceChangeListener(this);

        mBlue = findPreference(PREF_BLUE);
        mBlue.setOnPreferenceChangeListener(this);

        mSaturation = findPreference(PREF_SATURATION);
        mSaturation.setOnPreferenceChangeListener(this);

        mValue = findPreference(PREF_VALUE);
        mValue.setOnPreferenceChangeListener(this);

        mContrast = findPreference(PREF_CONTRAST);
        mContrast.setOnPreferenceChangeListener(this);

        mHue = findPreference(PREF_HUE);
        mHue.setOnPreferenceChangeListener(this);

        mGrayscale = findPreference(PREF_GRAYSCALE);
        mGrayscale.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        switch (key) {
            case PREF_ENABLED:
                FileUtils.setValue(KCAL_ENABLE, (boolean) newValue ? "1" : "0");
                break;
            case PREF_MINIMUM:
                FileUtils.setValue(KCAL_MIN, (int) newValue);
                break;
            case PREF_RED:
            case PREF_GREEN:
            case PREF_BLUE:
                String rgbString = mRed.getValue() + " " + mGreen.getValue() + " " + mBlue.getValue();
                FileUtils.setValue(KCAL_RGB, rgbString);
                break;
            case PREF_SATURATION:
                if (!(Settings.Secure.getInt(getContext().getContentResolver(), PREF_GRAYSCALE, 0) == 1)) {
                    FileUtils.setValue(KCAL_SAT, (int) newValue + SATURATION_OFFSET);
                }
                break;
            case PREF_VALUE:
                FileUtils.setValue(KCAL_VAL, (int) newValue + VALUE_OFFSET);
                break;
            case PREF_CONTRAST:
                FileUtils.setValue(KCAL_CONT, (int) newValue + CONTRAST_OFFSET);
                break;
            case PREF_HUE:
                FileUtils.setValue(KCAL_HUE, (int) newValue);
                break;
            case PREF_GRAYSCALE:
                FileUtils.setValue(KCAL_SAT, (boolean) newValue ? 128 : Settings.Secure.getInt(getContext().getContentResolver(), PREF_SATURATION, SATURATION_DEFAULT) + SATURATION_OFFSET);
                break;
        }
        return true;
    }

    // Add methods if needed
    public void applyValues(String values) {
        // Implement the method to apply values
    }

    public void setmGrayscale(boolean grayscale) {
        // Implement the method to set grayscale
    }

    public void setmSetOnBoot(boolean setOnBoot) {
        // Implement the method to set on boot
    }
}

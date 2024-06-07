package org.lineageos.settings.kcal;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import org.lineageos.settings.R;

public class KcalSettingsActivity extends FragmentActivity {

    private KcalSettings mKcalSettingsFragment;

    private static final int RED_DEFAULT = 255;
    private static final int GREEN_DEFAULT = 255;
    private static final int BLUE_DEFAULT = 255;
    private static final int MINIMUM_DEFAULT = 35;
    private static final int SATURATION_DEFAULT = 128;
    private static final int VALUE_DEFAULT = 128;
    private static final int CONTRAST_DEFAULT = 128;
    private static final int HUE_DEFAULT = 0;
    private static final boolean GRAYSCALE_DEFAULT = false;
    private static final boolean SETONBOOT_DEFAULT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kcal_activity);

        mKcalSettingsFragment = (KcalSettings) getSupportFragmentManager().findFragmentById(R.id.kcal_settings_fragment);
        if (mKcalSettingsFragment == null) {
            mKcalSettingsFragment = new KcalSettings();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.kcal_settings_fragment, mKcalSettingsFragment)
                    .commit();
        }
    }
}

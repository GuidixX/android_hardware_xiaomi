// SecureSettingSwitchPreference.java
package org.lineageos.settings.preferences;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.SwitchPreference;
import android.provider.Settings;

public class SecureSettingSwitchPreference extends SwitchPreference {
    public SecureSettingSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onClick() {
        super.onClick();
        Settings.Secure.putInt(getContext().getContentResolver(), getKey(), isChecked() ? 1 : 0);
    }
}

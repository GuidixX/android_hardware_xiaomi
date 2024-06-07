// SecureSettingSeekBarPreference.java
package org.lineageos.settings.preferences;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.SeekBarPreference;
import android.provider.Settings;

public class SecureSettingSeekBarPreference extends SeekBarPreference {
    public SecureSettingSeekBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        int value = Settings.Secure.getInt(getContext().getContentResolver(), getKey(), (int) defaultValue);
        setValue(value);
    }

    @Override
    protected void onClick() {
        super.onClick();
        Settings.Secure.putInt(getContext().getContentResolver(), getKey(), getValue());
    }
}

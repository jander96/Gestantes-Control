package com.devj.gestantescontrol.framework.ui.view

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.devj.gestantescontrol.R

class AjustesFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
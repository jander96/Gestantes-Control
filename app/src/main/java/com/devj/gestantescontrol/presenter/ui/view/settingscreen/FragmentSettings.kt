package com.devj.gestantescontrol.presenter.ui.view.settingscreen


import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.devj.gestantescontrol.R


class FragmentSettings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
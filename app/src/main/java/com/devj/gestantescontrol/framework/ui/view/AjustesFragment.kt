package com.devj.gestantescontrol.framework.ui.view


import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import androidx.work.WorkManager
import com.devj.gestantescontrol.GestantesApplication
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.RepoImpl
import com.devj.gestantescontrol.framework.NumberAndEG
import com.devj.gestantescontrol.framework.roomdatabase.AppGestanteDatabase
import com.devj.gestantescontrol.framework.roomdatabase.LocalDataBaseImpl
import com.devj.gestantescontrol.framework.ui.viewmodel.AjustesViewModel
import com.devj.gestantescontrol.framework.ui.viewmodel.AjustesViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AjustesFragment : PreferenceFragmentCompat() {
    private lateinit var roomdatabase: AppGestanteDatabase
    private lateinit var repo: RepoImpl
    private val viewModel: AjustesViewModel by viewModels() {
        AjustesViewModelFactory(repo)
    }
    lateinit var workManager: WorkManager

    var phoneAndEg :List<NumberAndEG> = mutableListOf()


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        roomdatabase = (requireContext().applicationContext as GestantesApplication).database
        repo = RepoImpl(LocalDataBaseImpl(roomdatabase))
        val isActive = preferenceManager.findPreference<SwitchPreferenceCompat>("sms")
        viewModel.gestantesList.observe(this){
            phoneAndEg = viewModel.getPhoneNumberAndEG(it)
       }






    }


}
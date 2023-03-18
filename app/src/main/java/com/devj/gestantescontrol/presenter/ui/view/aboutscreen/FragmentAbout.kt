package com.devj.gestantescontrol.presenter.ui.view.aboutscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.AcercaBinding

class FragmentAbout : Fragment(R.layout.acerca) {
    private var _binding: AcercaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AboutViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AcercaBinding.bind(view)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
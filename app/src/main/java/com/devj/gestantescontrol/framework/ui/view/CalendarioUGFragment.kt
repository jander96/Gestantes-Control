package com.devj.gestantescontrol.framework.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.hideKeyboard
import com.devj.gestantescontrol.databinding.FragmentCalendarioUgBinding
import com.devj.gestantescontrol.framework.ui.viewmodel.CalendarioUGViewModel


class CalendarioUGFragment : DialogFragment(R.layout.fragment_calendario_ug) {
    private var _binding: FragmentCalendarioUgBinding? = null
    private val binding get() = _binding!!
    private val modelView: CalendarioUGViewModel by viewModels()
    private lateinit var navController: NavController
    private val args : CalendarioUGFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCalendarioUgBinding.bind(view)
        navController = findNavController()
        binding.datePickerUg.maxDate = modelView.today
        binding.datePickerUg.minDate = modelView.aYearAgo

        binding.btnFugAceptada.setOnClickListener {
            it.hideKeyboard()
            modelView.enviarRegistros(binding,args,navController)
        }
    }

    override fun onDestroy() {
        _binding=null
        super.onDestroy()
    }
}
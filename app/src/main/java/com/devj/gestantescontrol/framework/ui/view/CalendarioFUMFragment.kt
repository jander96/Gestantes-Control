package com.devj.gestantescontrol.framework.ui.view


import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentCalendarioFumBinding
import com.devj.gestantescontrol.framework.ui.viewmodel.CalendarioFUMViewModel


class CalendarioFUMFragment : DialogFragment(R.layout.fragment_calendario_fum) {
    private var _binding: FragmentCalendarioFumBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CalendarioFUMViewModel by viewModels()
    private lateinit var navController: NavController
    private val args: CalendarioFUMFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCalendarioFumBinding.bind(view)
        navController = findNavController()
        binding.datePickerFum.maxDate = viewModel.today
        binding.datePickerFum.minDate = viewModel.aYearAgo


        binding.btnFumAceptada.setOnClickListener {
           viewModel.navigateAndpassData(binding,args,navController)
        }

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }
}
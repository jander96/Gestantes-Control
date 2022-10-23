package com.devj.gestantescontrol.framework.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.AcercaBinding
import com.devj.gestantescontrol.framework.ui.viewmodel.AcercaDeViewModel

class AcercaDeFragment : Fragment(R.layout.acerca) {
    private var _binding: AcercaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AcercaDeViewModel by viewModels()
    val developerEmail = "jander96@nauta.cu"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = AcercaBinding.bind(view)
        binding.imageApp.playAnimation()
        binding.imageApp.loop(true)
        binding.emailImage.setOnClickListener {
            viewModel.sendEmail(requireActivity().applicationContext, developerEmail)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}
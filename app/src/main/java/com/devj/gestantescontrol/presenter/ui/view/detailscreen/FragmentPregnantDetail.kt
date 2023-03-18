package com.devj.gestantescontrol.presenter.ui.view.detailscreen


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentDetailBinding


class FragmentPregnantDetail : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }
}




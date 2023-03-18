package com.devj.gestantescontrol.presenter.ui.view.editionscreen


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentEdicionBinding


class FragmentEdition : Fragment(R.layout.fragment_edicion) {

    private var _binding: FragmentEdicionBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEdicionBinding.bind(view)
    }
}
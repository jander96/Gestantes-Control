package com.devj.gestantescontrol.presenter.ui.view.editionscreen


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentEdicionBinding
import com.devj.gestantescontrol.domain.intents.EditionIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentEdition : Fragment(R.layout.fragment_edicion) {

    private var _binding: FragmentEdicionBinding? = null
    val binding get() = _binding!!
    private val viewModel : EditionViewModel by viewModels()
    private val args : FragmentEditionArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEdicionBinding.bind(view)
        if(args.pregnantId != 0) refillFieldsIntent()
    }

    private fun refillFieldsIntent() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.intentFlow.emit(EditionIntent.RefillFields(args.pregnantId))
            }
        }
    }
}
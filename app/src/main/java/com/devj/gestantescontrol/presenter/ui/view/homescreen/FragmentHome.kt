package com.devj.gestantescontrol.presenter.ui.view.homescreen



import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentHomeBinding
import com.devj.gestantescontrol.domain.intents.HomeIntent
import com.devj.gestantescontrol.domain.model.*
import com.devj.gestantescontrol.presenter.model.PregnantUI
import com.devj.gestantescontrol.presenter.ui.adapters.PregnantAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter : PregnantAdapter
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        navController = findNavController()

        lifecycleScope.launchWhenResumed{
            viewModel.intentFlow.emit(HomeIntent.EnterAtHome)
        }
        setupRecyclerView()

        observeState()

        binding.fab.setOnClickListener {
            navigateToEdition()
        }
    }


    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.viewState.collect { state ->
                    render(state)
                }
            }
        }
    }

    private suspend  fun render(state: HomeViewState) {
        state.pregnantList.collect{
            homeAdapter.submitList(it)
        }
        if (state.isDataBaseEmpty) binding.contenidoLayout.textvTest.text = "La base de datos esta vacia"
    }

    private fun setupRecyclerView() {
        homeAdapter = PregnantAdapter { navigateToDetail(it) }
        binding.contenidoLayout.recyclerView.apply {
           layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = homeAdapter
        }
    }

    private fun navigateToDetail(pregnant: PregnantUI) {
        navController.navigate(FragmentHomeDirections.actionFragmentHomeToFragmentPregnantDetail(pregnant))
    }
    private fun navigateToEdition() {
        navController.navigate(FragmentHomeDirections.actionFragmentHomeToFragmentEdition())
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
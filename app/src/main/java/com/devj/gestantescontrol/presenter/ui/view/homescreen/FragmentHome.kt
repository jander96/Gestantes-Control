package com.devj.gestantescontrol.presenter.ui.view.homescreen



import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.DataDateEmbedded
import com.devj.gestantescontrol.data.cache.model.MeasuresEmbedded
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.data.cache.model.RiskFactorEmbedded
import com.devj.gestantescontrol.databinding.FragmentHomeBinding
import com.devj.gestantescontrol.domain.intents.HomeIntent
import com.devj.gestantescontrol.domain.model.*
import com.devj.gestantescontrol.presenter.ui.adapters.PregnantAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentHome : Fragment(R.layout.fragment_home) {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter : PregnantAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        lifecycleScope.launch {
            viewModel.intentFlow.emit(HomeIntent.EnterAtHome)
            Log.d("MVIArc", "Se lanzó el intent")
        }
        setupRecyclerView()

        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.viewState.collect { state ->
                    render(state)
                }
            }

        }


        binding.fab.setOnClickListener {

        }
    }

    private suspend  fun render(state: HomeViewState) {
        state.pregnantList.collect{
            homeAdapter.submitList(it)
        }

        binding.contenidoLayout.textvTest.text = state.error.toString()
    }

    private fun setupRecyclerView() {
        homeAdapter = PregnantAdapter { navigateToDetail() }
        binding.contenidoLayout.recyclerView.apply {
           layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            adapter = homeAdapter
        }

    }

    private fun navigateToDetail() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
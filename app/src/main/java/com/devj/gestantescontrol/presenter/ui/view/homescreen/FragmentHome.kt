package com.devj.gestantescontrol.presenter.ui.view.homescreen



import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.data.cache.dao.PregnantDao
import com.devj.gestantescontrol.data.cache.model.PregnantEntity
import com.devj.gestantescontrol.databinding.FragmentHomeBinding
import com.devj.gestantescontrol.domain.intents.HomeIntent
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.Measures
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.domain.model.RiskFactor
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
    private lateinit var adapter : PregnantAdapter

    @Inject
    lateinit var dao : PregnantDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        adapter = PregnantAdapter { navigateToDetail() }
        lifecycleScope.launch {
            viewModel.intentFlow.emit(HomeIntent.EnterAtHome)
        }


        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main){
                viewModel.viewState.collect{state->
                    if(state.isDataBaseEmpty) binding.contenidoLayout.textvTest.text = "Esta limpia la base de datos" else "No esta limpia la base de datos"
                }
        }
        setupRecyclerView()

        binding.fab.setOnClickListener {
            val pregnant = Pregnant(
                0,"Tita","Perez",23,"123456",
                Measures(12.3,32.4),
                DataDate("",true,"",12,2,
                    "",12,34,"",23,
                    56),listOf(RiskFactor("")),"","")
            lifecycleScope.launch(Dispatchers.IO){ dao.insertPregnant(PregnantEntity.fromDomain(pregnant))}
        }
    }

    private fun setupRecyclerView() {
        binding.contenidoLayout.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }

    private fun navigateToDetail() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
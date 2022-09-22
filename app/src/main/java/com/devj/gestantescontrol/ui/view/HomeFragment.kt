package com.devj.gestantescontrol.ui.view


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentHomeBinding
import com.devj.gestantescontrol.domain.toGestante
import com.devj.gestantescontrol.domain.toGestanteParcelable
import com.devj.gestantescontrol.ui.viewmodel.HomeViewModel
import com.devj.gestantescontrol.ui.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(requireActivity().applicationContext)
    }
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        startAnimation()
        navController = findNavController()
        recyclerView = binding.contenido.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = GestantesAdapter { gestante->


            navController.navigate(HomeFragmentDirections.actionGoToDetail(gestante.toGestanteParcelable()))
        }
        recyclerView.adapter = adapter

       viewModel.gestantesList.observe(this.viewLifecycleOwner){ gestanteList->
            gestanteList.let {
                adapter.submitList(it.map { gestanteEntity ->
                    gestanteEntity.toGestante()
                })
            }
        }



        binding.fab.setOnClickListener {
            navController.navigate(R.id.home_to_edicion)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar, menu)

    }
    private fun startAnimation(){


        binding.contenido.viewAnimation.playAnimation()
        viewLifecycleOwner.lifecycleScope.launch {
            delay(2500)
            binding.contenido.viewAnimation.visibility = View.INVISIBLE

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.acercaDeFragment -> {
                return item.onNavDestinationSelected(navController)
                        || super.onOptionsItemSelected(item)
            }


            else -> false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
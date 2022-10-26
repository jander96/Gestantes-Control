package com.devj.gestantescontrol.framework.ui.view


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
import com.devj.gestantescontrol.GestantesApplication
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentHomeBinding
import com.devj.gestantescontrol.domain.RepoImpl
import com.devj.gestantescontrol.domain.toGestanteParcelable
import com.devj.gestantescontrol.framework.roomdatabase.AppGestanteDatabase
import com.devj.gestantescontrol.framework.roomdatabase.LocalDataBaseImpl
import com.devj.gestantescontrol.framework.ui.viewmodel.HomeViewModel
import com.devj.gestantescontrol.framework.ui.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/*Este fragment es el encargado de mostrar al usuario la lista de gestantes que se encuentran el la base de datos  */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var roomdatabase:AppGestanteDatabase
    private lateinit var repo : RepoImpl
    private val viewModel: HomeViewModel by viewModels {
        HomeViewModelFactory(repo)
    }
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
         roomdatabase = (requireContext().applicationContext as GestantesApplication).database
         repo = RepoImpl(LocalDataBaseImpl(roomdatabase))

        startAnimation()
        navController = findNavController()
        recyclerView = binding.contenido.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = GestantesAdapter {
            navController.navigate(HomeFragmentDirections.actionGoToDetail(it.toGestanteParcelable()))
        }
        
        recyclerView.adapter = adapter

       viewModel.gestantesList.observe(this.viewLifecycleOwner){ gestanteList->
            gestanteList.let {
                adapter.submitList(it)
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
    //Sobreescribo el metodo para q al seleccionar el item se navegue hasta
    // el destino con la id indicada
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.acercaDeFragment -> {
                return item.onNavDestinationSelected(navController)
                        || super.onOptionsItemSelected(item)
            }
            R.id.ajustesFragment -> {
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
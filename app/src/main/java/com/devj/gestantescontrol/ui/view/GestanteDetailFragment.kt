package com.devj.gestantescontrol.ui.view


import android.app.AlertDialog
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
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentDetailBinding
import com.devj.gestantescontrol.ui.viewmodel.DetailViewModel
import com.devj.gestantescontrol.ui.viewmodel.DetailViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GestanteDetailFragment : Fragment(R.layout.fragment_detail) {
    private var _binding: FragmentDetailBinding? = null

    val binding get() = _binding!!

    private val args: GestanteDetailFragmentArgs by navArgs()

    val viewModel: DetailViewModel by viewModels{
        DetailViewModelFactory(args, requireActivity().applicationContext)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        navController = findNavController()

        viewModel.gestante.observe(viewLifecycleOwner) { gestante ->
            viewModel.bind(binding, gestante)
        }


        binding.btnIcTelefono.setOnClickListener {
            viewModel.llamar(requireContext())
        }
        binding.btnIcSms.setOnClickListener {
            viewModel.enviarSms(requireContext())
        }


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_editar -> {
                viewModel.passDataAndNavigateToEdicion(navController)
                true
            }
            R.id.menu_item_eliminar -> {
                showAlertDialog()

                true
            }

            else -> false
        }
    }

    private fun showAlertDialog() {
       val builder: AlertDialog.Builder? = activity?.let {
           AlertDialog.Builder(it)
       }
        builder?.setTitle("Eliminar")
            ?.setMessage("Estas seguro de eliminar gestante")
            ?.setPositiveButton("Si"){_,_ ->
                navController.navigateUp()
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) { viewModel.eliminarGestante() }
                }
            }
            ?.setNegativeButton("No") { _, _ ->

            }
        val alertDialog = builder?.create()
        alertDialog?.show()

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()

    }
}




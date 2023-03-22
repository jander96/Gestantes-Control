package com.devj.gestantescontrol.presenter.ui.view.detailscreen


import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentDetailBinding
import com.devj.gestantescontrol.domain.intents.DetailIntent
import com.devj.gestantescontrol.domain.model.DetailViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentPregnantDetail : Fragment(R.layout.fragment_detail), MenuProvider {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val args: FragmentPregnantDetailArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        navController = findNavController()

        observeViewState()

        binding.btnIcTelefono.setOnClickListener {

        }
        binding.btnIcSms.setOnClickListener {

        }

    }

    private fun observeViewState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.viewState.collect{state->
                    renderView(state)
                }
            }
        }
    }

    private fun renderView(state: DetailViewState) {
        if (!state.isDeletedView){
            showPregnantDetailsView()
        }else{
            showPregnantDeletedView()
        }
    }

    private fun showPregnantDeletedView() {
        TODO("Not yet implemented")
    }

    private fun showPregnantDetailsView() {
        binding.tvEdad.text = args.pregnant.age.toString()
    }



    private fun navigateToEditionView() {
        navController.navigate(
            FragmentPregnantDetailDirections
                .actionFragmentPregnantDetailToFragmentEdition(args.pregnant.id)
        )
    }

    private fun showDeleteAlertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.alert_dialog_delete_pregnant_title)
            .setMessage(R.string.alert_dialog_delete_pregnant_message)
            .setNegativeButton(R.string.alert_dialog_delete_pregnant_negative_button) { _, _ -> }
            .setPositiveButton(R.string.alert_dialog_delete_pregnant_positive_button) { _, _ ->
                sendDeletePregnantIntent()
            }
            .show()
    }

    private fun sendDeletePregnantIntent() {
        binding.btnIcSms.setOnClickListener {
            lifecycleScope.launch {
                viewModel.intentFlow.emit(DetailIntent.DeletePregnant(args.pregnant.id))
            }
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_detail, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_item_eliminar -> {
                showDeleteAlertDialog()
                true
            }
            R.id.menu_item_editar -> {
                navigateToEditionView()
                true
            }
            else -> super.onContextItemSelected(menuItem)
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }




}




package com.devj.gestantescontrol.presenter.ui.view.editionscreen


import android.os.Bundle
import android.util.Log
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
import com.devj.gestantescontrol.domain.model.DataDate
import com.devj.gestantescontrol.domain.model.EditionViewState
import com.devj.gestantescontrol.domain.model.Pregnant
import com.devj.gestantescontrol.utils.getDate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentEdition : Fragment(R.layout.fragment_edicion) {
    companion object{
        const val US_PICKER_TAG = "DATE_PICKER_US"
        const val FUM_PICKER_TAG = "DATE_PICKER_FUM"
    }

    private var _binding: FragmentEdicionBinding? = null
    private val binding get() = _binding!!
    private val viewModel : EditionViewModel by viewModels()
    private val args : FragmentEditionArgs by navArgs()
    private lateinit var datePickerFUM: DialogDatePickerFUM
    private lateinit var datePickerUS: DialogDatePickerUS



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEdicionBinding.bind(view)
        datePickerFUM = DialogDatePickerFUM{ year, month, day ->
            viewLifecycleOwner.lifecycleScope.launch{
                repeatOnLifecycle(Lifecycle.State.RESUMED ){
                    viewModel.intentFlow.emit(EditionIntent.FUMDatePicked(getDate(year, month, day)))
                }
            }
        }
        datePickerUS = DialogDatePickerUS{ year,month,day,weeksOnUS,daysOnUS->
            viewLifecycleOwner.lifecycleScope.launch{
                repeatOnLifecycle(Lifecycle.State.RESUMED ){
                    viewModel.intentFlow.emit(EditionIntent.USDatePicked(getDate(year, month, day),weeksOnUS,daysOnUS))
                }
            }
        }
            .apply { isCancelable = true }
        setupDatePickers()


        if(args.pregnantId != 0) refillFieldsIntent()
        observeState()



    }


    private fun setupDatePickers() {
        binding.btnFum?.setOnClickListener {
            datePickerFUM.show(requireActivity().supportFragmentManager,FUM_PICKER_TAG)
        }
        binding.btnUsg?.setOnClickListener {
            datePickerUS.show(requireActivity().supportFragmentManager,US_PICKER_TAG)
        }
    }
    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.viewState.collect { state ->
                    render(state)
                }
            }
        }
    }

    private fun render(state: EditionViewState) {
        val pregnant = state.pregnant
        if (pregnant != null) with(binding){
            etNombre.setText(pregnant.name)
            etApellidos.setText(pregnant.lastName)
            etEdad.setText(pregnant.age.toString())
            etPeso?.setText(pregnant.measures?.weight.toString())
            etTalla?.setText(pregnant.measures?.size.toString())
            etTelefono?.setText(pregnant.phoneNumber)
            etNotas?.setText(pregnant.notes)
        }
    }


    private fun refillFieldsIntent() {
        viewLifecycleOwner.lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.RESUMED ){
                Log.d("Intent","Lanzado el rellenado de formularios")
                viewModel.intentFlow.emit(EditionIntent.RefillFields(args.pregnantId))
            }
        }
    }

}
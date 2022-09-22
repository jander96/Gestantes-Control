package com.devj.gestantescontrol.ui.view


import android.Manifest
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.createBitmapFromImageUri
import com.devj.gestantescontrol.core.getResizeBitmap
import com.devj.gestantescontrol.core.hideKeyboard
import com.devj.gestantescontrol.core.setImageFromBitmap
import com.devj.gestantescontrol.databinding.FragmentEdicionBinding
import com.devj.gestantescontrol.ui.viewmodel.EdicionViewModel
import com.devj.gestantescontrol.ui.viewmodel.EdicionViewModelFactory



class EdicionFragment : Fragment(R.layout.fragment_edicion) {

    private var _binding: FragmentEdicionBinding? = null
    val binding get() = _binding!!
    private val args: EdicionFragmentArgs by navArgs()
    private val viewModel: EdicionViewModel by viewModels {
        EdicionViewModelFactory(binding, args)
    }
    private lateinit var navController: NavController

    private val launcherContactos = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            // contactUri contiene la ruta al registro del Contacto escogido en la tabla Contacts
            val contactUri = it.data?.data!!

            binding.etTelefono!!.setText(viewModel.getContact(requireContext(), contactUri))
        }
    }
    private val launcherGaleria = registerForActivityResult(StartActivityForResult()) {


        if (it.resultCode == RESULT_OK) {
            val uriFoto = it.data?.data

            try {
             val bitmap = createBitmapFromImageUri(requireContext(),uriFoto!!)
                setImageFromBitmap(getResizeBitmap(bitmap,360,480,true),binding.foto)

            } catch (e: Exception) {

            }

            viewModel.fotoString = uriFoto.toString()

        }
    }
    private val requestPermision =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGaranted ->
            when {
                isGaranted -> viewModel.pickContact( launcherContactos)
                shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS) ->
                    Toast.makeText(
                        requireContext(),
                        "Debes aprobar el permiso para acceder a los contactos ",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("EdicionCycle","On ViewCreated")
        _binding = FragmentEdicionBinding.bind(view)
        navController = findNavController()

        viewModel.rellenarCamposArgs()

        binding.btnFum?.setOnClickListener {
            it.hideKeyboard()
            viewModel.navigateAndPassDataToFum(navController)
        }

        binding.btnUsg?.setOnClickListener {
            it.hideKeyboard()
            viewModel.navigateAndPassDatatoFug(navController)
        }
        binding.ibtnTelefono!!.setOnClickListener {

            it.hideKeyboard()
            requestPermision.launch(Manifest.permission.READ_CONTACTS)

        }
        binding.foto.setOnClickListener {
            viewModel.pickImage(launcherGaleria)
        }

    }

    override fun onDestroy() {
        Log.d("EdicionCycle","On Destroy")
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_edicion, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_guardar -> {
                binding.root.hideKeyboard()
                viewModel.reviewAndSave(requireActivity().applicationContext, navController)
                true
            }

            else -> false
        }
    }
}
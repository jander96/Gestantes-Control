package com.devj.gestantescontrol.framework.ui.view


import android.Manifest
import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.GestantesApplication
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.hideKeyboard
import com.devj.gestantescontrol.databinding.FragmentEdicionBinding
import com.devj.gestantescontrol.domain.RepoImpl
import com.devj.gestantescontrol.framework.roomdatabase.AppGestanteDatabase
import com.devj.gestantescontrol.framework.roomdatabase.LocalDataBaseImpl
import com.devj.gestantescontrol.framework.ui.viewmodel.EdicionViewModel
import com.devj.gestantescontrol.framework.ui.viewmodel.EdicionViewModelFactory



class EdicionFragment : Fragment(R.layout.fragment_edicion) {

    private var _binding: FragmentEdicionBinding? = null
    val binding get() = _binding!!
    private val args: EdicionFragmentArgs by navArgs()
    private lateinit var roomdatabase: AppGestanteDatabase
    private lateinit var repo : RepoImpl
    private val viewModel: EdicionViewModel by viewModels {
        EdicionViewModelFactory(repo)
    }
    private lateinit var navController: NavController

    private val launcherContactos = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            // contactUri contiene la ruta al registro del Contacto escogido en la tabla Contacts
            val contactUri = it.data?.data!!
            binding.etTelefono!!.setText(viewModel.getContact(requireContext(), contactUri))
        }
    }
    private val launcherGaleria =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            viewModel.saveBitmaptoInternalStorage(requireContext(), bitmap,binding)
        }
    private val requestPermision =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGaranted ->
            when {
                isGaranted -> viewModel.pickContact(launcherContactos)
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
        Log.d("EdicionCycle", "On ViewCreated")
        _binding = FragmentEdicionBinding.bind(view)
        roomdatabase = (requireContext().applicationContext as GestantesApplication).database
        repo = RepoImpl(LocalDataBaseImpl(roomdatabase))
        navController = findNavController()

        viewModel.rellenarCamposArgs(requireContext(),args,binding)

        binding.btnFum?.setOnClickListener {
            it.hideKeyboard()
            viewModel.navigateAndPassDataToFum(navController,args, binding)
        }

        binding.btnUsg?.setOnClickListener {
            it.hideKeyboard()
            viewModel.navigateAndPassDatatoFug(navController,args, binding)
        }
        binding.ibtnTelefono!!.setOnClickListener {

            it.hideKeyboard()
            requestPermision.launch(Manifest.permission.READ_CONTACTS)

        }
        binding.foto.setOnClickListener {
            launcherGaleria.launch()
        }

    }

    override fun onDestroy() {
        Log.d("EdicionCycle", "On Destroy")
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_toolbar_edicion, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_item_guardar -> {
                binding.root.hideKeyboard()
                viewModel.reviewAndSave(navController,args, binding)
                true
            }

            else -> false
        }
    }
}
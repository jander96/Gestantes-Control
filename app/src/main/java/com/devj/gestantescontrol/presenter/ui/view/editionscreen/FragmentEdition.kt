package com.devj.gestantescontrol.presenter.ui.view.editionscreen


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.content.FileProvider
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.FragmentEdicionBinding
import com.devj.gestantescontrol.domain.intents.EditionIntent
import com.devj.gestantescontrol.domain.model.EditionViewState
import com.devj.gestantescontrol.domain.model.Formulary
import com.devj.gestantescontrol.utils.ifEmptyReturnNull
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class FragmentEdition : Fragment(R.layout.fragment_edicion), MenuProvider {
    companion object {
        const val US_PICKER_TAG = "DATE_PICKER_US"
        const val FUM_PICKER_TAG = "DATE_PICKER_FUM"
        const val FILE_PROVIDER_AUTHORITY = "com.devj.gestantescontrol.fileprovider"
        const val FLOAT_FORMAT = "^\\d\\.\\d{2}\$|^$"
    }

    private var _binding: FragmentEdicionBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditionViewModel by viewModels()
    private val args: FragmentEditionArgs by navArgs()
    private lateinit var datePickerFUM: DialogDatePickerFUM
    private lateinit var datePickerUS: DialogDatePickerUS
    private var uri: Uri? = null
    private val photoPickerResults =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) uri?.let { binding.foto.setImageURI(it) }
        }
    private val contactPickerResults =
        registerForActivityResult(ActivityResultContracts.PickContact()) { uri ->
            uri?.let { getContactFromUri(uri) }
        }
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissionsMap ->
            if (permissionsMap[Manifest.permission.CAMERA] == true &&
                permissionsMap[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
            ) {
                launchCamera()
            } else {
                showSnackBarAlert()
            }
        }
    private val permissionContactLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                contactPickerResults.launch()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentEdicionBinding.bind(view)
        (requireActivity() as MenuHost).addMenuProvider(this, viewLifecycleOwner)
        setupDatePickers()
        datePickerFUM = DialogDatePickerFUM { year, month, day ->
            binding.btnFum?.setText(getString(R.string.date_picker_fum_text, day, month, year))
        }
        datePickerUS = DialogDatePickerUS { year, month, day, weeksOnUS, daysOnUS ->
            binding.btnUsg?.setText(getString(R.string.date_picker_us_text, day, month, year))
            binding.tvWeeksUs?.text = getString(R.string.weeks, weeksOnUS)
            binding.tvDayUs?.text = getString(R.string.days, daysOnUS)
        }.apply { isCancelable = true }

        if (args.pregnantUI != null) refillFieldsIntent()
        observeState()

        binding.foto.setOnClickListener {
            permissionLauncher.launch(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            )
        }
        binding.ibtnTelefono?.setOnClickListener {
            permissionContactLauncher.launch(Manifest.permission.READ_CONTACTS)
        }


    }


    private fun setupDatePickers() {
        binding.btnFum?.isFocusable = false
        binding.btnFum?.setOnClickListener {
            val fumPicker = requireActivity().supportFragmentManager.findFragmentByTag(FUM_PICKER_TAG)
            if (fumPicker == null)
                datePickerFUM.show(requireActivity().supportFragmentManager, FUM_PICKER_TAG)
        }
        binding.btnUsg?.isFocusable = false
        binding.btnUsg?.setOnClickListener {
            val usPicker = requireActivity().supportFragmentManager.findFragmentByTag(US_PICKER_TAG)
            if (usPicker == null)
                datePickerUS.show(requireActivity().supportFragmentManager, US_PICKER_TAG)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { state ->

                    render(state)
                }
            }
        }
    }

    private fun refillFieldsIntent() {
        with(binding) {
            etNombre.setText(args.pregnantUI?.name)
            etApellidos.setText(args.pregnantUI?.lastName)
            etEdad.setText(args.pregnantUI?.age ?: "")
            etTalla?.setText(args.pregnantUI?.size ?: "")
            etPeso?.setText(args.pregnantUI?.weight ?: "")
            etTelefono?.setText(args.pregnantUI?.phoneNumber)
            btnFum?.setText(args.pregnantUI?.fum)
            btnUsg?.setText(args.pregnantUI?.firstUS)
            etNotas?.setText(args.pregnantUI?.notes)
            if (args.pregnantUI?.isFUMReliable != null) {
                cbFumConfiable?.isChecked = args.pregnantUI?.isFUMReliable!!
            }
        }
    }

    private fun render(state: EditionViewState) {
        val pregnant = state.pregnant
        if (pregnant != null) with(binding) {
            etNombre.setText(pregnant.name)
            etApellidos.setText(pregnant.lastName)
            etEdad.setText(pregnant.age.toString())
            etPeso?.setText(pregnant.measures?.weight.toString())
            etTalla?.setText(pregnant.measures?.size.toString())
            etTelefono?.setText(pregnant.phoneNumber)
            etNotas?.setText(pregnant.notes)
        }
    }

    private fun launchCamera() {
        val timeStamp = "${System.currentTimeMillis() / 100000}"
        val filePicture =
            File(requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES), timeStamp)
        uri = FileProvider.getUriForFile(requireContext(), FILE_PROVIDER_AUTHORITY, filePicture)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, uri)
        }

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            photoPickerResults.launch(intent)
        }
    }

    private fun getContactFromUri(uri: Uri) {
        var id: String? = null
        val projection = arrayOf(ContactsContract.Contacts._ID)
        val cursor = requireContext()
            .contentResolver
            .query(uri, projection, null, null, null)

        if (cursor!!.moveToFirst()) id = cursor.getString(0)
        cursor.close()

        val selection =
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ? AND " +
                    ContactsContract.CommonDataKinds.Phone.TYPE + "= " +
                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE
        val cursorPhone = requireContext().contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            selection,
            arrayOf(id),
            null
        )
        if (cursorPhone!!.moveToFirst()) {
            val phone = cursorPhone.getString(0)
            binding.etTelefono?.setText(phone)
        }
        cursorPhone.close()
    }

    private fun showSnackBarAlert() {
        Snackbar.make(binding.root, R.string.no_permissions_granted, Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_toolbar_edicion, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.menu_item_guardar -> {
                if (isFormularyReady()) sendSaveIntent()
                else showWrongDataView()
                true
            }
            else -> false
        }
    }


    private fun isFormularyReady(): Boolean {
        1.1
        return with(binding) {
            etNombre.text.toString().isNotEmpty() &&
                    etApellidos.text.toString().isNotEmpty() &&
                    (btnFum?.text.toString().isNotEmpty() || btnUsg?.text.toString().isNotEmpty())
                    && isSizeInCorrectFormat()
        }
    }

    private fun showWrongDataView() {
        if (!isSizeInCorrectFormat()) binding.inputLayoutEdad.error =
            getString(R.string.wrong_float_format)
        Snackbar.make(binding.root, R.string.wrong_or_incomplete_formulary, Snackbar.LENGTH_SHORT)
            .show()
    }


    private fun isSizeInCorrectFormat(): Boolean {
        val regex = Regex(FLOAT_FORMAT)
        return binding.etTalla?.text.toString().matches(regex)
    }

    private fun sendSaveIntent() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.intentFlow.emit(EditionIntent.SaveDataPregnant(getFormulary()))
            }
        }
    }

    private fun getFormulary(): Formulary {
        return with(binding) {
            Formulary(
                id = args.pregnantUI?.id ?: 0,
                name = etNombre.text.toString(),
                lastName = etApellidos.text.toString(),
                age = etEdad.text.toString().ifEmptyReturnNull(),
                weight = etPeso?.text.toString().ifEmptyReturnNull(),
                size = etTalla?.text.toString().ifEmptyReturnNull(),
                phoneNumber = etTelefono?.text.toString().ifEmptyReturnNull(),
                fUM = btnFum?.text.toString().ifEmptyReturnNull(),
                isFUMReliable = cbFumConfiable?.isChecked ?: false,
                firstFUG = btnUsg?.text.toString().ifEmptyReturnNull(),
                firstUSWeeks = tvWeeksUs?.text.toString().ifEmptyReturnNull(),
                firstUSDays = tvDayUs?.text.toString().ifEmptyReturnNull(),
                secondFUG = null,
                secondUSWeeks = null,
                secondUSDays = null,
                thirdFUG = null,
                thirdUSWeeks = null,
                thirdUSDays = null,
                notes = etNotas?.text.toString().ifEmptyReturnNull()

            )
        }

    }


}
package com.devj.gestantescontrol.framework.ui.viewmodel

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.ContactsContract
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.core.getBitmapFromFile
import com.devj.gestantescontrol.databinding.FragmentEdicionBinding
import com.devj.gestantescontrol.domain.*
import com.devj.gestantescontrol.framework.ui.view.EdicionFragmentArgs
import com.devj.gestantescontrol.framework.ui.view.EdicionFragmentDirections
import com.devj.gestantescontrol.usescases.EditarGestanteUseCase
import com.devj.gestantescontrol.usescases.InsertarGestanteUseCase
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


class EdicionViewModel(
    private val repo: Repo
) : ViewModel() {

    private var _gestante = MutableLiveData<Gestante>()
    val gestante: LiveData<Gestante> get() = _gestante
    private var fotoFileName = ""


    private fun onCreateGestante(args: EdicionFragmentArgs,binding: FragmentEdicionBinding) {

        _gestante.value = Gestante(
            id = args.id,
            nombre = binding.etNombre.text.toString(),
            apellidos = binding.etApellidos.text.toString().ifEmpty { "" },
            edad = binding.etEdad.text.toString().ifEmpty { "" },
            peso = if (binding.etPeso?.text.toString().isNotEmpty()) {
                binding.etPeso!!.text.toString()
            } else "",
            talla = if (binding.etTalla?.text.toString().isNotEmpty()) {
                binding.etTalla!!.text.toString()
            } else "",
            fum = args.fum,
            fug = args.fug,
            cantSemanasUG = args.cantSemanas.toString(),
            cantDiasUG = args.cantDias.toString(),
            telefono = if (binding.etTelefono?.text.toString().isNotEmpty()) {
                binding.etTelefono!!.text.toString()
            } else "",
            notas = if (binding.etNotas?.text.toString().isNotEmpty()) {
                binding.etNotas!!.text.toString()
            } else "",
            fumConfiable = binding.cbFumConfiable!!.isChecked,
            foto = if (fotoFileName != "") {
                fotoFileName
            } else args.foto

        )
    }


    private fun insertarGestante() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                InsertarGestanteUseCase(repo)
                    .insertarGestanteUseCase(_gestante.value!!)
            }
        }
    }

    private fun actualizarGestante() {
        viewModelScope.launch(Dispatchers.IO) {
            EditarGestanteUseCase(repo)
                .editarGestante(gestante.value!!)
        }
    }


    fun rellenarCamposArgs(contexto: Context,args: EdicionFragmentArgs,binding: FragmentEdicionBinding) {

        binding.etNombre.setText(args.nombre)
        binding.etApellidos.setText(args.apellidos)
        binding.etEdad.setText(args.edad)
        binding.etPeso?.setText(args.peso)
        binding.etTalla?.setText(args.talla)
        binding.etTelefono?.setText(args.telefono)
        binding.etNotas?.setText(args.nota)
        binding.cbFumConfiable?.isChecked = args.fumConfiable
        when {
            fotoFileName != "" -> binding.foto.setImageBitmap(
                getBitmapFromFile(
                    contexto,
                    fotoFileName
                )
            )
            args.foto != "" -> binding.foto.setImageBitmap(getBitmapFromFile(contexto, args.foto))
            else -> binding.foto.setImageResource(R.drawable.ic_camera)
        }
    }

    private fun btnSaveToch(binding: FragmentEdicionBinding) {
        if (gestante.value?.id == 0) {
            insertarGestante()
            createSnackBar("Se añadio con exito",binding)
        } else {
            actualizarGestante()
           createSnackBar("Se actualizó con exito",binding)
        }
    }

    fun navigateAndPassDataToFum(navController: NavController,args: EdicionFragmentArgs,binding: FragmentEdicionBinding) {
        navController.navigate(
            EdicionFragmentDirections.actionEdicionFragmentToCalendarioFUMFragment(
                args.id,
                binding.etNombre.text.toString(),
                binding.etApellidos.text.toString(),
                binding.etEdad.text.toString(),
                binding.etPeso?.text.toString(),
                binding.etTalla?.text.toString(),
                binding.etTelefono?.text.toString(),
                binding.etNotas?.text.toString(),
                binding.cbFumConfiable?.isChecked!!,
                args.fug,
                if (fotoFileName == "") {
                    args.foto
                } else {
                    fotoFileName
                }
            )
        )
    }

    fun navigateAndPassDatatoFug(navController: NavController,args: EdicionFragmentArgs,binding: FragmentEdicionBinding) {

        navController.navigate(
            EdicionFragmentDirections.actionEdicionFragmentToCalendarioUGFragment(
                args.id,
                binding.etNombre.text.toString(),
                binding.etApellidos.text.toString(),
                binding.etEdad.text.toString(),
                binding.etPeso?.text.toString(),
                binding.etTalla?.text.toString(),
                binding.etTelefono?.text.toString(),
                binding.etNotas?.text.toString(),
                binding.cbFumConfiable?.isChecked!!,
                args.fum,
                if (fotoFileName == "") {
                    args.foto
                } else {
                    fotoFileName
                }
            )
        )
    }

    fun reviewAndSave(navController: NavController,args: EdicionFragmentArgs,binding: FragmentEdicionBinding) {
        if (binding.etNombre.text.isEmpty()) {
            binding.inputLayoutNombre.error = "Campo Obligatorio"
        } else if (binding.cbFumConfiable!!.isChecked && args.fum == "0/0/0") {
           createSnackBar("Debe escoger FUM",binding)
        } else if (!binding.cbFumConfiable.isChecked && args.fug == "0/0/0" && args.fum == "0/0/0") {
            createSnackBar("Debe escoger FUM o FUG",binding)
        } else if (!binding.cbFumConfiable.isChecked && args.fug == "0/0/0" && args.fum != "0/0/0") {
            createSnackBar("Debe escoger FUG si FUM no es confiable",binding)
        } else {
            onCreateGestante(args,binding)
            btnSaveToch(binding)
            navController.navigate(R.id.action_edicionFragment_to_homeFragment)
        }
    }

    fun pickContact(launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        launcher.launch(intent)
    }


    fun getContact(contexto: Context, uri: Uri): String {
        // id representa el ID del contacto en la tabla contact
        var id = ""

        // phone representa el numero de telefono en la tabla contact
        var phone = ""

        // Como ruta especifico la uri que recivo que corresponde
        // con el registro del contacto escogido por la intent
        // De este registro solo me interesa recuperar el valor q
        // contiene la columna ID
        val projection = arrayOf(ContactsContract.Contacts._ID)

        val cursor = contexto.contentResolver.query(
            uri,
            projection,
            null,
            null,
            null
        )
        // Obtengo el valor de ID que por logica debe estar en la columna 0
        // ya que es una sola columna en la proyeccion y se lo asigno al avariable id
        if (cursor!!.moveToFirst()) {
            id = cursor.getString(0)
        }
        cursor.close()
        //
        /*Ya tengo el _ID con este dato puedo obtener el numero de telefono correspondiente
        * en la Tabla Data, ya que este no cambia */


        /* Debido a la diversidad de la tabla Data el el que no todos los registros contienen
        * el mismo tipo de informacion para una misma columna debemos especificar q el valor a
        * retornar debe ser de tipo numero de telefono movil
        *
        *Ademas
        * que el registro contenga el valor de la columna_ID igual al argumento q  se pase */
        val seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ? AND " +
                ContactsContract.CommonDataKinds.Phone.TYPE + "= " +
                ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE

        val cursorPhone = contexto.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            seleccion,
            arrayOf(id),//Se pasa por argumento el id que contiene el valor de Contact._ID
            null
        )
        if (cursorPhone!!.moveToFirst()) {
            phone = cursorPhone.getString(0)
        }
        cursorPhone.close()

        return phone.removePrefix("+")

    }

    fun saveBitmaptoInternalStorage(contexto: Context, bitmap: Bitmap?,binding: FragmentEdicionBinding) {
        if (bitmap != null) {
            val fileName = "photo_gestante" + "${System.currentTimeMillis() / 1000}"
            binding.foto.setImageBitmap(bitmap)

            try {
                //Create file to save photo
                File(contexto.filesDir, fileName)
                contexto.openFileOutput(fileName, Context.MODE_PRIVATE)
                    .use { fileOutputStream ->
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                    }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            fotoFileName = fileName
        }
    }
   private  fun createSnackBar(message: String,binding: FragmentEdicionBinding){
        Snackbar.make(binding.root,message,Snackbar.LENGTH_LONG).show()
    }


}

class EdicionViewModelFactory(
    private val repo: Repo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(EdicionViewModel::class.java)) {
            return EdicionViewModel(repo) as T
        }
        throw IllegalArgumentException("No se corresponde con la clase viewModel esperada")
    }
}
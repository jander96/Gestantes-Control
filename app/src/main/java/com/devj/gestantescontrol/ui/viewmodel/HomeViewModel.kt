package com.devj.gestantescontrol.ui.viewmodel


import android.content.Context
import androidx.lifecycle.*
import com.devj.gestantescontrol.data.database.entities.GestanteEntity
import com.devj.gestantescontrol.domain.GetAllGestantesUseCase
import kotlinx.coroutines.flow.Flow


class HomeViewModel(private val applicationContext: Context) : ViewModel() {
val gestantesList : LiveData<List<GestanteEntity>> = getAllGestantes(applicationContext).asLiveData()

   private fun getAllGestantes(context: Context): Flow<List<GestanteEntity>> {
        return GetAllGestantesUseCase(context).getAllGestantesUseCase()
    }
}


class HomeViewModelFactory(private val applicationContext: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(applicationContext) as T
        }
        throw IllegalArgumentException("No se corresponde el tipo de ViewModel con el esperado")
    }
}
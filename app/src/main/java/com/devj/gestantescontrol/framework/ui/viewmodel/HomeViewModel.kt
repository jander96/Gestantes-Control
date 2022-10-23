package com.devj.gestantescontrol.framework.ui.viewmodel


import android.content.Context
import androidx.lifecycle.*
import com.devj.gestantescontrol.domain.Gestante
import com.devj.gestantescontrol.domain.Repo
import com.devj.gestantescontrol.framework.roomdatabase.entities.GestanteEntity
import com.devj.gestantescontrol.usescases.GetAllGestantesUseCase
import kotlinx.coroutines.flow.Flow


class HomeViewModel(private val repo: Repo) : ViewModel() {
val gestantesList : LiveData<List<Gestante>> = getAllGestantes().asLiveData()

   private fun getAllGestantes(): Flow<List<Gestante>> {
        return GetAllGestantesUseCase(repo).getAllGestantesUseCase()
    }
}


class HomeViewModelFactory(private val repo: Repo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repo) as T
        }
        throw IllegalArgumentException("No se corresponde el tipo de ViewModel con el esperado")
    }
}
package com.devj.gestantescontrol.presenter.ui

import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class MyMenuProvider(private val context: Context, private val menu: Menu, private val menuResId: Int): MenuHost {
    override fun addMenuProvider(provider: MenuProvider) {
        TODO("Not yet implemented")
    }

    override fun addMenuProvider(provider: MenuProvider, owner: LifecycleOwner) {
        TODO("Not yet implemented")
    }

    override fun addMenuProvider(
        provider: MenuProvider,
        owner: LifecycleOwner,
        state: Lifecycle.State
    ) {
        TODO("Not yet implemented")
    }

    override fun removeMenuProvider(provider: MenuProvider) {
        TODO("Not yet implemented")
    }

    override fun invalidateMenu() {
        TODO("Not yet implemented")
    }
}
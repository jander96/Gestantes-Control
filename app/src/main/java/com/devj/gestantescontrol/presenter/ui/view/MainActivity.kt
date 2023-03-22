package com.devj.gestantescontrol.presenter.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            toolbar.inflateMenu(R.menu.menu_toolbar)
        }

        screenSplash.setKeepOnScreenCondition { false }
        setupHost()
    }

    private fun setupHost() {
        (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .also { navController = it.navController }

        val appBarConfiguration = AppBarConfiguration(
            navController.graph,
            binding.drawerLayout
        )
       binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.navDrawer.setupWithNavController(navController)
    }
}
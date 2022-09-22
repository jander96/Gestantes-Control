package com.devj.gestantescontrol.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.devj.gestantescontrol.R
import com.devj.gestantescontrol.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController= navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.acercaDeFragment),binding.drawerLayout)
        binding.toolbar.setupWithNavController(navController,appBarConfiguration)
        binding.navDrawer.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            if(destination.id == R.id.gestanteDetailFragment ||
                destination.id == R.id.edicionFragment ||
                destination.id == R.id.calendarioFUMFragment ||
                destination.id == R.id.calendarioUGFragment) {
                binding.navDrawer.visibility = View.GONE

            } else {
                binding.navDrawer.visibility = View.VISIBLE
            }
        }
    }









}
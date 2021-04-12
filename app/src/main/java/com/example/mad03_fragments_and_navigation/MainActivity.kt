package com.example.mad03_fragments_and_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mad03_fragments_and_navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "This is the onCreate lifecycle")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        // get tha navController of our NavHostFragment
        navController = this.findNavController(R.id.navhostFragment)
        // setup the action bar/top menu with our navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
    // onCreate, onStart, onPause, onResume, onDestroy, onRestart, onStop


    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "This is the onStart lifecycle")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "This is the onPause lifecycle")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "This is the onResume lifecycle")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "This is the onDestroy lifecycle")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "This is the onRestart lifecycle")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "This is the onStop lifecycle")
    }
}
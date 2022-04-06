package com.example.mygym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mygym.databinding.ActivityMainBinding
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.mygym.screen.personalarea.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_about_gym.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)

//        resetDrawer()

        userViewModel.user.observe(this, {
            currentUser = it

//            resetDrawer()
        })


        if (currentUser != null) {
            Toast.makeText(this, currentUser?.phoneNumber, Toast.LENGTH_LONG).show()
            binding.navigationView.menu.findItem(R.id.personalAreaFragment).isVisible = true
            binding.navigationView.menu.findItem(R.id.authFragment).isVisible = false
            appBarConfiguration = AppBarConfiguration.Builder(
                R.id.startFragment,
                R.id.authFragment,
                R.id.personalAreaFragment,
                R.id.aboutGymFragment,
                R.id.rulesFragment,
            ).setOpenableLayout(drawerLayout).build()


        } else {
            Toast.makeText(this, "Юзер не зареган", Toast.LENGTH_LONG).show()
            binding.navigationView.menu.findItem(R.id.personalAreaFragment).isVisible = false
            binding.navigationView.menu.findItem(R.id.authFragment).isVisible = true
            appBarConfiguration = AppBarConfiguration.Builder(
                R.id.startFragment,
                R.id.authFragment,
                R.id.personalAreaFragment,
                R.id.aboutGymFragment,
                R.id.rulesFragment,
            ).setOpenableLayout(drawerLayout).build()
        }

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun resetDrawer() {

        val fragmentId = if (currentUser != null) {
            R.id.personalAreaFragment
            Log.d("fragmentId", "fragmentId: " + R.id.personalAreaFragment)
        } else {
            R.id.authFragment
            Log.d("fragmentId", "fragmentId: " + R.id.authFragment)
        }

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.startFragment,
            fragmentId,
            R.id.aboutGymFragment,
            R.id.rulesFragment,
        ).setOpenableLayout(drawerLayout).build()

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }

}

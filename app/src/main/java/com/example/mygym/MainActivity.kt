package com.example.mygym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        resetDrawer(R.id.authFragment, R.id.personalAreaFragment)

        userViewModel.user.observe(this, {
            currentUser = it
            resetDrawer(R.id.authFragment, R.id.personalAreaFragment)
        })

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun resetDrawer(authFragmentId: Int, personalAreaFragmentId: Int) {

        if (currentUser != null) {
            Toast.makeText(this, currentUser?.phoneNumber, Toast.LENGTH_LONG).show()
            binding.navigationView.menu.findItem(personalAreaFragmentId).isVisible = true
            binding.navigationView.menu.findItem(authFragmentId).isVisible = false
        } else {
            Toast.makeText(this, "Юзер не зареган", Toast.LENGTH_LONG).show()
            binding.navigationView.menu.findItem(personalAreaFragmentId).isVisible = false
            binding.navigationView.menu.findItem(authFragmentId).isVisible = true
        }

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.startFragment,
            authFragmentId,
            personalAreaFragmentId,
            R.id.teamFragment2,
            R.id.aboutGymFragment,
            R.id.rulesFragment,
        ).setOpenableLayout(drawerLayout).build()

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }

}

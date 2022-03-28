package com.example.mygym

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mygym.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.mygym.screen.rules.RulesFragment
import com.example.mygym.screen.aboutgym.AboutGymFragment
import com.example.mygym.screen.personalarea.AuthFragment
import com.example.mygym.screen.personalarea.PersonalAreaFragment
import com.example.mygym.screen.personalarea.UserViewModel
import com.example.mygym.screen.start.StartFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser
    private val userModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userModel.user.observe(this, {
            currentUser = it
            Toast.makeText(this, currentUser?.phoneNumber, Toast.LENGTH_LONG).show()
        })

        if (currentUser != null) {
            Toast.makeText(this, currentUser?.phoneNumber, Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Юзер не зареган", Toast.LENGTH_LONG).show()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.startFragment, R.id.personalAreaFragment, R.id.aboutGymFragment, R.id.rulesFragment,)
            .setOpenableLayout(drawerLayout).build()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        NavigationUI.setupWithNavController(binding.navigationView, navController)

    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}

package com.example.mygym

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
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.mygym.screen.rules.RulesFragment
import com.example.mygym.screen.aboutgym.AboutGymFragment
import com.example.mygym.screen.personalarea.AuthFragment
import com.example.mygym.screen.personalarea.PersonalAreaFragment
import com.example.mygym.screen.personalarea.UserViewModel
import com.example.mygym.screen.start.StartFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var nvDrawer: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
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

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        mDrawer = findViewById(R.id.drawerLayout)
        drawerToggle = setupDrawerToggle()
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        // Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle)

        nvDrawer = findViewById(R.id.nav_menu)
        // Setup drawer view
        setupDrawerContent(nvDrawer)

    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(
            this,
            mDrawer,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (drawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener {

            it.isChecked = true

            if (currentUser != null) {
                when (it.itemId){
                R.id.home_item -> replaceFragment(StartFragment(), it.title.toString())
                R.id.personal_area_item -> replaceFragment(PersonalAreaFragment(), it.title.toString())
                R.id.rules_item -> replaceFragment(RulesFragment(), it.title.toString())
                R.id.about_item -> replaceFragment(AboutGymFragment(), it.title.toString())
                else -> {replaceFragment(StartFragment(), it.title.toString())}
                }
            } else {
                when (it.itemId){
                    R.id.home_item -> replaceFragment(StartFragment(), it.title.toString())
                    R.id.personal_area_item -> replaceFragment(AuthFragment(), it.title.toString())
                    R.id.rules_item -> replaceFragment(RulesFragment(), it.title.toString())
                    R.id.about_item -> replaceFragment(AboutGymFragment(), it.title.toString())
                    else -> {replaceFragment(StartFragment(), it.title.toString())}
                }
            }

            true

        }
    }

    private fun replaceFragment(fragment: Fragment, title: String): Boolean {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_fragment, fragment)
        fragmentTransaction.commit()
        binding.drawerLayout.closeDrawers()
        setTitle(title)
        return true
    }

}

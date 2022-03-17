package com.example.mygym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
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
import com.example.mygym.screen.rules.RulesFragment
import com.example.mygym.screen.aboutgym.AboutGymFragment
import com.example.mygym.screen.personalarea.AuthFragment
import com.example.mygym.screen.personalarea.PersonalAreaFragment
import com.example.mygym.screen.start.StartFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var nvDrawer: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private val currentUser: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (currentUser != null) {
            Toast.makeText(this, currentUser.phoneNumber, Toast.LENGTH_LONG).show()
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

//            if (it.itemId == R.id.personal_area_item) {
//                if (currentUser != null) {
//                    replaceFragment(PersonalAreaFragment(), it.title.toString())
//                    Toast.makeText(this, "Пользователь зареган, идем в личный кабинет", Toast.LENGTH_LONG).show()
//
//                } else {
//                    replaceFragment(AuthFragment(), it.title.toString())
//                    Toast.makeText(this, "Пользователь не зареган, идем в регистрацию", Toast.LENGTH_LONG).show()
//                }
//            }
//
//            when(it.itemId){
//                R.id.home_item -> replaceFragment(StartFragment(), it.title.toString())
//                R.id.personal_area_item -> replaceFragment(PersonalAreaFragment(), it.title.toString())
//                R.id.rules_item -> replaceFragment(RulesFragment(), it.title.toString())
//                R.id.about_item -> replaceFragment(AboutGymFragment(), it.title.toString())
//                //R.id.personal_area_item -> replaceFragment(PersonalAreaFragment(), it.title.toString())
//                else -> {replaceFragment(StartFragment(), it.title.toString())}
//            }

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

//    private fun selectDrawerItem(menuItem: MenuItem) {
//        navigationView.setNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.home_item -> replaceFragment(StartFragment(), it.title.toString())
//                R.id.personal_area_item -> replaceFragment(AuthFragment(), it.title.toString())
//                R.id.rules_item -> replaceFragment(RulesFragment(), it.title.toString())
//                else -> {replaceFragment(StartFragment(), it.title.toString())}
//            }
//        }
//        try {
//            fragment = fragmentClass.newInstance() as Fragment
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//
//        // Insert the fragment by replacing any existing fragment
//        val fragmentManager: FragmentManager = supportFragmentManager
//        if (fragment != null) {
//            fragmentManager.beginTransaction()
//                .replace(R.id.nav_fragment, fragment)
//                .addToBackStack(null)
//                .commit()
//        }
//
//        // Highlight the selected item has been done by NavigationView
//        menuItem.isChecked = true
//        // Set action bar title
//        title = menuItem.title
//        // Close the navigation drawer
//        mDrawer.closeDrawers()
//    }

    private fun replaceFragment(fragment: Fragment, title: String): Boolean {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_fragment, fragment)
        fragmentTransaction.commit()
        binding.drawerLayout.closeDrawers()
        setTitle(title)
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ActivityLifecycle", "onDestroy")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ActivityLifecycle", "onResume")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ActivityLifecycle", "onStart")



    }

    override fun onStop() {
        super.onStop()
        Log.d("ActivityLifecycle", "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ActivityLifecycle", "onPause")
    }

}

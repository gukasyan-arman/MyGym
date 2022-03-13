package com.example.mygym

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import com.example.mygym.databinding.ActivityMainBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.mygym.screen.rules.RulesFragment
import com.example.mygym.screen.aboutgym.AboutGymFragment
import com.example.mygym.screen.start.StartFragment
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawer: DrawerLayout
    private lateinit var toolbar: Toolbar
    private lateinit var  nvDrawer: NavigationView
    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
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
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        var fragment: Fragment? = null
        val fragmentClass: Class<*> = when (menuItem.itemId) {
            R.id.home_item -> StartFragment::class.java
            R.id.about_item -> AboutGymFragment::class.java
            R.id.rules_item -> RulesFragment::class.java
            R.id.about_app_item -> RulesFragment::class.java
            else -> {StartFragment::class.java}
        }
        try {
            fragment = fragmentClass.newInstance() as Fragment
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Insert the fragment by replacing any existing fragment
        val fragmentManager: FragmentManager = supportFragmentManager
        if (fragment != null) {
            fragmentManager.beginTransaction()
                .replace(R.id.nav_fragment, fragment)
                .commit()
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.isChecked = true
        // Set action bar title
        title = menuItem.title
        // Close the navigation drawer
        mDrawer.closeDrawers()
    }

}
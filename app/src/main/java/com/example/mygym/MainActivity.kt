package com.example.mygym

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mygym.adapter.ViewPagerAdapter
import com.example.mygym.databinding.ActivityMainBinding
import com.example.mygym.databinding.FragmentStartBinding
import com.example.mygym.screen.start.PagerItem2Fragment
import com.example.mygym.screen.start.PagerItem3Fragment
import com.example.mygym.screen.start.PagerItemFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val viewPager: ViewPager2 = findViewById(R.id.startViewPager)
//
//        val fragments: ArrayList<Fragment> = arrayListOf(
//            PagerItemFragment(),
//            PagerItem2Fragment(),
//            PagerItem3Fragment()
//        )
//
//        val adapter = ViewPagerAdapter(fragments, this)
//        viewPager.adapter = adapter

    }
}
package com.example.mygym.screen.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.example.mygym.MainActivity
import com.example.mygym.R
import com.example.mygym.databinding.ActivitySplashBinding
import com.example.mygym.hideActionBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hideActionBar(this)
        val intent = Intent(this, MainActivity::class.java)

        val launch = CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            startActivity(intent)
            finish()
        }

    }
}
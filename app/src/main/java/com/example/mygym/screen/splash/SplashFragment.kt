package com.example.mygym.screen.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toolbar
import androidx.navigation.Navigation
import com.example.mygym.R
import com.example.mygym.databinding.FragmentSplashBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)

        CoroutineScope(Dispatchers.Main).launch {
            delay(1000)
            Navigation.findNavController(view!!).navigate(R.id.action_splashFragment_to_startFragment)
        }

        return binding.root
    }

}
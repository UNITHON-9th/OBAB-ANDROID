package com.uniton.obab.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.databinding.ActivityHomeBinding
import com.uniton.obab.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({ changeActivity() }, 2000)

    }

    fun changeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}

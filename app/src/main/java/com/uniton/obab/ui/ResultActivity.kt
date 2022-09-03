package com.uniton.obab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityHomeBinding
import com.uniton.obab.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
    }

    private fun initListener() = with(binding) {
        btnDone.setOnClickListener {
            finish()
        }
    }
}

package com.uniton.obab.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.uniton.obab.databinding.ActivitySampleBinding
import com.uniton.obab.network.sample.SampleService
import com.uniton.obab.network.sample.SampleCallback

class SampleActivity : AppCompatActivity(), SampleCallback {
    private lateinit var binding: ActivitySampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            Log.w("TAG", "click")
            SampleService(this).getRepositories("jihee.dev", "android-calculator-mvc")
        }
    }

    override fun onSuccess() {
        binding.textView.text = "Success"
    }

    override fun onFail() {
        binding.textView.text = "Fail"
    }
}

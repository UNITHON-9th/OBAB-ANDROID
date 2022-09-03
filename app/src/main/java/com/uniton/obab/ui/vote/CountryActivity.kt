package com.uniton.obab.ui.vote

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.databinding.ActivityCountryBinding

class CountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryBinding
    private lateinit var timer: CountDownTimer

    private val MAX_COUNT = 10
    private val MAX_COUNT_MILLIS = MAX_COUNT * 1000L
    private val COUNT_DOWN_INTERVAL = 1000L

    private var currentSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initTimer()
    }

    private fun initListener() = with(binding) {
        layoutKorean.setOnClickListener {
            layoutKorean.isSelected = true
            layoutWestern.isSelected = false
            layoutChinese.isSelected = false
            layoutJapanese.isSelected = false
            currentSelected = 1
        }

        layoutWestern.setOnClickListener {
            layoutKorean.isSelected = false
            layoutWestern.isSelected = true
            layoutChinese.isSelected = false
            layoutJapanese.isSelected = false
            currentSelected = 2
        }

        layoutChinese.setOnClickListener {
            layoutKorean.isSelected = false
            layoutWestern.isSelected = false
            layoutChinese.isSelected = true
            layoutJapanese.isSelected = false
            currentSelected = 3
        }

        layoutJapanese.setOnClickListener {
            layoutKorean.isSelected = false
            layoutWestern.isSelected = false
            layoutChinese.isSelected = false
            layoutJapanese.isSelected = true
            currentSelected = 4
        }

        btnRandom.setOnClickListener {
            chooseRandomOption()
        }
    }

    private fun initTimer() = with(binding) {
        timer = object : CountDownTimer(MAX_COUNT_MILLIS, COUNT_DOWN_INTERVAL) {
            override fun onTick(millsUntilFinished: Long) {
                val time = millsUntilFinished.toInt() / 1000
                timeText.text = "${time}초"
                timeProgress.progress = time * 10
            }

            override fun onFinish() {
                chooseRandomOption()
                Log.w("TAG", "Finished!")
            }

        }

        timer.start()
    }

    private fun chooseRandomOption() = with(binding) {
        layoutKorean.isSelected = false
        layoutWestern.isSelected = false
        layoutChinese.isSelected = false
        layoutJapanese.isSelected = false

        val range = (1..4)
        currentSelected = range.random()

        when (currentSelected) {
            1 -> layoutKorean.isSelected = true
            2 -> layoutWestern.isSelected = true
            3 -> layoutChinese.isSelected = true
            4 -> layoutJapanese.isSelected = true
        }
    }
}

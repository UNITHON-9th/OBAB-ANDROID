package com.uniton.obab.ui.vote

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityCountryBinding
import com.uniton.obab.ui.VoteCompleteActivity


class CountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountryBinding
    private lateinit var timer: CountDownTimer

    private val MAX_COUNT = 11
    private val MAX_COUNT_MILLIS = MAX_COUNT * 1000L
    private val COUNT_DOWN_INTERVAL = 1000L

    private var currentSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initTimer()
        initGif()
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

        btnDone.setOnClickListener {
//            chooseRandomOption()
            changeActivity()
        }
    }

    private fun changeActivity() {
        val isCaptain = intent.getBooleanExtra("isCaptain", false)
        val intent = Intent(this, VoteCompleteActivity::class.java)
        intent.putExtra("isCaptain",isCaptain)
        startActivity(intent)
        finish()
    }

    private fun initTimer() = with(binding) {
        timer = object : CountDownTimer(MAX_COUNT_MILLIS, COUNT_DOWN_INTERVAL) {
            override fun onTick(millsUntilFinished: Long) {
                val time = millsUntilFinished.toInt() / 1000
            }

            override fun onFinish() {
                chooseRandomOption()
                Log.w("TAG", "Finished!")
                changeActivity()
            }

        }

        timer.start()
    }

    private fun initGif() {
        Glide.with(this).load(R.drawable.count).override(560, 560).into(binding.ivProgress)
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

    override fun onBackPressed() {
        showExitDialog()
    }

    private fun showExitDialog() {
        val exitCloseDialog = Dialog(this)
        exitCloseDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        exitCloseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        exitCloseDialog.setContentView(R.layout.dialog_exit)

        exitCloseDialog.show()

        exitCloseDialog.findViewById<AppCompatTextView>(R.id.dialog_exit_tv_button_exit)
            .setOnClickListener {
                exitCloseDialog.dismiss()
                finish()
            }

        exitCloseDialog.findViewById<AppCompatTextView>(R.id.dialog_exit_tv_button_continue)
            .setOnClickListener {
                exitCloseDialog.dismiss()
            }
    }

}

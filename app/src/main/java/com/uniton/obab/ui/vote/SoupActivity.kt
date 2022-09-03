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
import com.uniton.obab.databinding.ActivitySoupBinding
import com.uniton.obab.model.VoteInformation
import com.uniton.obab.ui.VoteCompleteActivity

class SoupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySoupBinding
    private lateinit var timer: CountDownTimer

    private val MAX_COUNT = 11
    private val MAX_COUNT_MILLIS = MAX_COUNT * 1000L
    private val COUNT_DOWN_INTERVAL = 1000L

    private var currentSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initTimer()
        initGif()
    }

    private fun initListener() = with(binding) {
        layoutSoup.setOnClickListener {
            layoutSoup.isSelected = true
            layoutNotSoup.isSelected = false
            currentSelected = true
            changeActivity()
        }

        layoutNotSoup.setOnClickListener {
            layoutSoup.isSelected = false
            layoutNotSoup.isSelected = true
            currentSelected = false
            changeActivity()
        }

        btnDone.setOnClickListener {
            changeActivity()
        }
    }

    private fun changeActivity() {
        val voteInformation = intent.getParcelableExtra<VoteInformation>("voteInfo")
        val newInformation = voteInformation?.copy(isSoup = currentSelected)
        val intent = Intent(this, HotActivity::class.java)
        intent.putExtra("voteInfo", newInformation)
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
    }

    private fun initGif() {
        Glide.with(this).load(R.drawable.count).override(560, 560).into(binding.ivProgress)
    }

    private fun chooseRandomOption() = with(binding) {
        layoutSoup.isSelected = false
        layoutNotSoup.isSelected = false

        val range = (0..1)
        currentSelected = range.random() == 0

        when (currentSelected) {
            true -> layoutSoup.isSelected = true
            false -> layoutNotSoup.isSelected = true
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

    override fun onResume() {
        super.onResume()
        timer.start()
    }

    override fun onStop() {
        timer.cancel()
        super.onStop()
    }
}

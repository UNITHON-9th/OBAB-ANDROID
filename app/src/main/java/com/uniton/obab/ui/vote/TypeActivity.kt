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
import com.uniton.obab.databinding.ActivityTypeBinding
import com.uniton.obab.model.VoteInformation
import com.uniton.obab.ui.VoteCompleteActivity

class TypeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypeBinding
    private lateinit var timer: CountDownTimer

    private val MAX_COUNT = 11
    private val MAX_COUNT_MILLIS = MAX_COUNT * 1000L
    private val COUNT_DOWN_INTERVAL = 1000L

    private var currentSelected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initTimer()
        initGif()
    }

    private fun initListener() = with(binding) {
        layoutRice.setOnClickListener {
            layoutRice.isSelected = true
            layoutNoodle.isSelected = false
            layoutRiceCake.isSelected = false
            layoutBread.isSelected = false
            currentSelected = 0
            changeActivity()
        }

        layoutRice.setOnClickListener {
            layoutRice.isSelected = false
            layoutNoodle.isSelected = true
            layoutRiceCake.isSelected = false
            layoutBread.isSelected = false
            currentSelected = 1
            changeActivity()
        }

        layoutRice.setOnClickListener {
            layoutRice.isSelected = false
            layoutNoodle.isSelected = false
            layoutRiceCake.isSelected = true
            layoutBread.isSelected = false
            currentSelected = 2
            changeActivity()
        }

        layoutRice.setOnClickListener {
            layoutRice.isSelected = false
            layoutNoodle.isSelected = false
            layoutRiceCake.isSelected = false
            layoutBread.isSelected = true
            currentSelected = 3
            changeActivity()
        }

        btnDone.setOnClickListener {
            changeActivity()
        }
    }

    private fun changeActivity() {
        val voteInformation = intent.getParcelableExtra<VoteInformation>("voteInfo")
        val newInformation = voteInformation?.copy(typeId = currentSelected)
        val intent = Intent(this, SpicyActivity::class.java)
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
        layoutRice.isSelected = false
        layoutNoodle.isSelected = false
        layoutRiceCake.isSelected = false
        layoutBread.isSelected = false

        val range = (0..3)
        currentSelected = range.random()

        when (currentSelected) {
            0 -> layoutRice.isSelected = true
            1 -> layoutNoodle.isSelected = true
            2 -> layoutRiceCake.isSelected = true
            3 -> layoutBread.isSelected = true
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

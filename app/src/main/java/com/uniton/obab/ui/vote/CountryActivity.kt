package com.uniton.obab.ui.vote

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Window
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityCountryBinding
import com.uniton.obab.model.VoteInformation
import com.uniton.obab.ui.VoteCompleteActivity
import javax.sql.DataSource


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
            currentSelected = 0
            changeActivity()
        }

        layoutWestern.setOnClickListener {
            layoutKorean.isSelected = false
            layoutWestern.isSelected = true
            layoutChinese.isSelected = false
            layoutJapanese.isSelected = false
            currentSelected = 1
            changeActivity()
        }

        layoutChinese.setOnClickListener {
            layoutKorean.isSelected = false
            layoutWestern.isSelected = false
            layoutChinese.isSelected = true
            layoutJapanese.isSelected = false
            currentSelected = 2
            changeActivity()
        }

        layoutJapanese.setOnClickListener {
            layoutKorean.isSelected = false
            layoutWestern.isSelected = false
            layoutChinese.isSelected = false
            layoutJapanese.isSelected = true
            currentSelected = 3
            changeActivity()
        }

        btnDone.setOnClickListener {
//            chooseRandomOption()
            changeActivity()
        }
    }

    private fun changeActivity() {
        val isCaptain = intent.getBooleanExtra("isCaptain", false)
        val roomNo = intent.getStringExtra("roomNo")

        val intent = Intent(this, TypeActivity::class.java)
        val voteInformation = VoteInformation(isReader = isCaptain, roomNo = roomNo ?: "", countryId = currentSelected)
        intent.putExtra("voteInfo", voteInformation)
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
        val listener: RequestListener<GifDrawable> by lazy {
            object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?, model: Any,
                    target: com.bumptech.glide.request.target.Target<GifDrawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<GifDrawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(1)
                    return false
                }
            }
        }

        Glide.with(this).asGif()
            .load(R.drawable.count)
            .diskCacheStrategy(DiskCacheStrategy.NONE)// ????????? ?????? ?????? off
            .skipMemoryCache(true)
            .listener(listener).into(binding.ivProgress)
    }

    private fun chooseRandomOption() = with(binding) {
        layoutKorean.isSelected = false
        layoutWestern.isSelected = false
        layoutChinese.isSelected = false
        layoutJapanese.isSelected = false

        val range = (0..3)
        currentSelected = range.random()

        when (currentSelected) {
            0 -> layoutKorean.isSelected = true
            1 -> layoutWestern.isSelected = true
            2 -> layoutChinese.isSelected = true
            3 -> layoutJapanese.isSelected = true
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

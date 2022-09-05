package com.uniton.obab.ui.vote

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityHotBinding
import com.uniton.obab.model.CreateRoomRepository
import com.uniton.obab.model.CreateRoomRequest
import com.uniton.obab.model.VoteInformation
import com.uniton.obab.model.request.UserChoiceRequest
import com.uniton.obab.network.Api
import com.uniton.obab.network.room.create.CreateRoomService
import com.uniton.obab.network.sendChoice.SendUserChoiceCallback
import com.uniton.obab.network.sendChoice.SendUserChoiceService
import com.uniton.obab.ui.VoteCompleteActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HotActivity : AppCompatActivity(), SendUserChoiceCallback {
    private lateinit var binding: ActivityHotBinding
    private lateinit var timer: CountDownTimer
    private var information: VoteInformation? = null

    private val MAX_COUNT = 11
    private val MAX_COUNT_MILLIS = MAX_COUNT * 1000L
    private val COUNT_DOWN_INTERVAL = 1000L

    private var currentSelected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        initTimer()
        initGif()
    }

    private fun initListener() = with(binding) {
        layoutHot.setOnClickListener {
            layoutHot.isSelected = true
            layoutCold.isSelected = false
            currentSelected = true
            this@HotActivity.sendUserChoice()
        }

        layoutCold.setOnClickListener {
            layoutHot.isSelected = false
            layoutCold.isSelected = true
            currentSelected = false
            this@HotActivity.sendUserChoice()
        }

        btnDone.setOnClickListener {
            this@HotActivity.sendUserChoice()
        }
    }

    private fun sendUserChoice() {
        val voteInformation = intent.getParcelableExtra<VoteInformation>("voteInfo")
        information = voteInformation?.copy(isHot = currentSelected)

        val deviceId = applicationContext.getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        ).getString("FCM_TOKEN", "")

        information?.apply {
            if (deviceId != null) {
                Log.w("TAG", "a")
                val request = UserChoiceRequest(
                    deviceId = deviceId,
                    roomNo = roomNo,
                    country = countryId,
                    food = typeId,
                    isSpicy = isSpicy,
                    isSoup = isSoup,
                    isHot = isHot
                )

                SendUserChoiceService(this@HotActivity).sendUserChoice(request)
            } else {
                Log.w("TAG", "b")
                onFail(errMsg = "디바이스 아이디를 가져오지 못했습니다")
            }
        } ?: kotlin.run {
            Log.w("TAG", "c")
            onFail(errMsg = "유저 선택 정보를 가져오지 못했습니다")
        }
    }

    private fun initTimer() = with(binding) {
        timer = object : CountDownTimer(MAX_COUNT_MILLIS, COUNT_DOWN_INTERVAL) {
            override fun onTick(millsUntilFinished: Long) {
                val time = millsUntilFinished.toInt() / 1000
            }

            override fun onFinish() {
                chooseRandomOption()
                Log.w("TAG", "Finished!")
                this@HotActivity.sendUserChoice()
            }

        }
    }

    private fun initGif() {
        Glide.with(this).load(R.drawable.count).override(560, 560).into(binding.ivProgress)
    }

    private fun chooseRandomOption() = with(binding) {
        layoutHot.isSelected = false
        layoutCold.isSelected = false

        val range = (0..1)
        currentSelected = range.random() == 0

        when (currentSelected) {
            true -> layoutHot.isSelected = true
            false -> layoutCold.isSelected = true
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

    override fun onSuccess() {
        Log.w("TAG", "d")
        val intent = Intent(this, VoteCompleteActivity::class.java)
        intent.putExtra("voteInfo", information)
        startActivity(intent)
        finish()
    }

    override fun onFail(errMsg: String?) {
        Log.w("TAG", "e")
        Toast.makeText(this, "다시 시도해 주세요 $errMsg", Toast.LENGTH_SHORT).show()
    }
}

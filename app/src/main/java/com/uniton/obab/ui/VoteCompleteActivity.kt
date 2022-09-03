package com.uniton.obab.ui

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityVoteCompleteBinding
import com.uniton.obab.databinding.DialogCodeBinding
import com.uniton.obab.databinding.DialogEarlyCloseBinding
import com.uniton.obab.model.PersonalResultRepository
import com.uniton.obab.network.survey.PersonalResultCallback
import com.uniton.obab.network.survey.PersonalResultService

class VoteCompleteActivity : AppCompatActivity(), PersonalResultCallback {
    private lateinit var binding: ActivityVoteCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val isCaptain = intent.getBooleanExtra("isCaptain", false)

        if (isCaptain) {
            binding.voteCompleteLayoutButtonCaptain.isVisible = true
            binding.voteCompleteLayoutButtonNormal.isVisible = false
        } else {
            binding.voteCompleteLayoutButtonCaptain.isVisible = false
            binding.voteCompleteLayoutButtonNormal.isVisible = true

        }

        binding.voteCompleteTvBtnEarlyCloseCaptain.setOnClickListener {
            showEarlyCloeDialog()
        }

        binding.voteCompleteTvBtnCodeCaptain.setOnClickListener {
            showCodeDialog()
        }

        binding.voteCompleteLayoutButtonNormal.setOnClickListener {
            changeHomeActivity()
        }

        val deviceId = applicationContext.getSharedPreferences(
            getString(R.string.app_name),
            Context.MODE_PRIVATE
        ).getString("FCM_TOKEN", "")

        val voteInfo =intent.getParcelableExtra<VoteInformation>("voteInfo")

        if (deviceId != null && voteInfo != null) {
            tryGetPersonalResult(deviceId = deviceId, roomNo = voteInfo.roomNo)
        }

    }

    private fun changeHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
    }

    private fun changeResultActivity() {
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showCodeDialog() {
        val codeDialog = Dialog(this)
        codeDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        codeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        codeDialog.setContentView(R.layout.dialog_code)

        codeDialog.show()
        val code =
            codeDialog.findViewById<AppCompatTextView>(R.id.dialog_code_complete_tv_code).text
        codeDialog.findViewById<ConstraintLayout>(R.id.dialog_code_complete_layout_code)
            .setOnClickListener {
                Toast.makeText(this, "초대코드가 클립보드에 복사되었습니다", Toast.LENGTH_SHORT).show()
                val clip = ClipData.newPlainText(getString(R.string.app_name), code)
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(clip)
            }
    }

    private fun showEarlyCloeDialog() {
        val earlyCloseDialog = Dialog(this)
        earlyCloseDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        earlyCloseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        earlyCloseDialog.setContentView(R.layout.dialog_early_close)

        earlyCloseDialog.show()

        earlyCloseDialog.findViewById<AppCompatTextView>(R.id.dialog_early_close_tv_button_cancel)
            .setOnClickListener {
                earlyCloseDialog.dismiss()
            }

        earlyCloseDialog.findViewById<AppCompatTextView>(R.id.dialog_early_close_tv_button_early_close)
            .setOnClickListener {
                changeResultActivity()
            }
    }

    private fun tryGetPersonalResult(deviceId: String, roomNo: String) {
        PersonalResultService(this).getPersonalResult(deviceId = deviceId, roomNo = roomNo)
    }

    override fun onSuccess(result: PersonalResultRepository) {
        val data = result.data
        when (data.country) {
            0 -> {
                binding.voteCompleteTvCountry.text = "한식"
            }
            1 -> {
                binding.voteCompleteTvCountry.text = "양식"
            }
            2 -> {
                binding.voteCompleteTvCountry.text = "중식"
            }
            3 -> {
                binding.voteCompleteTvCountry.text = "일식"
            }
        }
        when (data.food) {
            0 -> {
                binding.voteCompleteTvFood.text = "밥"
            }
            1 -> {
                binding.voteCompleteTvFood.text = "면"
            }
            2 -> {
                binding.voteCompleteTvFood.text = "떡"
            }
            3 -> {
                binding.voteCompleteTvFood.text = "빵"
            }
        }

        if (data.isSpicy) binding.voteCompleteTvSpicy.text = "매운 음식"
        else binding.voteCompleteTvSpicy.text = "안 매운 음식"

        if (data.isSoup) binding.voteCompleteTvSpicy.text = "국물 음식"
        else binding.voteCompleteTvSpicy.text = "국물이 없는 음식"

        if (data.isHot) binding.voteCompleteTvHot.text ="뜨거운 음식"
        else binding.voteCompleteTvHot.text = "차가운 음식"

        binding.voteCompleteTvCompleteNumber.text = data.submitCount.toString()
        binding.voteCompleteTvMaxNumber.text = data.totalCount.toString()
    }

    override fun onFailure(result: String) {
        TODO("Not yet implemented")
        showToast("수리중...")
    }

}

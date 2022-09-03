package com.uniton.obab.ui

import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
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

class VoteCompleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoteCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var isCaptain = intent.getBooleanExtra("isCaptain", false)

        binding.voteCompleteLayoutButtonCaptain.isVisible = isCaptain
        binding.voteCompleteLayoutButtonNormal.isVisible = !isCaptain


        binding.voteCompleteTvBtnEarlyCloseCaptain.setOnClickListener {
            showEarlyCloeDialog()
        }

        binding.voteCompleteTvBtnCodeCaptain.setOnClickListener {
            showCodeDialog()
        }

        binding.voteCompleteLayoutButtonNormal.setOnClickListener {
            // 방장 - 알겠어요!
        }

    }

    private fun changeActivity() {
        val intent = Intent(this, null)
        startActivity(intent)
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
        val code = codeDialog.findViewById<AppCompatTextView>(R.id.dialog_code_complete_tv_code).text
        codeDialog.findViewById<ConstraintLayout>(R.id.dialog_code_complete_layout_code).setOnClickListener {
            Toast.makeText(this, "초대코드가 클립보드에 복사되었습니다", Toast.LENGTH_SHORT).show()
            val clip = ClipData.newPlainText(getString(R.string.app_name), code)
            val clipboard =  getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(clip)
        }
    }

    private fun showEarlyCloeDialog() {
        val earlyCloseDialog = Dialog(this)
        earlyCloseDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        earlyCloseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        earlyCloseDialog.setContentView(R.layout.dialog_early_close)

        earlyCloseDialog.show()

        earlyCloseDialog.findViewById<AppCompatTextView>(R.id.dialog_early_close_tv_button_cancel).setOnClickListener {
            earlyCloseDialog.dismiss()
        }

        earlyCloseDialog.findViewById<AppCompatTextView>(R.id.dialog_early_close_tv_button_early_close).setOnClickListener{
            // 조기종료 버튼 클릭
        }
    }

}
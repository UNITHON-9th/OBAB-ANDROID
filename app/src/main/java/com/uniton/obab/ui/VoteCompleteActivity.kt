package com.uniton.obab.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.uniton.obab.databinding.ActivityVoteCompleteBinding

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
            // 방장 - 조기 종료
        }

        binding.voteCompleteTvBtnCodeCaptain.setOnClickListener {
            // 방장 - 참여 코드 보기
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

}

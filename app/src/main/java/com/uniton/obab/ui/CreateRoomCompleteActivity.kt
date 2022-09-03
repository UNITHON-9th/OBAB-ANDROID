package com.uniton.obab.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityCreateRoomBinding
import com.uniton.obab.databinding.ActivityCreateRoomCompleteBinding
import java.util.regex.Pattern

class CreateRoomCompleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRoomCompleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRoomCompleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createRoomCompleteLayoutCode.setOnClickListener {
            showToast("초대코드가 클립보드에 복사되었습니다")
            val clip = ClipData.newPlainText(getString(R.string.app_name), binding.createRoomCompleteTvCode.text)
            val clipboard =  getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
            clipboard.setPrimaryClip(clip)
        }

        binding.createRoomCompleteIvBack.setOnClickListener {
            finish()
        }

        binding.createRoomCompleteLayoutButtonStart.setOnClickListener {
            changeActivity()
        }

    }

    private fun changeActivity() {
        val intent = Intent(this, VoteCompleteActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

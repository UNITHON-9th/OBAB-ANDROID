package com.uniton.obab.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityCreateRoomBinding
import java.util.regex.Pattern

class CreateRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createRoomEtCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkActiveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.createRoomIvBack.setOnClickListener {
            finish()
        }

    }

    private fun changeCompleteActivity() {
        val intent = Intent(this, CreateRoomCompleteActivity::class.java)
        startActivity(intent)
    }

    private fun checkActiveButton() {
        if (binding.createRoomEtCode.text.toString().isEmpty()) {
            return
        }

        val participatesNumber = binding.createRoomEtCode.text.toString()
        val button = binding.createRoomLayoutButtonCreate
        if (!Pattern.matches("^\\d*\$", participatesNumber)) {
            button.setBackgroundColor(applicationContext.getColor(R.color.main_red_opacity))
            button.isClickable = false
            showToast("숫자만 입력 가능합니다")
            return
        }

        if (participatesNumber.isNotEmpty()) {
            button.setBackgroundColor(applicationContext.getColor(R.color.main_red))
            button.isClickable = true
            button.setOnClickListener {
                if (participatesNumber.toInt() > 100) {
                    showToast("100명 이하까지 가능합니다")
                    return@setOnClickListener
                }
                changeCompleteActivity()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

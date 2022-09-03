package com.uniton.obab.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityEnterRoomBinding
import java.util.regex.Pattern

class EnterRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnterRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterRoomEtCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                checkActiveButton()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.enterRoomIvBack.setOnClickListener {
            finish()
        }

    }

    private fun changeActivity() {
        val intent = Intent(this, null)
        startActivity(intent)
    }

    private fun checkActiveButton() {
        val code = binding.enterRoomEtCode.text.toString()
        if (code.length == 6  && (Pattern.matches("^\\d*\$", code))) {
            binding.enterRoomLayoutButtonEnter.setBackgroundColor(applicationContext.getColor(R.color.main_red))
            binding.enterRoomLayoutButtonEnter.isClickable = true
            binding.enterRoomLayoutButtonEnter.setOnClickListener {
                showToast("Click")
            }
        } else {
            binding.enterRoomLayoutButtonEnter.setBackgroundColor(applicationContext.getColor(R.color.main_red_opacity))
            binding.enterRoomLayoutButtonEnter.isClickable = false
        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}

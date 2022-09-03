package com.uniton.obab.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityEnterRoomBinding
import com.uniton.obab.model.CreateRoomRequest
import com.uniton.obab.model.EnterRoomRepository
import com.uniton.obab.model.EnterRoomRequest
import com.uniton.obab.network.room.create.CreateRoomService
import com.uniton.obab.network.room.enter.EnterRoomCallback
import com.uniton.obab.network.room.enter.EnterRoomService
import com.uniton.obab.ui.vote.CountryActivity
import java.util.regex.Pattern

class EnterRoomActivity : AppCompatActivity(), EnterRoomCallback {
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

    private fun changeCountryActivity(roomNo: String) {
        val intent = Intent(this, CountryActivity::class.java)
        intent.putExtra("roomNo", roomNo)
        startActivity(intent)
        finish()
    }

    private fun checkActiveButton() {
        val code = binding.enterRoomEtCode.text.toString()
        if (code.length == 6 && (Pattern.matches("^\\d*\$", code))) {
            binding.enterRoomLayoutButtonEnter.setBackgroundColor(applicationContext.getColor(R.color.main_red))
            binding.enterRoomLayoutButtonEnter.isClickable = true
            binding.enterRoomLayoutButtonEnter.setOnClickListener {
                tryPostEnterRoom(code)
            }
        } else {
            binding.enterRoomLayoutButtonEnter.setBackgroundColor(applicationContext.getColor(R.color.main_red_opacity))
            binding.enterRoomLayoutButtonEnter.isClickable = false
        }

    }

    private fun tryPostEnterRoom(inviteCode: String) {
        val enterRoomRequest =
            applicationContext.getSharedPreferences(
                getString(R.string.app_name),
                Context.MODE_PRIVATE
            ).getString("FCM_TOKEN", "")
                ?.let { EnterRoomRequest(deviceId = it, inviteCode = inviteCode) }
        if (enterRoomRequest != null) {
            EnterRoomService(this).postEnterRoom(enterRoomRequest)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(result: EnterRoomRepository) {
        if (result.responseCode == "CM00") {
            changeCountryActivity(result.data.roomNo)
        }
    }

    override fun onFailure(result: EnterRoomRepository) {
        TODO("Not yet implemented")
        showToast("수리중 입니다...")
    }

}

package com.uniton.obab.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val finishTime: Long = 1000
    private var pressTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeLayoutCreate.setOnClickListener {
            changeCreateRoomActivity()
        }

        binding.homeLayoutEnter.setOnClickListener {
            changeEnterRoomActivity()
        }

    }

    private fun changeEnterRoomActivity() {
        val intent = Intent(this, EnterRoomActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        startActivity(intent)
    }

    private fun changeCreateRoomActivity() {
        val intent = Intent(this, CreateRoomActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        val tempTime = System.currentTimeMillis()
        val intervalTime: Long = tempTime - pressTime

        if (intervalTime in 0..finishTime) {
            finish()
        } else {
            pressTime = tempTime
            Toast.makeText(applicationContext, "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show()
        }
    }

}

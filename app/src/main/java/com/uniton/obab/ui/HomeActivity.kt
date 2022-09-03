package com.uniton.obab.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
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

}

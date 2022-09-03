package com.uniton.obab.ui

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


        binding.createRoomCompleteIvBack.setOnClickListener {
            finish()
        }

        binding.createRoomCompleteLayoutButtonStart.setOnClickListener {
            showToast("CLICK")
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

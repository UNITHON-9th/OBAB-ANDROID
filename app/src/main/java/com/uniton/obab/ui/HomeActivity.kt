package com.uniton.obab.ui

import SpeedyLinearLayoutManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityHomeBinding
import com.uniton.obab.ui.adaper.CarouselAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val sampleList = listOf(
        R.drawable.ic_bread,
        R.drawable.ic_america,
        R.drawable.ic_spicy
    )

    private val sampleList2 = listOf(
        R.drawable.ic_cold,
        R.drawable.ic_noodle,
        R.drawable.ic_china
    )

    private val sampleList3 = listOf(
        R.drawable.ic_hot,
        R.drawable.ic_japan,
        R.drawable.ic_korea,
    )

    private val carouselAdapter1 = CarouselAdapter(this, sampleList)
    private val carouselAdapter2 = CarouselAdapter(this, sampleList2)
    private val carouselAdapter3 = CarouselAdapter(this, sampleList3)

    private val finishTime: Long = 1000
    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListener()
        getRoomNum()
    }

    private fun getRoomNum() {
        intent?.getStringExtra("roomNo")?.let { roomNum ->
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("roomNo", roomNum)
            startActivity(intent)
        }
        Log.w("TAG", "${intent?.getStringExtra("roomNo")}" )
    }

    private fun initRecyclerView() = with(binding) {
        autoRvFirst.adapter = carouselAdapter1
        autoRvFirst.layoutManager = SpeedyLinearLayoutManager(
            this@HomeActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        autoRvFirst.smoothScrollToPosition(Int.MAX_VALUE)

        autoRvSecond.adapter = carouselAdapter2
        autoRvSecond.layoutManager = SpeedyLinearLayoutManager(
            this@HomeActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        autoRvSecond.smoothScrollToPosition(Int.MAX_VALUE)


        autoRvThird.adapter = carouselAdapter3
        autoRvThird.layoutManager = SpeedyLinearLayoutManager(
            this@HomeActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        autoRvThird.smoothScrollToPosition(Int.MAX_VALUE)

        // TODO: ?????? ????????? ??? ??????????????? ?????????
    }

    private fun initListener() = with(binding) {
        btnEnter.setOnClickListener { intentEnterRoomActivity() }
        btnCreate.setOnClickListener { intentCreateRoomActivity() }
    }

    private fun intentEnterRoomActivity() {
        val intent = Intent(this, EnterRoomActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        startActivity(intent)
    }

    private fun intentCreateRoomActivity() {
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
            Toast.makeText(applicationContext, "????????? ???????????? ?????? ???????????????", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.autoRvFirst.smoothScrollToPosition(Int.MAX_VALUE)
        binding.autoRvSecond.smoothScrollToPosition(Int.MAX_VALUE)
        binding.autoRvThird.smoothScrollToPosition(Int.MAX_VALUE)
    }

    override fun onStop() {
        binding.autoRvFirst.stopScroll()
        binding.autoRvSecond.stopScroll()
        binding.autoRvThird.stopScroll()
        super.onStop()
    }
}

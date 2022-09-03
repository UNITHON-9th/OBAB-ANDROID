package com.uniton.obab.ui

import SpeedyLinearLayoutManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.uniton.obab.R
import com.uniton.obab.databinding.ActivityHomeBinding
import com.uniton.obab.ui.adaper.CarouselAdapter

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val sampleList = listOf(
        R.drawable.ic_food_sample,
        R.drawable.ic_logo,
        R.drawable.ic_vote_complete
    )

    private val sampleList2 = listOf(
        R.drawable.ic_vote_complete,
        R.drawable.ic_food_sample,
        R.drawable.ic_logo
    )

    private val sampleList3 = listOf(
        R.drawable.ic_logo,
        R.drawable.ic_vote_complete,
        R.drawable.ic_food_sample,
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

        // TODO: 화면 꺼졌을 때 애니메이션 멈추기
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
            Toast.makeText(applicationContext, "한번더 누르시면 앱이 종료됩니다", Toast.LENGTH_SHORT).show()
        }
    }
}

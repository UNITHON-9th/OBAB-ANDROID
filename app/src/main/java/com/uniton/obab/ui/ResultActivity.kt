package com.uniton.obab.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uniton.obab.databinding.ActivityResultBinding
import com.uniton.obab.model.response.ResultResponse
import com.uniton.obab.network.result.ResultCallback
import com.uniton.obab.network.result.ResultService
import com.uniton.obab.network.survey.PersonalResultService

class ResultActivity : AppCompatActivity(), ResultCallback {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiveIntent()
        initListener()
    }

    private fun receiveIntent() {
        val roomNo = intent?.getStringExtra("roomNo")

        roomNo?.let {
            ResultService(this).getResults(it)
        }
    }

    private fun initListener() = with(binding) {
        btnDone.setOnClickListener {
            finish()
        }
    }

    override fun onSuccess(result: ResultResponse): Unit = with(binding) {
        Log.w("TAG", result.toString())

        val korean = result.countries[0] / result.countries.sum() * 100
        val western = result.countries[1] / result.countries.sum() * 100
        val chinese = result.countries[2] / result.countries.sum() * 100
        val japanese = result.countries[3] / result.countries.sum() * 100

        tvCountryFirst.text = korean.toString() + "%"
        tvCountrySecond.text = western.toString() + "%"
        tvCountryThird.text = chinese.toString() + "%"
        tvCountryForth.text = japanese.toString() + "%"

        tvCountryFirstTitle.text = "한식"
        tvCountrySecondTitle.text = "양식"
        tvCountryThirdTitle.text = "중식"
        tvCountryForthTitle.text = "일식"

        graphCountryFirst.layoutParams = LinearLayout.LayoutParams(0, 16, korean.toFloat())
        graphCountrySecond.layoutParams = LinearLayout.LayoutParams(0, 16, western.toFloat())
        graphCountryThird.layoutParams = LinearLayout.LayoutParams(0, 16, chinese.toFloat())
        graphCountryForth.layoutParams = LinearLayout.LayoutParams(0, 16, japanese.toFloat())


        val spicy = result.spicys[0] / result.spicys.sum() * 100
        val notSpicy = result.spicys[1] / result.spicys.sum() * 100
        tvSpicy.text = "매운 거 " + spicy + "%"
        tvNotSpicy.text = "안 매운 거 " + notSpicy + "%"

        graphSpicy.layoutParams = LinearLayout.LayoutParams(0, 16, spicy.toFloat())
        graphNotSpicy.layoutParams = LinearLayout.LayoutParams(0, 16, notSpicy.toFloat())

        val soup = result.soups[0] / result.soups.sum() * 100
        val notSoup = result.soups[1] / result.soups.sum() * 100
        tvSpicy.text = "매운 거 " + soup + "%"
        tvNotSpicy.text = "안 매운 거 " + notSoup + "%"

        graphSoup.layoutParams = LinearLayout.LayoutParams(0, 16, soup.toFloat())
        graphNotSoup.layoutParams = LinearLayout.LayoutParams(0, 16, notSoup.toFloat())

        val hot = result.hots[0] / result.hots.sum() * 100
        val cold = result.hots[1] / result.hots.sum() * 100
        tvHot.text = "뜨거운 음식 " + hot + "%"
        tvCold.text = "차가운 음식 " + cold + "%"

        graphHot.layoutParams = LinearLayout.LayoutParams(0, 16, hot.toFloat())
        graphCold.layoutParams = LinearLayout.LayoutParams(0, 16, cold.toFloat())
    }

    override fun onFailure(error: String) {
        Toast.makeText(this, "$error", Toast.LENGTH_SHORT).show()
    }
}

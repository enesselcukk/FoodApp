package com.eneselcuk.foodapp.ui.splash

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.navigation.fragment.findNavController
import com.eneselcuk.foodapp.R
import android.content.Intent
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import com.eneselcuk.foodapp.MainActivity


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            R.layout.activity_splash)

        splash()
        fullScreen()
    }

    private fun splash() {
        countDownTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val myIntent = Intent(this@SplashActivity, MainActivity::class.java)
                finish()
                startActivity(myIntent)
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }


    private fun fullScreen() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }
}
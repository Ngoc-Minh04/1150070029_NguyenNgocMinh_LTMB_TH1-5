package com.example.lab1_basicapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnOpenDice: Button
    private lateinit var btnOpenCallSms: Button
    private lateinit var btnOpenLoading: Button
    private lateinit var btnOpenSplash: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOpenDice = findViewById(R.id.btnOpenDice)
        btnOpenCallSms = findViewById(R.id.btnOpenCallSms)
        btnOpenLoading = findViewById(R.id.btnOpenLoading)
        btnOpenSplash = findViewById(R.id.btnOpenSplash)

        //  Bài 1 - Tung xúc xắc
        btnOpenDice.setOnClickListener {
            startActivity(Intent(this, DiceActivity::class.java))
        }

        //  Bài 2 - Gọi điện và nhắn tin
        btnOpenCallSms.setOnClickListener {
            startActivity(Intent(this, CallSmsActivity::class.java))
        }

        //  Bài 3 - Loading UI
        btnOpenLoading.setOnClickListener {
            startActivity(Intent(this, LoadingActivity::class.java))
        }

        //  Mở màn hình Splash
        btnOpenSplash.setOnClickListener {
            startActivity(Intent(this, SplashActivity::class.java))
        }
    }
}

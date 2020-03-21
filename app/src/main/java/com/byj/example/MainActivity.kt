package com.byj.example

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // getStringExtra를 활용하여 값을 가져온뒤 TextView 에다가 셋팅
        val mainTextview = findViewById<TextView>(R.id.TextView)
        mainTextview.text = "${intent.getStringExtra("signInId")}님 환영합니다"
    }

}
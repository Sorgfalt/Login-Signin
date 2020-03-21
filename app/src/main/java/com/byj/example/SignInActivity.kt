package com.byj.example

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sign


class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val signInId = findViewById<EditText>(R.id.et_id)
        val signInPass = findViewById<EditText>(R.id.et_pass)
        val signInName = findViewById<EditText>(R.id.et_name)
        val signJoinBtn = findViewById<Button>(R.id.btn_joinIn)

        signJoinBtn.setOnClickListener {
            if (signInId.text.toString() == "" && signInPass.text.toString() == "" && signInName.text.toString() == "") {
                // 빈값이 있으면 실패
                return@setOnClickListener
            }
            // 빈값 무결성 통과 플로우 처리
            val sharedPreferences = getSharedPreferences("StudyPreference", Context.MODE_PRIVATE)
            val eidtor = sharedPreferences.edit()
            eidtor.putString("signInId", signInId.text.toString())
            eidtor.putString("signInPass", signInPass.text.toString())
            eidtor.putString("signInName", signInName.text.toString())
            eidtor.apply()
            finish()


        }


    }
}
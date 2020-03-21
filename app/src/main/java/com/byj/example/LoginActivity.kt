package com.byj.example

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editId = findViewById<EditText>(R.id.edit_id)
        val editPw = findViewById<EditText>(R.id.edit_password)
        val editName = findViewById<EditText>(R.id.edit_name)
        val buttonLogin = findViewById<Button>(R.id.btn_login)
        val buttonSignIn = findViewById<Button>(R.id.btn_registerJoin)

        buttonLogin.setOnClickListener {
            val sharedPref = getSharedPreferences("StudyPreference", Context.MODE_PRIVATE)
            val signId = sharedPref.getString("signInId", "idError")
            val signPw = sharedPref.getString("signInPass", "pwError")
            val signName = sharedPref.getString("signInName", "nameError")

            if (editId.text.toString() == signId && editPw.text.toString() == signPw && editName.text.toString() == signName) {
                val startMain = Intent(this@LoginActivity, MainActivity::class.java)
                startMain.putExtra("signInId",signId)
                Toast.makeText(this@LoginActivity, "메인화면으로 이동하였습니다", Toast.LENGTH_SHORT).show()
                startActivity(startMain)
            } else {
                Toast.makeText(this@LoginActivity, "입력값이 틀렸습니다", Toast.LENGTH_SHORT).show()
            }
        }

        buttonSignIn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignInActivity::class.java)
            startActivity(intent)
            Toast.makeText(this@LoginActivity, "회원가입 화면으로 이동하였습니다", Toast.LENGTH_SHORT).show()

        }
    }
}

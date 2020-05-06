package com.example.sessionmanagementusingkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.sdnkcreations.sessionmanagerinkotlin.SessionManager
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    lateinit var session: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        session = SessionManager(applicationContext)
        if (session.isLoggedIn()) {
            var i: Intent = Intent(applicationContext, FirstActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
            finish()
        }

        btnLogin.setOnClickListener {

            val username: String = txtUsername.text.toString()
            val password: String = txtPassword.text.toString()

            if (username.trim().length > 0 && password.trim().length > 0) {

                session.createLoginSession(username, password)
                var i: Intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(i)
                finish()

            } else {

                Toast.makeText(this, "Login Failed...\n Please enter both credential", Toast.LENGTH_LONG).show()
            }

        }
    }
}

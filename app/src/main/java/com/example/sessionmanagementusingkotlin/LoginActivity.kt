package com.example.sessionmanagementusingkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sdnkcreations.sessionmanagerinkotlin.SessionManager
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    lateinit var session: SessionManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        session = SessionManager(applicationContext)
        session.checkLogin()

        val user: HashMap<String, String> = session.getUserDetails()

        val name: String = user.get(SessionManager.KEY_NAME)!!
        val pass: String = user.get(SessionManager.KEY_PASS)!!

        Disp_Username.setText("Name: " + name)
        Disp_Password.setText("Email: " + pass)
        btnLogout.setOnClickListener {

            session.LogoutUser()
        }
    }
}

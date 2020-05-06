package com.sdnkcreations.sessionmanagerinkotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.sessionmanagementusingkotlin.FirstActivity
import com.example.sessionmanagementusingkotlin.LoginActivity

class SessionManager {

    var pref: SharedPreferences
    var edior: SharedPreferences.Editor
    var context: Context
    var PRIVATE_MODE: Int = 0


    constructor(context: Context) {

        this.context = context
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        edior = pref.edit()
    }


    companion object {

        val PREF_NAME: String = "KotlinDemo"
        val IS_LOGIN: String = "isLogin"
        val KEY_NAME: String = "name"
        val KEY_PASS: String = "pass"

    }

    fun createLoginSession(name: String, emai: String) {

        edior.putBoolean(IS_LOGIN, true)
        edior.putString(KEY_NAME, name)
        edior.putString(KEY_PASS, emai)
        edior.commit()
    }

    fun checkLogin() {

        if (!this.isLoggedIn()) {
            var i: Intent = Intent(context, FirstActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            context.startActivity(i)
        }
    }

    fun getUserDetails(): HashMap<String, String> {

        var user: Map<String, String> = HashMap<String, String>()
        (user as HashMap).put(KEY_NAME, pref.getString(KEY_NAME, null).toString())
        (user as HashMap).put(KEY_PASS, pref.getString(KEY_PASS, null).toString())

        return user
    }

    fun LogoutUser() {
        edior.clear()
        edior.commit()


        val i: Intent = Intent(context, LoginActivity::class.java)
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(i)
    }

    fun isLoggedIn(): Boolean {

        return pref.getBoolean(IS_LOGIN, false)
    }


}
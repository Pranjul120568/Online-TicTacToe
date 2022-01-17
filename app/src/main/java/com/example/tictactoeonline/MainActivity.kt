package com.example.tictactoeonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()
        Log.d("HERE is the user",auth.toString())
        if(auth!!.currentUser==null){
            startActivity(Intent(this,loginActivity::class.java))
        }else{

        }

    }

    override fun onBackPressed() {
        finish()
    }
}
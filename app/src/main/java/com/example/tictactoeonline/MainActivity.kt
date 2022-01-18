package com.example.tictactoeonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tictactoeonline.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? =null
    private var database:FirebaseDatabase?=null
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        Log.d("HERE is the user",auth.toString())
        if(auth!!.currentUser==null){
            startActivity(Intent(this,loginActivity::class.java))
        }else{
            binding.SendBtn.setOnClickListener{
                sendRequest(binding)
            }
        }
    }
    private fun sendRequest(binding: ActivityMainBinding) {
     val email:String=binding.enterIdEt.text.toString()
        database= FirebaseDatabase.getInstance()
        var userEmail=auth!!.currentUser!!.email
        var myref=database!!.reference
        if(email.isEmpty()){
            Toast.makeText(this,"Please enter username!",Toast.LENGTH_SHORT).show()
        }else{
            myref.child("users").child(email).child("Request").setValue(userEmail)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
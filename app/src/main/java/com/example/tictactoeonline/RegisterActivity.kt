package com.example.tictactoeonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.tictactoeonline.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private var auth:FirebaseAuth?=null
    private var database: FirebaseDatabase?= FirebaseDatabase.getInstance()
    private lateinit var binding:ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegisterBinding.inflate(layoutInflater)
        auth= FirebaseAuth.getInstance()
        setContentView(binding.root)
        var emailEt: EditText =binding.loginIdEt1
        var passwordEt: EditText =binding.passwordEt1
        binding.registerInBtn.setOnClickListener{
            registerUser(emailEt,passwordEt)

        }
    }

    private fun registerUser(emailEt: EditText, passwordEt: EditText) {
        auth= FirebaseAuth.getInstance()
        var myRef=database!!.reference
        var email:String=emailEt.text.toString()
        var password:String=passwordEt.text.toString()
        if(email.isEmpty() ||password.isEmpty()){
            Toast.makeText(this,"Please enter username/password", Toast.LENGTH_SHORT).show()
        }else{
            auth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this,"User created Successfully",Toast.LENGTH_SHORT).show()

                    myRef.child("users").child(splitString(email)).child("Request").setValue(auth!!.currentUser!!.uid).addOnSuccessListener {
                        Log.d("HERE",splitString(email)+" "+auth!!.currentUser!!.uid+" "+it.toString())
                    }
                    startActivity(Intent(this,loginActivity::class.java))
                }else{
                    Toast.makeText(this,"Please Re-enter the details",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun splitString(email:String):String{
        var split=email.split("@")
        return split[0]
    }
}
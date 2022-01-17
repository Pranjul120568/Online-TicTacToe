package com.example.tictactoeonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tictactoeonline.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth

class loginActivity : AppCompatActivity() {
    private var auth: FirebaseAuth? =null
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth= FirebaseAuth.getInstance()
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var emailEt:EditText=binding.loginIdEt
        var passwordEt:EditText=binding.passwordEt
        binding.signInBtn.setOnClickListener{
            checkLogin(emailEt,passwordEt)
        }
        binding.RegisterTv.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
    private fun checkLogin(emailEt:EditText,passwordEt:EditText) {
        auth= FirebaseAuth.getInstance()
        var email:String=emailEt.text.toString()
        var password:String=passwordEt.text.toString()
        if(email.isEmpty() ||password.isEmpty()){
            Toast.makeText(this,"Please enter username/password",Toast.LENGTH_SHORT).show()
        }else{
auth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener{
    if(it.isSuccessful){
        Toast.makeText(this,"Successfully Logged In",Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        startActivity(Intent(this,MainActivity::class.java))
    }else{
        Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show()
    }
}
        }
    }
}
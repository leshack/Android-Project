package com.example.accers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.accers.databinding.ActivitySignInBinding
import com.example.accers.databinding.ActivityStartPageBinding
import com.google.firebase.auth.FirebaseAuth

class StartPage : AppCompatActivity() {

    private lateinit var binding: ActivityStartPageBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.login.setOnClickListener{
            var intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
        binding.signup.setOnClickListener{
            var intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

 }

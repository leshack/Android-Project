package com.example.accers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.accers.databinding.ActivityHeader1Binding
import com.example.accers.databinding.ActivityStartPageBinding
import com.google.firebase.auth.FirebaseAuth


class Header1 : AppCompatActivity() {

    private lateinit var binding: ActivityHeader1Binding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHeader1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        kotlin.run {
            val firebaseUser = firebaseAuth.currentUser
            if (firebaseUser != null){
                val email = firebaseUser?.email
                binding.emailheader.text = email.toString().trim()
        }

    }

    }
//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        return super.onCreateView(name, context, attrs)
//        info()
//    }

//    private  fun info(){
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null){
//            val email = firebaseUser?.email
//            binding.emailheader.text = email.toString().trim()
//        }else{
//            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
//        }
//
//    }
}
package com.example.accers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import java.lang.Thread.sleep


class Splashscreen : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        firebaseAuth = FirebaseAuth.getInstance()

        val thread = Thread() {

            kotlin.run {
                if(firebaseAuth.currentUser != null) {
                    sleep(5000);
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    var intent = Intent(this, StartPage::class.java)
                    startActivity(intent)
                }
            }

        };thread.start();
    }
}
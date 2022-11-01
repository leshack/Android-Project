package com.example.activitiesandintents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonReply : Button = findViewById(R.id.btnReply)
        textView = findViewById(R.id.message)

        buttonReply.text ="OPEN IMAGE"

        buttonReply.setOnClickListener {

            var imageIntent = Intent(this,ImageActivity::class.java)
            startActivity(imageIntent)
        }
    }
    fun showMessage(view: View){
        textView.text ="HAPPY BIRTHDAY"
    }
}
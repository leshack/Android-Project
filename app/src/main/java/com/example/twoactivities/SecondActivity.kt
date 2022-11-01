package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//// Getting values from the main activity using keystrokes
        val mMessageEditText:EditText = findViewById(R.id.editText_reply)

        val message = intent.getStringExtra("SEND_KEY_VALUE")

        var textView: TextView = findViewById(R.id.text_message_reply)

        textView.text = message.toString()

        val buttonReply : Button = findViewById(R.id.button_reply)

/// A signing button to an intent explicit intent to on the mainActivity
        buttonReply.setOnClickListener {

            val firstIntent = Intent(this, MainActivity::class.java)


            var message = mMessageEditText.text.toString().trim()
            firstIntent.putExtra("REPLY_KEY_VALUE", message)

            startActivity(firstIntent)
        }


    }
}
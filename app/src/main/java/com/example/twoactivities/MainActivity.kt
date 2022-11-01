package com.example.twoactivities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mMessageEditText:EditText = findViewById(R.id.editText_send)

       val message = intent.getStringExtra("REPLY_KEY_VALUE")

       var textViewReply: TextView = findViewById(R.id.text_message_reply)

        textViewReply.text = message.toString()


        val buttonSend : Button = findViewById(R.id.button_send)

            buttonSend.setOnClickListener {

                val secondIntent = Intent(this, SecondActivity::class.java)


                var message = mMessageEditText.text.toString().trim()
                secondIntent.putExtra("SEND_KEY_VALUE", message)

                startActivity(secondIntent)
            }


    }
}
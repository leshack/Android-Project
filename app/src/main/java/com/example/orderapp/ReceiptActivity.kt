package com.example.orderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReceiptActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        var showReceipt : TextView = findViewById(R.id.showReceipt)
        var paybtn : FloatingActionButton = findViewById(R.id.btnPayment)


        paybtn.setOnClickListener{

        }

    }
}
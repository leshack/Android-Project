package com.example.systembroadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val receiver = AirplaneModeChangedReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var receiverIntent = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(receiver,receiverIntent)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}
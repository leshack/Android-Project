package com.example.systembroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class AirplaneModeChangedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val isAirPlaneModeEnabled = intent?.getBooleanExtra("state", false) ?: return

        if(isAirPlaneModeEnabled){
            Toast.makeText(context,"Airplane Mode enabled", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(context,"Airplane Mode is disabled", Toast.LENGTH_LONG).show()
        }
    }
}
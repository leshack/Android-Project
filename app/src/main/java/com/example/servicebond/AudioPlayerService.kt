package com.example.servicebond

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast

class AudioPlayerService : Service(){

    lateinit var player: MediaPlayer

    override fun onBind(intent:Intent): IBinder?{
        return null
    }
    override fun onCreate(){
        super.onCreate()
        Toast.makeText(this,"Service was created", Toast.LENGTH_SHORT).show()
    }
    override fun onStartCommand(intent: Intent, flags:Int, startId: Int): Int{
        player = MediaPlayer.create(this,Settings.System.DEFAULT_ALARM_ALERT_URI)
        player.isLooping = true
        player.start()
        Toast.makeText(this,"Service was Started",Toast.LENGTH_SHORT).show()
        return START_STICKY
    }
    override fun onDestroy() {
        super.onDestroy()
        player.stop()
        Toast.makeText(this,"Service being Destroyed",Toast.LENGTH_SHORT).show()
    }
}

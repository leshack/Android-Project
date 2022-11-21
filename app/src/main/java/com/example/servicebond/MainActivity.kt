package com.example.servicebond

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart: Button = findViewById(R.id.btnStart)
        val btnStop: Button = findViewById(R.id.btnStop)

        Log.d("TIMER","Current Thread"+ Thread.currentThread().name)

        val runnable = Runnable{
            var num: Int= 1
            Log.d("TIMER","Current Thread"+ Thread.currentThread().name)
            while(num< 7){
                try{
                    if (num ==6){
                        num =0
                    }
                    Log.d("TIMER","Second$num")
                    Thread.sleep(1000)
                }
                catch (e:InterruptedException){
                    e.printStackTrace()
                }
                num++
            }
        }
        val thread = Thread (runnable)
        thread.start()

        btnStart.setOnClickListener{
            val intentService = Intent(applicationContext,AudioPlayerService::class.java)
            startService(intentService)
        }
        btnStop.setOnClickListener{
            val intentService = Intent(applicationContext,AudioPlayerService::class.java)
            stopService(intentService)
        }
    }
}
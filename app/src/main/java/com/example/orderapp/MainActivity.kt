package com.example.orderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    var orderMessage = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var donut : ImageView = findViewById(R.id.donut)
        var iceCream : ImageView = findViewById(R.id.ice_cream)
        var froyo : ImageView = findViewById(R.id.froyo)
        var orderButton : FloatingActionButton = findViewById(R.id.btnOrder)

        var sprinkleCheck: CheckBox = findViewById(R.id.sprinkles)
        var oreoCheck: CheckBox = findViewById(R.id.oreos)
        var fruitCheck: CheckBox = findViewById(R.id.fruit)

        donut.setOnClickListener{
            orderMessage = getString(R.string.donut_order_message)
            displayToast(orderMessage)
        }

        iceCream.setOnClickListener{
            orderMessage = getString(R.string.ice_cream_order_message)
            displayToast(orderMessage)
        }
        froyo.setOnClickListener{
            orderMessage = getString(R.string.froyo_order_message)
            displayToast(orderMessage)
        }

        orderButton.setOnClickListener{

            if (sprinkleCheck.isChecked){
                //
            }
            if (oreoCheck.isChecked){
                //
            }
            if (fruitCheck.isChecked){
                //
            }

            var intentOrder = Intent(this, OrderActivity::class.java)
            intentOrder.putExtra("ORDER", orderMessage)
            startActivity(intentOrder)
        }
    }

    private fun displayToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
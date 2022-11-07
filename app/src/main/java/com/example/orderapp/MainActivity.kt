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
    var price = ""
    var totalAmount: Int = 0
    private val result = StringBuilder()


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
            result.append(" Donuts 50Ksh")
            totalAmount += 50
        }


        iceCream.setOnClickListener{
            orderMessage = getString(R.string.ice_cream_order_message)
            displayToast(orderMessage)
            result.append(" IceCream 150Ksh")
            totalAmount += 150

        }
        froyo.setOnClickListener{
            orderMessage = getString(R.string.froyo_order_message)
            displayToast(orderMessage)
            result.append(" Yoghurt 50Ksh")
            totalAmount += 200
        }

        orderButton.setOnClickListener{
            result.append("Selected Items:")
            if (sprinkleCheck.isChecked){
                result.append(" Sprinkles 20Ksh")
                        totalAmount += 20
            }
            if (oreoCheck.isChecked){
                result.append(" Oreo 30Ksh")
                totalAmount += 30
            }
            if (fruitCheck.isChecked){
                result.append(" Fruit 50Ksh")
                totalAmount += 50
            }

             ///toast notification with the total amount of orders
            var intentOrder = Intent(this, OrderActivity::class.java)
            result.append(" Total: " + totalAmount + "Ksh")
            Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()

            // converting the total to string so as to be able to bundle/store it using intent keystrokes
            price = totalAmount.toString()

            // transfering the stored data to the next activity using the bundle keystrokes
            intentOrder.putExtra("ORDER", orderMessage)
            intentOrder.putExtra("PRICE", price)
            startActivity(intentOrder)
        }
    }

    private fun displayToast(message: String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
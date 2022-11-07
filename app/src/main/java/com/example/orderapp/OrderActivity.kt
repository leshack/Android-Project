package com.example.orderapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    var totalAmount: Int = 0
    var totals =""
    private val result = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        var orderTextView: TextView = findViewById(R.id.order_textview)
        var fullName: TextView = findViewById(R.id.full_name)
        var address: TextView = findViewById(R.id.address)
        var phoneNumber: TextView = findViewById(R.id.phone_number)
        var orderDetails: TextView = findViewById(R.id.order_description)

        var sameDayChecked : RadioButton = findViewById(R.id.sameday)
        var nextDayChecked : RadioButton = findViewById(R.id.nextday)
        var selfPickChecked : RadioButton = findViewById(R.id.pickup)

        var orderButton: Button = findViewById(R.id.submitOrder)

        var orderedItem = intent.getStringExtra("ORDER")
        orderTextView.text = orderedItem.toString()

         /// getting the string variable then converting to interger so as to add
        var orderedtotals = intent.getStringExtra("PRICE")
        var total = orderedtotals
        if (total != null) {
            totalAmount +=  total.toInt()
        }

        orderButton.setOnClickListener {
            if(sameDayChecked.isChecked){
                result.append(" sameday 300Ksh")
                totalAmount += 300
            } else if (nextDayChecked.isChecked){
                result.append(" Next day 100Ksh")
                totalAmount += 100
            } else if (selfPickChecked.isChecked){
                result.append(" self pick free")
                totalAmount += 0
            }
            ///toast notification with the total amount of orders
            result.append(" Total: " + totalAmount + "Ksh")
            Toast.makeText(applicationContext, result.toString(), Toast.LENGTH_LONG).show()

            // converting the total to string so as to be able to bundle/store it using intent keystrokes
            totals = totalAmount.toString()

            // transfering the stored data to the next activity using the bundle keystrokes
            var intentReceiptActivity = Intent(this, ReceiptActivity::class.java)
            intentReceiptActivity.putExtra("TOTAL", totals)
            intentReceiptActivity.putExtra("NAME", fullName.text.toString().trim())
            intentReceiptActivity.putExtra("ADDRESS", address.text.toString().trim())
            intentReceiptActivity.putExtra("PHONE", phoneNumber.text.toString().trim())
            intentReceiptActivity.putExtra("DETAILS", orderDetails.text.toString().trim())
            startActivity(intentReceiptActivity)
        }
    }
}
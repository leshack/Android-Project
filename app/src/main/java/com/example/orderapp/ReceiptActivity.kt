package com.example.orderapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ReceiptActivity : AppCompatActivity() {

    var totalAmount: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        var showReceipt : TextView = findViewById(R.id.showReceipt)
        var paybtn : FloatingActionButton = findViewById(R.id.btnPayment)

        /// getting the string variable then converting to interger so as to add
        var pricetotals = intent.getStringExtra("TOTAL")
        var total = pricetotals
        if (total != null) {
            totalAmount +=  total.toInt()
        }
        showReceipt.text =  "The total cost of your order is Ksh." +pricetotals.toString()+ ".proceed to pay"

        val name = intent.getStringExtra("NAME")
        val address = intent.getStringExtra("ADDRESS")
        val phone = intent.getStringExtra("PHONE")
        val details = intent.getStringExtra("DETAILS")

        var fullname: TextView = findViewById(R.id.full_name)
        fullname.text = "Full name:"+name.toString()

        var addres: TextView = findViewById(R.id.address)
        addres.text = "Address:"+address.toString()

        var number: TextView = findViewById(R.id.phone_number)
        number.text = "Phone number:"+phone.toString()

        var detail: TextView = findViewById(R.id.order)
        detail.text = "Order details:"+details.toString()


        paybtn.setOnClickListener{

        }

    }
}
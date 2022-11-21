package com.example.preferenceimplementation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var nameET: EditText = findViewById(R.id.edittext)
        var ageET: EditText = findViewById(R.id.edittextage)
        var btnSave: Button = findViewById(R.id.btnSave)
        var btnLoad: Button = findViewById(R.id.btnLoad)
        var checkAge: CheckBox = findViewById(R.id.checkBox)

        //MODE_PUBLIC - Other apps can read/access the sharedprefs
        //MODE_PRIVATE - Other apps cannot read/access the sharedprefs
        //MODE_APPEND - take existing preferences and append new preferences

        var sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)

        // whenever we want to write to SharedPrefences, we need its editor
        var editor = sharedPref.edit() // the edit function returns the editor of our shared preference reference

        // Retrieve data in edit texts and store them in SharedPreferences by clicking button
        btnSave.setOnClickListener{

            var name = nameET.text.toString() //getData from edit text
            var age = ageET.text.toString().toInt() // get age val from edit tet as an integer
            var isAdult = checkAge.isChecked // Boolean value os whether the check box is checked or not

            //Data can now be saved to the shared preferences using the editor object we created above
            // within shared prefences, data is saved as KEY - VALUE pairs
            editor.putString("name", name)
            editor.putInt("age", age)
            editor.putBoolean("isAdult", isAdult)
            editor.apply() // used to write data to shared preferences asychronously --commit() does so synchronously


        }
        btnLoad.setOnClickListener{
            var name = sharedPref.getString("name", null)
            var age = sharedPref.getInt("age", 0)
            var isAdult = sharedPref.getBoolean("isAdult", false)

            //Populate our views
            nameET.setText(name)
            ageET.setText(age.toString())
            checkAge.isChecked = isAdult
        }
        // closes btnLoad's onclick listener
    }// closes onCreate
} // closes MainActivity : AppCompatActivity(
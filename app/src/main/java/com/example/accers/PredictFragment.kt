package com.example.accers

import android.app.*
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class PredictFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_predict, container, false)

        var button: Button = view.findViewById(R.id.predict)
        var result: TextView = view.findViewById(R.id.result)
        var biking: EditText = view.findViewById(R.id.editbkt)
        var smoking: EditText = view.findViewById(R.id.editsmk)

        button.setOnClickListener {
            val url = "https://accer.onrender.com/predict"
            val queue = Volley.newRequestQueue(activity)
            val stringRequest: StringRequest = object : StringRequest(Method.POST, url,
                Response.Listener { response: String ->
                    try {
                        result.text = response

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }

                } as Response.Listener<String>,
                Response.ErrorListener { error: VolleyError ->
                    onErrorResponse(
                        error
                    )
                }) {
                override fun getParams(): MutableMap<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["biking"] = smoking.text.toString()
                    params["smoking"] = biking.text.toString()
                    return params
                }
            }

            queue.add(stringRequest)
        }

        return view
    }

    private fun onErrorResponse(error: VolleyError) {
        // this method is called when we get
        // any error while fetching data from our API
        Log.e("TAG", "RESPONSE IS $error")
        // in this case we are simply displaying a toast message.
        Toast.makeText(activity, "Fail to get response", Toast.LENGTH_SHORT).show()
    }

}
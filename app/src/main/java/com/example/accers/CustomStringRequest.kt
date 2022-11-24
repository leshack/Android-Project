package com.example.accers

import androidx.annotation.GuardedBy
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class CustomStringRequest(
    method: Int,
    url: String,
   // jsonRequest: String?,
    listener: Response.Listener<String>,
    errorListener: Response.ErrorListener?
) : Request<String>(method, url, errorListener) {

    private val lock = Any()

    @GuardedBy("lock")
    private var listener: Response.Listener<String>? = listener

    override fun getParams(): MutableMap<String, String> {
        return params
    }

    override fun cancel() {
        super.cancel()
        synchronized(lock) { listener = null }
    }

    override fun deliverResponse(response: String) {
        var listener: Response.Listener<String>?
        synchronized(lock) { listener = this.listener }
        if (listener != null) {
            listener!!.onResponse(response)
        }
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<String> {
        val parsed: String = try {
            String(response.data, Charset.forName(HttpHeaderParser.parseCharset(response?.headers)))
        } catch (e: UnsupportedEncodingException) {
            // Since minSdkVersion = 8, we can't call
            // new String(response.data, Charset.defaultCharset())
            // So suppress the warning instead.
            String(response.data)
        }

        return Response.success(
            parsed,
            HttpHeaderParser.parseCacheHeaders(response)
        )
    }
}
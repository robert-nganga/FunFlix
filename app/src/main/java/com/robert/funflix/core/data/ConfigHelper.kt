package com.robert.funflix.core.data

import android.content.Context
import com.robert.funflix.R
import org.json.JSONObject

object ConfigHelper {
    fun getAccessToken(context: Context): String {
        return try {
            val inputStream = context.resources.openRawResource(R.raw.config)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            jsonObject.getString("access_token")
        } catch (e: Exception){
            ""
        }
    }
}

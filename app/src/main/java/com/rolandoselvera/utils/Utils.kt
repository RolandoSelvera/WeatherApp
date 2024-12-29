package com.rolandoselvera.utils

import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

inline fun <reified T> T.toEncodedJson(): String {
    val gson = Gson()
    val json = gson.toJson(this)
    return URLEncoder.encode(json, StandardCharsets.UTF_8.toString())
}

inline fun <reified T> String.fromEncodedJson(): T {
    val decodedJson = URLDecoder.decode(this, StandardCharsets.UTF_8.toString())
    val gson = Gson()
    return gson.fromJson(decodedJson, T::class.java)
}
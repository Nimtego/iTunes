package com.nimtego.plectrum.data.cache

import com.google.gson.Gson


class Serializer {

    private val gson = Gson()

    fun serialize(entity: Any, clazz: Class<*>): String {
        return gson.toJson(entity, clazz)
    }


    fun <T> deserialize(string: String, clazz: Class<T>): T {
        return gson.fromJson(string, clazz)
    }
}
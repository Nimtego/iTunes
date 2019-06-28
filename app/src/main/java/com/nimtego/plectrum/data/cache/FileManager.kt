package com.nimtego.plectrum.data.cache

import android.content.Context
import java.io.*
import javax.inject.Inject

class FileManager @Inject constructor() {

    internal fun writeToFile(file: File, fileContent: String) {
        if (!file.exists()) {
            try {
                val writer = FileWriter(file)
                writer.write(fileContent)
                writer.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }


    internal fun readFileContent(file: File): String {
        val fileContentBuilder = StringBuilder()
        if (file.exists()) {
            val stringLine: String
            try {
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)
                stringLine = bufferedReader.readLine()
                while ( stringLine != null) {
                    fileContentBuilder.append(stringLine).append("\n")
                }
                bufferedReader.close()
                fileReader.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        return fileContentBuilder.toString()
    }


    internal fun exists(file: File): Boolean {
        return file.exists()
    }


    internal fun clearDirectory(directory: File): Boolean {
        var result = false
        if (directory.exists()) {
            for (file in directory.listFiles()) {
                result = file.delete()
            }
        }
        return result
    }


    internal fun writeToPreferences(context: Context, preferenceFileName: String, key: String,
                                    value: Long) {

        val sharedPreferences = context.getSharedPreferences(preferenceFileName,
                Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }


    internal fun getFromPreferences(context: Context, preferenceFileName: String, key: String): Long {
        val sharedPreferences = context.getSharedPreferences(preferenceFileName,
                Context.MODE_PRIVATE)
        return sharedPreferences.getLong(key, 0)
    }
}

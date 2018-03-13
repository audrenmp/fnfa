package com.example.amauplot.festivalnationaldufilmdanimation

import android.content.Context
import android.util.Log

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.Arrays


class FileHelper {

    companion object {
        fun readFile(path: String, context: Context ): String{
            try {
                val file = context.openFileInput(path)
                val inputStreamReader = InputStreamReader(file)
                val bufferedReader = BufferedReader(inputStreamReader)
                val sb = StringBuilder()
                var line: String

                try {
                    var tmpRead = bufferedReader.readLine()
                    while (tmpRead != null) {
                        sb.append(tmpRead)
                        tmpRead = bufferedReader.readLine()
                    }
                    return sb.toString();
                } catch(e: IOException) {
                    Log.d("Error", e.toString());
                }
            } catch(e: FileNotFoundException) {
                Log.d("Error", e.toString());
            }
            return ""
        }

        fun writeFile(path: String, value: String, context: Context){
            try {
                val fos: FileOutputStream = context.openFileOutput(path, Context.MODE_PRIVATE)
                val byteArr = value.toByteArray()

                try {
                    fos.write(byteArr)
                    fos.close()
                } catch(e: IOException){
                    Log.d("Error File Helper", e.toString())
                }
            } catch(e: FileNotFoundException){
                Log.d("Error File Helper", e.toString())
            }
        }
    }

}


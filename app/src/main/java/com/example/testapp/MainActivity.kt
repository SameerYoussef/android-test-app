package com.example.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val engine = Engine(120, 200)

        CoroutineScope(Dispatchers.Main).launch {
            engine.turnOn()
        }
        setContentView(R.layout.activity_main)
    }
}

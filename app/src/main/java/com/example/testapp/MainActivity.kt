package com.example.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.playlist.PlaylistFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val engine = Engine(120, 200)
        val car = Car(100.0, engine)

        CoroutineScope(Dispatchers.Main).launch {
            car.turnOn()
        }
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, PlaylistFragment.newInstance())
                .commit()
        }
    }
}

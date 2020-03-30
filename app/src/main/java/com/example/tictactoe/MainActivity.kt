package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn2Player = findViewById<Button>(R.id.playerVS)
        btn2Player.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, 2)
            }
            startActivity(intent)
        }

        val btn1Player = findViewById<Button>(R.id.computerVS)
        btn1Player.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, 1)
            }
            startActivity(intent)
        }
    }




}

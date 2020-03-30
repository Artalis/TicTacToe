package com.example.tictactoe

import TicTacToe
import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.provider.AlarmClock
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlayerActivity : AppCompatActivity() {

    private var player =1
    private var tictactoe =TicTacToe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        var playerNumber= this.intent.getIntExtra(AlarmClock.EXTRA_MESSAGE,2)  //backButton
        val btnBack = findViewById<Button>(R.id.back)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }
        val btnRestart = findViewById<Button>(R.id.restart) //restartButton
        btnRestart.setOnClickListener {
            val intent = Intent(this, PlayerActivity::class.java).apply {
                putExtra(AlarmClock.EXTRA_MESSAGE, playerNumber)
            }
            startActivity(intent)
        }
        val buttons = mutableListOf<ImageButton>() //buttons in list
        buttons.add(findViewById(R.id.imageButton1))
        buttons.add(findViewById(R.id.imageButton2))
        buttons.add(findViewById(R.id.imageButton3))
        buttons.add(findViewById(R.id.imageButton4))
        buttons.add(findViewById(R.id.imageButton5))
        buttons.add(findViewById(R.id.imageButton6))
        buttons.add(findViewById(R.id.imageButton7))
        buttons.add(findViewById(R.id.imageButton8))
        buttons.add(findViewById(R.id.imageButton9))

        val textView = findViewById<TextView>(R.id.view) //text View

        for (b in buttons){ //for all buttons
            b.setOnClickListener {
                val buttonNumber = buttons.indexOf(b)+1 //number of button

                if(tictactoe.emptyField(buttonNumber)) { //jeżeli puste pole
                    tictactoe.newMove(player, buttonNumber) //new move for player on field buttonNumber
                    var win =tictactoe.checkWhoWin(player) //if win return string, else empty string
                    if(win!=""){
                        makeNotClicakble(buttons)
                        textView.text = win
                    }
                    player = if (player == 1) { //zamiana graczy i zmiana zdjęcia
                        b.setImageResource(R.drawable.cross)
                        2
                    } else {
                        b.setImageResource(R.drawable.circle)
                        1
                    }
                    if (playerNumber == 1 && win=="")  { //jeżeli 2 graczy
                        player = 2
                        var x= tictactoe.comMove()     //ruch komputera
                        win =tictactoe.checkWhoWin(player)
                        if(win!=""){
                            makeNotClicakble(buttons)
                            textView.text = win
                        }
                        buttons[x-1].setImageResource(R.drawable.circle)
                        player=1

                    }
                }
            }
        }



    }

    fun makeNotClicakble(button: MutableList<ImageButton>){
        for(b in button)
            b.isClickable = false

    }




}

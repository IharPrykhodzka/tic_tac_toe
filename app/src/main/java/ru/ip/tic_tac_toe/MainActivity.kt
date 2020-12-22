package ru.ip.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import splitties.toast.toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var player: Player
    private lateinit var playerAI: Player
    var pass = false
    private lateinit var thisPlayer: Player
    private lateinit var pField: MutableList<Button>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start.setOnClickListener {

            pField = mutableListOf(line_1_1, line_1_2, line_1_3, line_2_1, line_2_2, line_2_3, line_3_1, line_3_2, line_3_3)

            val crossAndZero = listOf(CROSS, ZERO)
            player = Player(crossAndZero.random())
            playerAI = Player(if (player.turn == CROSS) {
                ZERO
            } else {
                CROSS
            })

            Log.d(LOG, "${player.turn} \n ${playerAI.turn}")

            btnInit()
            game(player, playerAI)
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            line_1_1.id -> updatePlayingField(line_1_1, thisPlayer.turn)
            line_1_2.id -> updatePlayingField(line_1_2, thisPlayer.turn)
            line_1_3.id -> updatePlayingField(line_1_3, thisPlayer.turn)

            line_2_1.id -> updatePlayingField(line_2_1, thisPlayer.turn)
            line_2_2.id -> updatePlayingField(line_2_2, thisPlayer.turn)
            line_2_3.id -> updatePlayingField(line_2_3, thisPlayer.turn)

            line_3_1.id -> updatePlayingField(line_3_1, thisPlayer.turn)
            line_3_2.id -> updatePlayingField(line_3_2, thisPlayer.turn)
            line_3_3.id -> updatePlayingField(line_3_3, thisPlayer.turn)
        }
    }

    private fun game(player: Player, playerAI: Player) {

        if (!pass) {
            if (playerAI.turn == CROSS) {
                thisPlayer = playerAI
                playerAI.playAI(pField)

            } else {
                toast("Ваш ход")
                thisPlayer = player
            }
        } else {
            if (playerAI.turn == ZERO) {
                thisPlayer = playerAI
                playerAI.playAI(pField)
            } else {
                toast("Ваш ход")
                thisPlayer = player

            }
        }

    }

    private fun updatePlayingField(theBtn: Button, turn: Int) {

        pField.remove(theBtn)
        theBtn.isEnabled = false
        pass = !pass

        if (turn == CROSS) {
            theBtn.setBackgroundResource(R.drawable.ic_add)
        } else {
            theBtn.setBackgroundResource(R.drawable.ic_zero_24)
        }


        game(player, playerAI)
    }

    private fun btnInit() {
        line_1_1.setOnClickListener(this)
        line_1_2.setOnClickListener(this)
        line_1_3.setOnClickListener(this)
        line_2_1.setOnClickListener(this)
        line_2_2.setOnClickListener(this)
        line_2_3.setOnClickListener(this)
        line_3_1.setOnClickListener(this)
        line_3_2.setOnClickListener(this)
        line_3_3.setOnClickListener(this)
    }
}
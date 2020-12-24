package ru.ip.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import splitties.toast.toast

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var thePlayer: Player
    private lateinit var playerAI: Player
    var pass = false
    private lateinit var thisPlayer: Player
    private lateinit var pField: MutableList<Button>
    private lateinit var checkField: MutableMap<Button, Player>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        start.setOnClickListener {

            start.setText(R.string.play_again)

            letGone()

            pField = mutableListOf(line_1_1, line_1_2, line_1_3, line_2_1, line_2_2, line_2_3, line_3_1, line_3_2, line_3_3)
            pass = false
            pField.forEach{
                it.setBackgroundResource(R.drawable.black_square)
                it.isEnabled = true
            }

            val crossAndZero = listOf(CROSS, ZERO)
            thePlayer = Player(crossAndZero.random())
            playerAI = Player(if (thePlayer.turn == CROSS) {
                ZERO
            } else {
                CROSS
            })

            checkField = mutableMapOf((start to playerAI))
            Log.d(LOG, "${thePlayer.turn} \n ${playerAI.turn}")

            initBtn()
            playGame(thePlayer, playerAI)
        }
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            line_1_1.id -> updatePlayingField(line_1_1, thisPlayer)
            line_1_2.id -> updatePlayingField(line_1_2, thisPlayer)
            line_1_3.id -> updatePlayingField(line_1_3, thisPlayer)

            line_2_1.id -> updatePlayingField(line_2_1, thisPlayer)
            line_2_2.id -> updatePlayingField(line_2_2, thisPlayer)
            line_2_3.id -> updatePlayingField(line_2_3, thisPlayer)

            line_3_1.id -> updatePlayingField(line_3_1, thisPlayer)
            line_3_2.id -> updatePlayingField(line_3_2, thisPlayer)
            line_3_3.id -> updatePlayingField(line_3_3, thisPlayer)
        }
    }

    private fun playGame(player: Player, playerAI: Player) {

        if (!pass) {
            if (playerAI.turn == CROSS) {
                giveTextResult.text = getString(R.string.AI_first)
                thisPlayer = playerAI
                playerAI.playAI(pField)

            } else {
                giveTextResult.text = getString(R.string.you_first)
                thisPlayer = player
            }
        } else {
            if (playerAI.turn == ZERO) {
                thisPlayer = playerAI
                playerAI.playAI(pField)
            } else {
                thisPlayer = player

            }
        }

    }

    private fun updatePlayingField(theBtn: Button, player: Player) {

        pField.remove(theBtn)
        theBtn.isEnabled = false
        pass = !pass


        checkField[theBtn] = player

        if (player.turn == CROSS) {
            theBtn.setBackgroundResource(CROSS_VIEW)
        } else {
            theBtn.setBackgroundResource(ZERO_VIEW)
        }


        if (!checkEnd()) {
            playGame(thePlayer, playerAI)
        } else {
            pField.forEach {
                it.isEnabled = false
            }
        }
    }

    private fun initBtn() {
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

    private fun checkEnd(): Boolean {

        if (checkField[line_1_1] == thePlayer && checkField[line_1_2] == thePlayer && checkField[line_1_3] == thePlayer) {
            win_1_3.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_2_1] == thePlayer && checkField[line_2_2] == thePlayer && checkField[line_2_3] == thePlayer) {
            win_4_6.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_3_1] == thePlayer && checkField[line_3_2] == thePlayer && checkField[line_3_3] == thePlayer) {
            win_7_9.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_1_1] == thePlayer && checkField[line_2_1] == thePlayer && checkField[line_3_1] == thePlayer) {
            win_1_7.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_1_2] == thePlayer && checkField[line_2_2] == thePlayer && checkField[line_3_2] == thePlayer) {
            win_2_8.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_1_3] == thePlayer && checkField[line_2_3] == thePlayer && checkField[line_3_3] == thePlayer) {
            win_3_9.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_1_1] == thePlayer && checkField[line_2_2] == thePlayer && checkField[line_3_3] == thePlayer) {
            win_1_9.visibility = View.VISIBLE
            printText(thePlayer)
            return true
        } else if (checkField[line_1_3] == thePlayer && checkField[line_2_2] == thePlayer && checkField[line_3_1] == thePlayer) {
            win_3_7.visibility = View.VISIBLE
            printText(thePlayer)
            return true


        } else if (checkField[line_1_1] == playerAI && checkField[line_1_2] == playerAI && checkField[line_1_3] == playerAI) {
            win_1_3.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_2_1] == playerAI && checkField[line_2_2] == playerAI && checkField[line_2_3] == playerAI) {
            win_4_6.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_3_1] == playerAI && checkField[line_3_2] == playerAI && checkField[line_3_3] == playerAI) {
            win_7_9.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_1_1] == playerAI && checkField[line_2_1] == playerAI && checkField[line_3_1] == playerAI) {
            win_1_7.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_1_2] == playerAI && checkField[line_2_2] == playerAI && checkField[line_3_2] == playerAI) {
            win_2_8.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_1_3] == playerAI && checkField[line_2_3] == playerAI && checkField[line_3_3] == playerAI) {
            win_3_9.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_1_1] == playerAI && checkField[line_2_2] == playerAI && checkField[line_3_3] == playerAI) {
            win_1_9.visibility = View.VISIBLE
            printText(playerAI)
            return true
        } else if (checkField[line_1_3] == playerAI && checkField[line_2_2] == playerAI && checkField[line_3_1] == playerAI) {
            win_3_7.visibility = View.VISIBLE
            printText(playerAI)
            return true


        } else if (pField.isNullOrEmpty()) {
            giveTextResult.text = getString(R.string.draw)
            return true
        } else {
            return false
        }
    }

    private fun printText(player: Player) {
        if (player == playerAI) {
            giveTextResult.text = getString(R.string.you_loose)
        } else {
            giveTextResult.text = getString(R.string.you_win)
        }
    }

    private fun letGone(){
        win_3_7.visibility = View.GONE
        win_1_9.visibility = View.GONE
        win_2_8.visibility = View.GONE
        win_1_7.visibility = View.GONE
        win_7_9.visibility = View.GONE
        win_4_6.visibility = View.GONE
        win_1_3.visibility = View.GONE
        win_3_9.visibility = View.GONE
    }
}
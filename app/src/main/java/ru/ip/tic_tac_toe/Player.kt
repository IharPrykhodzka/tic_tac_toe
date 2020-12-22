package ru.ip.tic_tac_toe

import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class Player(val turn: Int) {

    fun playAI(pField: MutableList<Button>){
        val randomBtn = pField.random()
        randomBtn.performClick()
    }
}
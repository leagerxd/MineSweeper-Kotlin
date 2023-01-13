package com.nazar.rgr.minesweeper

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        playB.setOnClickListener { createBoardActivity(8, 8, 10) }
    }

    private fun createBoardActivity(height: Int, width: Int, amountOfMines: Int) {
        val intent = Intent(this, GameActivity::class.java).apply {
            GameActivity.run {
                putExtra(INTENT_MINES, amountOfMines)
                putExtra(INTENT_WIDTH, width)
                putExtra(INTENT_HEIGHT, height)
            }
        }
        startActivity(intent)
    }
}


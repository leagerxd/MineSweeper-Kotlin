package com.nazar.rgr.minesweeper

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentActivity
import android.util.Log

class GameEndDialog : DialogFragment() {
    private var result: Game.EndState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            result = it.getEnum<Game.EndState>("result")
        }
        Log.i(TAG, "Created from $activity")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = when (result!!) {
            Game.EndState.WON -> "You won!"
            Game.EndState.LOST -> "You lost :("
            Game.EndState.UNDECIDED -> "You... huh"
        }

        return activity?.let { activity: FragmentActivity ->
            AlertDialog.Builder(activity).apply {
                setMessage(title)
                setPositiveButton("Play again") { _, _ ->
                    activity.restart()
                }
            }.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    companion object {
        @JvmStatic
        fun newInstance(result: Game.EndState) =
                GameEndDialog().apply {
                    arguments = Bundle().apply {
                        putEnum("result", result)
                    }
                }

        const val TAG = "GameEndDialog"
    }
}

fun Bundle.putEnum(key: String, enum: Enum<*>) {
    putString(key, enum.name)
}

inline fun <reified T : Enum<T>> Bundle.getEnum(key: String): T {
    return enumValueOf(getString(key)!!)
}

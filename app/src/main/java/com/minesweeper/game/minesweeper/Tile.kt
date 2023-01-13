package com.minesweeper.game.minesweeper

open class Tile {
    enum class State { HIDDEN, FLAGGED, MINE, NUMBERED }

    var isMine = false
    fun plantMine() {
        isMine = true
    }

    fun reveal() {
        if (isRevealed) return
        isRevealed = true
    }

    fun toggleFlag() {
        isFlagged = !isFlagged
    }

    fun removeMine() {
        isMine = false
    }

    fun isEmpty() = !isMine && numberOfMinedNeighbors == 0

    var numberOfMinedNeighbors = 0
    var isFlagged = false
    var isRevealed = false
    val state: State
    get() = if (isRevealed) (if (isMine) State.MINE else State.NUMBERED) else (if (isFlagged) State.FLAGGED else State.HIDDEN)

    override fun toString(): String {
        return when (state) {
            State.MINE -> "MINE"
            State.HIDDEN -> "HIDDEN"
            State.FLAGGED -> "FLAGGED"
            State.NUMBERED -> numberOfMinedNeighbors.toString()
        }
    }
}
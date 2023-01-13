package com.minesweeper.game.minesweeper

import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.GridLayout.spec
import android.widget.ImageView
import utils.DoublyIndexedItem

fun GridLayout.addGame(viewModel: GameViewModel) {
    rowCount = viewModel.height
    columnCount = viewModel.width
    viewModel.board.forEach { item ->
        val params = GridLayout.LayoutParams(spec(item.i), spec(item.j))
        addView(createView(viewModel, item), params)
    }
    updateBoard(viewModel.game)
}

fun GridLayout.createView(viewModel: GameViewModel, item: DoublyIndexedItem<Tile>): View {
    return ImageView(context).apply {
        setOnLongClickListener {
            viewModel.holdTile(item.i, item.j)
            updateBoard(viewModel.game)
            true
        }
        setOnClickListener {
            viewModel.clickTile(item.i, item.j)
            updateBoard(viewModel.game)
        }
    }
}

fun ViewGroup.children(): List<View> = (0 until childCount).map { getChildAt(it) }

fun GridLayout.updateBoard(game: Game) {
    game.board.flatten().zip(children()).forEach { (item, view) ->
        val image = when (item.value.state) {
            Tile.State.HIDDEN -> R.drawable.hidden_tile
            Tile.State.FLAGGED -> R.drawable.flag_tile
            Tile.State.MINE -> R.drawable.ic_mine
            Tile.State.NUMBERED -> numberedTile(item.value.numberOfMinedNeighbors)
        }
        view as ImageView
        view.setImageResource(image)
    }
}

fun numberedTile(number: Int) = when (number) {
    0 -> R.drawable.ic_minesweeper_0
    1 -> R.drawable.ic_minesweeper_1
    2 -> R.drawable.ic_minesweeper_2
    3 -> R.drawable.ic_minesweeper_3
    4 -> R.drawable.ic_minesweeper_4
    5 -> R.drawable.ic_minesweeper_5
    6 -> R.drawable.ic_minesweeper_6
    7 -> R.drawable.ic_minesweeper_7
    8 -> R.drawable.ic_minesweeper_8
    else -> R.mipmap.ic_launcher
}


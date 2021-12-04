package days

import Day
import utils.Matrix

object Day4 : Day {
    override fun solve1(s: String): String {
        val lines = s.trim().lineSequence().iterator()
        val numberIterator = lines.next().split(",").map(String::toInt).iterator()

        var last = -1
        val picked = mutableSetOf<Int>()

        fun checkWinner(board: Matrix<Int>): Boolean = (0 until 5).any { i ->
            (0 until 5).all { board[it][i] in picked } ||
            (0 until 5).all { board[i][it] in picked }
        }

        val boards = mutableListOf<Matrix<Int>>()

        while(lines.hasNext()) {
            lines.next()

            boards.add((0 until 5).map {
                lines.next().trim()
            }.map {
                it.split("\\s+".toRegex()).map(String::toInt)
            })
        }

        while (boards.none(::checkWinner)) {
            last = numberIterator.next()
            picked.add(last)
        }

        val winner = boards.first(::checkWinner)
        return (winner.flatten().filter { it !in picked }.sum()*last).toString()
    }

    override fun solve2(s: String): String {
        val lines = s.trim().lineSequence().iterator()
        val numberIterator = lines.next().split(",").map(String::toInt).iterator()

        var last = -1
        val picked = mutableSetOf<Int>()

        fun checkWinner(board: Matrix<Int>): Boolean = (0 until 5).any { i ->
            (0 until 5).all { board[it][i] in picked } ||
                    (0 until 5).all { board[i][it] in picked }
        }

        val boards = mutableListOf<Matrix<Int>>()

        while(lines.hasNext()) {
            lines.next()

            boards.add((0 until 5).map {
                lines.next().trim()
            }.map {
                it.split("\\s+".toRegex()).map(String::toInt)
            })
        }

        var lastWinners = listOf<Matrix<Int>>()
        var boardsLocked: List<Matrix<Int>> = boards

        while (boardsLocked.isNotEmpty() && numberIterator.hasNext()) {
            last = numberIterator.next()
            picked.add(last)
            val (a,b) = boardsLocked.partition(::checkWinner)
            lastWinners = a
            boardsLocked = b
        }

        val lastWinner = lastWinners.first()
        return (lastWinner.flatten().filter { it !in picked }.sum()*last).toString()
    }
}
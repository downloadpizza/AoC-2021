package days

import Day

object Day9 : Day {
    override fun solve1(s: String): String {
        val tubes = s.trim().lines().map {
            it.toCharArray().map { char -> char.digitToInt() }
        }
        val num = mutableListOf<Int>()
        for (x in tubes[0].indices) {
            for (y in tubes.indices) {
                val value = tubes[y][x]
                val neighbours = listOf(
                    tubes.getOrNull(y - 1)?.getOrNull(x - 1) ?: 9,
                    tubes.getOrNull(y)?.getOrNull(x - 1) ?: 9,
                    tubes.getOrNull(y + 1)?.getOrNull(x - 1) ?: 9,

                    tubes.getOrNull(y - 1)?.getOrNull(x) ?: 9,
                    tubes.getOrNull(y + 1)?.getOrNull(x) ?: 9,

                    tubes.getOrNull(y - 1)?.getOrNull(x + 1) ?: 9,
                    tubes.getOrNull(y)?.getOrNull(x + 1) ?: 9,
                    tubes.getOrNull(y + 1)?.getOrNull(x + 1) ?: 9,
                )

                if (neighbours.all { it > value })
                    num.add(value)
            }
        }
        return num.sumOf { it + 1 }.toString()
    }

    enum class Direction {
        Up,
        Right,
        Down,
        Left
    }

    override fun solve2(s: String): String {
        val tubes = s.trim().lines().map {
            it.toCharArray().map { char -> char.digitToInt() }
        }

        val xIndices = tubes[0].indices
        val yIndices = tubes.indices

        val lowest = mutableListOf<Pair<Int, Int>>()

        val basinMap = yIndices.map { y ->
            xIndices.map { x ->
                val value = tubes[y][x]
                val neighbours = listOf(
                    (tubes.getOrNull(y)?.getOrNull(x - 1) ?: 9) to Direction.Left,
                    (tubes.getOrNull(y - 1)?.getOrNull(x) ?: 9) to Direction.Up,
                    (tubes.getOrNull(y + 1)?.getOrNull(x) ?: 9) to Direction.Down,
                    (tubes.getOrNull(y)?.getOrNull(x + 1) ?: 9) to Direction.Right
                )

                if (neighbours.all { it.first > value })
                    lowest.add(x to y)

                neighbours.filter {
                    it.first > value && it.first != 9
                }.map(Pair<Int, Direction>::second)
            }
        }

        fun getBasin(x: Int, y: Int, members: MutableSet<Pair<Int, Int>>): Set<Pair<Int, Int>> {
            val dirs = basinMap[y][x]
            members.add(x to y)
            dirs.map {
                when (it) {
                    Direction.Up -> Pair(x, y - 1)
                    Direction.Right -> Pair(x + 1, y)
                    Direction.Down -> Pair(x, y + 1)
                    Direction.Left -> Pair(x - 1, y)
                }
            }
                .filter { it !in members }
                .forEach {
                    getBasin(it.first, it.second, members)
                }
            return members
        }

        return lowest.map { (lx, ly) ->
            getBasin(lx, ly, mutableSetOf()).size
        }.sortedDescending().take(3).reduce { acc, i -> acc * i }.toString()
    }
}
package days

import Day

data class EnergyLevel(var value: Int, var flashed: Boolean)

object Day11 : Day {
    override fun solve1(s: String): String {
        val energyLevels = s.lines().map {
            it.toCharArray().map {EnergyLevel(it.digitToInt(), flashed = false) }
        }

        fun flash(x: Int, y: Int) {
            if (y !in energyLevels.indices || x !in energyLevels[0].indices)
                return
            if(energyLevels[y][x].flashed)
                return
            energyLevels[y][x].value += 1
            if(energyLevels[y][x].value <= 9)
                return
            energyLevels[y][x].flashed = true
            for(xo in -1..1) {
                for(yo in -1..1) {
                    if(xo != 0 || yo != 0)
                        flash(x + xo, y + yo)
                }
            }
        }

        val flashes = (0 until 100).sumOf {
            energyLevels.forEach { row ->
                row.forEach {
                    it.value += 1
                }
            }

            val indices = energyLevels.flatMapIndexed { row, list ->
                list.mapIndexedNotNull { col, energyLevel ->
                    if(energyLevel.value > 9) Pair(row, col) else null
                }
            }

            for((y, x) in indices) {
                flash(x, y)
            }

            val sum = energyLevels.sumOf { row ->
                row.count { it.flashed }
            }

            energyLevels.forEach { row ->
                row.forEach {
                    if(it.flashed)
                        it.value = 0
                    it.flashed = false
                }
            }

            sum
        }
        return flashes.toString()
    }

    override fun solve2(s: String): String {
        val energyLevels = s.lines().map {
            it.toCharArray().map {EnergyLevel(it.digitToInt(), flashed = false) }
        }

        fun flash(x: Int, y: Int) {
            if (y !in energyLevels.indices || x !in energyLevels[0].indices)
                return
            if(energyLevels[y][x].flashed)
                return
            energyLevels[y][x].value += 1
            if(energyLevels[y][x].value <= 9)
                return
            energyLevels[y][x].flashed = true
            for(xo in -1..1) {
                for(yo in -1..1) {
                    if(xo != 0 || yo != 0)
                        flash(x + xo, y + yo)
                }
            }
        }

        val flashes = (1 until 10000).first {
            energyLevels.forEach { row ->
                row.forEach {
                    it.value += 1
                }
            }

            val indices = energyLevels.flatMapIndexed { row, list ->
                list.mapIndexedNotNull { col, energyLevel ->
                    if(energyLevel.value > 9) Pair(row, col) else null
                }
            }

            for((y, x) in indices) {
                flash(x, y)
            }

            val sum = energyLevels.all { row ->
                row.all { it.flashed }
            }

            energyLevels.forEach { row ->
                row.forEach {
                    if(it.flashed)
                        it.value = 0
                    it.flashed = false
                }
            }

            sum
        }
        return flashes.toString()
    }
}
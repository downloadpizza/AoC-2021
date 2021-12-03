package days

import Day

object Day3 : Day {
    var epsilon = ""
    var gamma = ""

    override fun solve1(s: String): String {
        val len = s.trim().lineSequence().first().length
        val lines = s.trim().lines()
            .map { it.toInt(2) }

        var gamma = 0
        var epsi = 0

        for (i in (len - 1) downTo 0) {
            val bit = lines.count { it.and((1 shl i)) != 0 }
            if ((2 * bit) > lines.size)
                gamma += 1 shl i
            else
                epsi += 1 shl i
        }

        this.gamma = gamma.toString(2).padStart(len, '0')
        this.epsilon = epsi.toString(2).padStart(len, '0')

        return (gamma * epsi).toString()
    }

    override fun solve2(s: String): String {
        val len = s.trim().lineSequence().first().length
        val lines = s.trim().lines()

        var oxy = lines
        var co = lines

        for (i in 0 until len) {
            val oxyRating = 2 * oxy.count { it[i] == '1' } >= oxy.size
            val coRating = 2 * co.count { it[i] == '1' } < co.size

            oxy = oxy.filter { (it[i] == '1') == oxyRating }
            if (co.size != 1)
                co = co.filter { (it[i] == '1') == coRating }
        }

        val oxyV = oxy.single().toInt(2)
        val coV = co.single().toInt(2)

        return (oxyV * coV).toString()
    }
}

object Day3OL : Day {
    override fun solve1(s: String) = s.lines().run {
        this.fold(listOf<Int>()) { p, n ->
            n.mapIndexed { i, c ->
                c.digitToInt() + p.getOrElse(i) { 0 }
            }
        }.foldIndexed(Pair(0L, 0L)) { i, p, n ->
            if (2 * n >= this.size)
                Pair(
                    p.first + (1 shl (this[0].length - i - 1)),
                    p.second
                ) else
                Pair(
                    p.first,
                    p.second + (1 shl (this[0].length - i - 1))
                )
        }.run {
            this.first * this.second
        }
    }.toString()

    override fun solve2(s: String): String {
        return ""
    }
}
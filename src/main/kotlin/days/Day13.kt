package days

import Day
import utils.Matrix
import utils.mapMatrix

object Day13 : Day {
    fun foldDots(dots: List<Pair<Int, Int>>, alongAxis: Char, alongDistance: Int): List<Pair<Int, Int>> =
        when (alongAxis) {
            'x' -> {
                dots.map { (x, y) ->
                    Pair(
                        if (x > alongDistance)
                            x - (x - alongDistance) * 2
                        else
                            x, y
                    )
                }.toSet().toList()
            }
            'y' -> {
                dots.map { (x, y) ->
                    Pair(
                        x, if (y > alongDistance)
                            y - (y - alongDistance) * 2
                        else
                            y
                    )
                }.toSet().toList()
            }
            else -> {
                throw Exception("NOT A VALID AXIS")
            }
        }.toList()

    override fun solve1(s: String): String {
        val (pairsS, foldsS) = s.trim().split("\n\n")
        val dots = pairsS.lines().map {
            val (a, b) = it.split(",")
            Pair(a.toInt(), b.toInt())
        }

        val folds = foldsS.lines().map {
            val (a, b) = it.replace("fold along ", "").split("=")
            Pair(a[0], b.toInt())
        }

        return foldDots(dots, folds[0].first, folds[0].second).count().toString()
    }

    override fun solve2(s: String): String {
        val (pairsS, foldsS) = s.trim().split("\n\n")
        val dots = pairsS.lines().map {
            val (a, b) = it.split(",")
            Pair(a.toInt(), b.toInt())
        }

        val folds = foldsS.lines().map {
            val (a, b) = it.replace("fold along ", "").split("=")
            Pair(a[0], b.toInt())
        }

        val endDots = folds.fold(dots) { dt, a ->
            foldDots(dt, a.first, a.second)
        }
        val xMax = endDots.maxOf { it.first }
        val yMax = endDots.maxOf { it.second }

        return (0..yMax).joinToString("\n") { y ->
            (0..xMax).map { x ->
                if (Pair(x, y) in endDots)
                    '#'
                else
                    ' '
            }.joinToString("")
        }
    }
}

object Day13OL : Day {
    override fun solve1(s: String): String = s.trim().split("\n\n").run {
        this[1].lines().map {
            it.replace("fold along ", "").split("=")
        }.map {
            Pair(it[0][0], it[1].toInt())
        }.subList(0, 1).fold(this[0].lines().map {
            it.split(",")
        }.map {
            Pair(it[0].toInt(), it[1].toInt())
        }) { dt, fold ->
            when (fold.first) {
                'x' -> {
                    dt.map { (x, y) ->
                        Pair(
                            if (x > fold.second)
                                x - (x - fold.second) * 2
                            else
                                x, y
                        )
                    }.toSet().toList()
                }
                'y' -> {
                    dt.map { (x, y) ->
                        Pair(
                            x, if (y > fold.second)
                                y - (y - fold.second) * 2
                            else
                                y
                        )
                    }.toSet().toList()
                }
                else -> {
                    throw Exception("NOT A VALID AXIS")
                }
            }
        }.count().toString()
    }

    override fun solve2(s: String): String = s.trim().split("\n\n").run {
        this[1].lines().map {
            it.replace("fold along ", "").split("=")
        }.map {
            Pair(it[0][0], it[1].toInt())
        }.fold(this[0].lines().map {
            it.split(",")
        }.map {
            Pair(it[0].toInt(), it[1].toInt())
        }) { dt, v ->
            when (v.first) {
                'x' -> {
                    dt.map { (x, y) ->
                        Pair(
                            if (x > v.second)
                                x - (x - v.second) * 2
                            else
                                x, y
                        )
                    }.toSet().toList()
                }
                'y' -> {
                    dt.map { (x, y) ->
                        Pair(
                            x, if (y > v.second)
                                y - (y - v.second) * 2
                            else
                                y
                        )
                    }.toSet().toList()
                }
                else -> {
                    throw Exception("NOT A VALID AXIS")
                }
            }
        }
    }.run {
        (0..this.maxOf { it.second }).joinToString("\n") { y ->
            (0..this.maxOf { it.first }).map { x ->
                if (Pair(x, y) in this)
                    '#'
                else
                    ' '
            }.joinToString("")
        }
    }
}
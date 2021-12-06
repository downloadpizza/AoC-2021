package days

import Day
import utils.anyRange
import java.lang.Integer.*

data class Vent(val start: Pair<Int, Int>, val end: Pair<Int, Int>)

object Day5 : Day {
    override fun solve1(s: String): String = s.trim().lineSequence().map {
        val (sf, ef) = it.split(" -> ")
        val (sx, sy) = sf.split(",").map(String::toInt)
        val (ex, ey) = ef.split(",").map(String::toInt)
        Vent(Pair(sx, sy), Pair(ex, ey))
    }.filter {
        it.start.first == it.end.first || it.start.second == it.end.second
    }.flatMap { vent ->
        when(vent.start.first == vent.end.first) {
            true -> (min(vent.start.second, vent.end.second)..max(vent.start.second, vent.end.second)).map { Pair(vent.start.first, it) }
            false -> (min(vent.start.first, vent.end.first)..max(vent.start.first, vent.end.first)).map { Pair(it, vent.start.second) }
        }
    }.groupingBy {
        it
    }.eachCount().values.count { it >= 2 }.toString()

    override fun solve2(s: String): String = s.trim().lineSequence().map {
        val (sf, ef) = it.split(" -> ")
        val (sx, sy) = sf.split(",").map(String::toInt)
        val (ex, ey) = ef.split(",").map(String::toInt)
        Vent(Pair(sx, sy), Pair(ex, ey))
    }.flatMap { vent ->
        when {
            (vent.start.first == vent.end.first) -> (vent.start.second anyRange vent.end.second)
                .map { Pair(vent.start.first, it) }
            (vent.start.second == vent.end.second) -> (vent.start.first anyRange vent.end.first)
                .map { Pair(it, vent.start.second) }
            else -> (vent.start.first anyRange vent.end.first)
                .zip(vent.start.second anyRange vent.end.second)
                .map { (x, y) ->
                    Pair(x, y)
                }
        }
    }.groupingBy {
        it
    }.eachCount().values.count { it >= 2 }.toString()
}
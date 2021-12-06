package days

import Day
import java.math.BigInteger

object Day6 : Day {
    override fun solve1(s: String): String {
        val fishes = s.trim().split(",").map(String::toInt).toMutableList()
        for (it in 0 until 80) {
            for (i in fishes.indices) {
                if (fishes[i] > 0) {
                    fishes[i] -= 1
                } else {
                    fishes[i] = 6
                    fishes.add(8)
                }
            }
        }
        return fishes.size.toString()
    }

    override fun solve2(s: String): String {
        val fishes = s.trim().split(",").map(String::toByte)
            .groupingBy { it }
            .eachCount()
            .mapValues {
                it.value.toLong()
            }.toMutableMap()
        for(it in 0 until 256) {
            println(it)
            println(fishes)
            println()
            for (i in 0..fishes.keys.maxOrNull()!!) {
                fishes[(i-1).toByte()] = fishes.getOrDefault(i.toByte(), 0)
                fishes[i.toByte()] = 0
            }
            val zeroes = fishes.getOrDefault(-1, 0)
            fishes[-1] = 0
            fishes[6] = fishes.getOrDefault(6, 0) + zeroes
            fishes[8] = fishes.getOrDefault(8, 0) + zeroes
        }
        return fishes.values.reduce { acc, i -> acc + i }.toString()
    }
}

object Day6OL : Day {
    override fun solve1(s: String) = (0 until 80).fold(s.trim().split(",").map(String::toByte)
        .groupingBy { it }
        .eachCount()
        .map {
            it.key to it.value.toLong()
        }.toMap()) { r, _ ->
        r.flatMap { (i, c) ->
            if (i > 0)
                listOf((i - 1).toByte() to c)
            else
                listOf(6.toByte() to c, 8.toByte() to c)
        }.groupBy {
            it.first
        }.mapValues {
            it.value.sumOf { it.second }
        }.toMap()
    }.values.sum().toString()

    override fun solve2(s: String) = (0 until 256).fold(s.trim().split(",").map(String::toByte)
        .groupingBy { it }
        .eachCount()
        .map {
            it.key to it.value.toLong()
        }.toMap()) { r, _ ->
        r.flatMap { (i, c) ->
            if (i > 0)
                listOf((i - 1).toByte() to c)
            else
                listOf(6.toByte() to c, 8.toByte() to c)
        }.groupBy {
            it.first
        }.mapValues {
            it.value.sumOf { it.second }
        }.toMap()
    }.values.sum().toString()
}
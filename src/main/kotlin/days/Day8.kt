package days

import Day

object Day8 : Day {
    private val digits = listOf(
        "abcefg".toCharArray().toSortedSet(),
        "cf".toCharArray().toSortedSet(),
        "acdeg".toCharArray().toSortedSet(),
        "acdfg".toCharArray().toSortedSet(),
        "bcdf".toCharArray().toSortedSet(),
        "abdfg".toCharArray().toSortedSet(),
        "abdefg".toCharArray().toSortedSet(),
        "acf".toCharArray().toSortedSet(),
        "abcdefg".toCharArray().toSortedSet(),
        "abcdfg".toCharArray().toSortedSet(),
    )
    override fun solve1(s: String): String {
        val notes = s.trim().lineSequence().map {
            val (patterns, output) = it.split(" | ").map {
                it.split(" ")
            }
            patterns to output
        }

        val checkFor = listOf(1, 4, 7, 8).map { digits[it].count() }
        return notes.sumOf {
            it.second.count {
                it.length in checkFor
            }
        }.toString()
    }

    private val chars = "abcdefg".toList()
    private val permutations = allPermutations(chars).asSequence()

    override fun solve2(s: String): String {
        val notes = s.trim().lineSequence().map {
            val (patterns, output) = it.split(" | ").map {
                it.split(" ")
            }
            patterns to output
        }

        return notes.sumOf { (patterns, output) ->
            val conv = permutations.map {
                chars.zip(it).toMap()
            }.first { conv ->
                patterns.count { pattern ->
                    digits.any { digitSet ->
                        digitSet == pattern.map { ch ->
                            conv[ch]!!
                        }.toSortedSet()
                    }
                } == 10
            }
            output.map { digit ->
                digits.indexOfFirst { digitSet ->
                    digitSet == digit.map { conv[it]!! }.toSortedSet()
                }
            }.joinToString("").toInt()
        }.toString()
    }
}

fun <T> allPermutations(list: List<T>): Set<List<T>> {
    if (list.isEmpty()) return setOf(emptyList())

    val result: MutableSet<List<T>> = mutableSetOf()
    for (i in list.indices) {
        allPermutations(list - list[i]).forEach{
                item -> result.add(item + list[i])
        }
    }
    return result
}
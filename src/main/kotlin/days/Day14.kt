package days

import Day

object Day14 : Day {
    override fun solve1(s: String): String {
        val lines = s.lineSequence()
        var template = lines.first()
        val pairs = lines.drop(2).map {
            val (a, b) = it.split(" -> ")
            a to b
        }.toMap()

        for (i in 0 until 10) {
            template = template.zipWithNext { a, b ->
                val key = "$a$b"
                if (pairs.containsKey(key)) {
                    "$a${pairs[key]}"
                } else {
                    "$a"
                }
            }.joinToString("") + template.last()
        }

        val oft = template.groupingBy { it }
            .eachCount()
            .values
        return (oft.maxOrNull()!! - oft.minOrNull()!!).toString()
    }

    override fun solve2(s: String): String {
        val lines = s.lineSequence()

        val fc = lines.first().trim().last()

        val template: MutableMap<Pair<Char, Char>, Long> =
            lines.first()
                .trim()
                .zipWithNext { a, b ->
                    Pair(a, b)
                }
                .groupingBy { it }
                .eachCount()
                .mapValues { (k, v) -> v.toLong() }
                .toMutableMap()

        val pairs = lines.drop(2).map {
            val (a, b) = it.split(" -> ")
            Pair(a[0], a[1]) to Pair(Pair(a[0], b[0]), Pair(b[0], a[1]))
        }.toMap()

        for (i in 0 until 40) {
            for ((k, v) in template.toMap()) {
                val (a, b) = pairs[k]!!
                template[k] = template.getOrDefault(k, 0) - v
                template[a] = template.getOrDefault(a, 0) + v
                template[b] = template.getOrDefault(b, 0) + v
            }
        }

        val amounts = template.map { (k,v) ->
            k.first to v
        }.groupBy({
            it.first
        }) {
            it.second
        }.mapValues { (k, v) ->
            if(k == fc)
                v.sum() + 1
            else
                v.sum()
        }.values

        return (amounts.maxOrNull()!! - amounts.minOrNull()!!).toString()
    }
}
package days

import Day

object Day10 : Day {
    override fun solve1(s: String): String {
        return s.trim().lineSequence().map {
            val st = ArrayDeque<Char>()
            it.toCharArray().asSequence().firstNotNullOfOrNull {
                if(it in "([{<") {
                    st.addLast(it)
                    null
                } else {
                    val pop = st.removeLastOrNull()
                    when(it) {
                        ')' -> if(pop == '(') null else 3
                        ']' -> if(pop == '[') null else 57
                        '}' -> if(pop == '{') null else 1197
                        '>' -> if(pop == '<') null else 25137
                        else -> null
                    }
                }
            } ?: 0
        }.sum().toString()
    }

    val values2 = mapOf(
        '(' to 1,
        '[' to 2,
        '{' to 3,
        '<' to 4
    )

    override fun solve2(s: String): String {
        val queues = s.trim().lineSequence().mapNotNull { str ->
            val st = ArrayDeque<Char>()
            val cont = str.trim().toCharArray().all {
                if (it in "([{<") {
                    st.addLast(it)
                    true
                } else {
                    val pop = st.removeLastOrNull()
                    when (it) {
                        ')' -> pop == '('
                        ']' -> pop == '['
                        '}' -> pop == '{'
                        '>' -> pop == '<'
                        else -> false
                    }
                }
            }
            if(cont) st else null
        }
        val scores = queues.map {
            var sum = 0L
            while(it.isNotEmpty()) {
                sum *= 5L
                sum += values2[it.removeLast()]!!
            }
            println(sum)
            sum
        }.sorted().toList()
        return scores[scores.size/2].toString()
    }
}
package academy.kt.domain

class CheckAnswerUseCase {
    fun isCorrectAnswer(answer: List<String>, correctAnswer: List<String>): Boolean {
        if (answer == correctAnswer) return true
        val answer = aggregateDelays(answer)
        val correctAnswer = aggregateDelays(correctAnswer)
        if (answer == correctAnswer) return true
        // Accept answers with values in a different order
        return groupValues(answer) == groupValues(correctAnswer)
    }

    private fun aggregateDelays(answer: List<String>): List<String> =
        answer.fold(listOf<String>()) { acc, s ->
            if (s.matches(DELAY_REGEX)) {
                val last = acc.lastOrNull()
                if (last != null && last.matches(DELAY_REGEX)) {
                    val newDelay = s.substringAfter("(").substringBefore(" sec)").toInt() +
                            last.substringAfter("(").substringBefore(" sec)").toInt()
                    acc.dropLast(1) + "($newDelay sec)"
                } else {
                    acc + s
                }
            } else {
                acc + s
            }
        }

    private fun groupValues(answer: List<String>): List<Set<String>> =
        answer.fold(listOf()) { acc, s ->
            if (s.startsWith("(")) {
                acc.plusElement(setOf(s))
            } else {
                val last = acc.lastOrNull()
                if (last != null && (last.size > 1 || last.singleOrNull()?.startsWith("(") == false)) {
                    acc.dropLast(1).plusElement(last + s)
                } else {
                    acc.plusElement(setOf(s))
                }
            }
        }

    companion object {
        val DELAY_REGEX = Regex("\\((\\d+) sec\\)")
    }
}
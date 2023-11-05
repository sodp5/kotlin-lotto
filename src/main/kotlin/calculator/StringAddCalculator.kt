package calculator

class StringAddCalculator {
    private val customDelimiterRegex = Regex("//(.*)\n(.*)")
    private val defaultDelimiterRegex = Regex("[,:]")

    fun add(text: String?): Int {
        if (text.isNullOrBlank()) {
            return 0
        }

        if (text.contains('-')) {
            throw RuntimeException()
        }

        val splits = splitByCustomDelimiter(text)
            ?: splitByDefaultDelimiter(text)

        val numbers = splits.map { it.toInt() }

        return numbers.sum()
    }

    private fun splitByCustomDelimiter(text: String): List<String>? {
        return customDelimiterRegex.find(text)
            ?.let {
                val customDelimiter = it.groupValues[1]

                it.groupValues[2].split(customDelimiter)
            }
            ?.filter { it.isNotBlank() }
    }

    private fun splitByDefaultDelimiter(text: String): List<String> {
        return text.split(defaultDelimiterRegex)
    }
}

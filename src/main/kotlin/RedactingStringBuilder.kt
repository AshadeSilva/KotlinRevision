package main.kotlin

class RedactingStringBuilder(private val redacted: Set<String>) {
    private val stringBuilder: StringBuilder = java.lang.StringBuilder()

    
    fun append(inputString: String): StringBuilder = stringBuilder.append(inputString.redacted())

    // extension methods are technically public BUT only in the scope of a class object
    fun String.redacted(): String = buildString{
        for (word in this.split(" ")){
            if (word in redacted){
                append("_".repeat(word.length))

            } else{
                append(word)
            }
            append(" ")
        }
    }

    override fun toString(): String = stringBuilder.toString()
}

// accessing string.redact extension using with
fun buildersRedactor(inputString: String): String {
    val builder = RedactingStringBuilder(setOf("these", "words", "are", "redacted"))
    // return inputString.redacted() doesn't work
    with (builder){
        return inputString.redacted()
    }
}

fun RedactingStringBuilder.newBuilder(){

}
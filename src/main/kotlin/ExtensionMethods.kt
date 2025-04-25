package main.kotlin

// note used in Point file
infix operator fun Point.plus(addTo: Int): Point = Point(first+addTo, second+addTo)

fun Double.isPowerOfTwo(): Boolean =
    when (this){
        0.0 -> true
        1.0 -> true
        this.toInt().toDouble() -> (this/2).isPowerOfTwo()
        else -> false
}
fun Int.isPowerOfTwo(): Boolean = this.toDouble().isPowerOfTwo()

fun String.isPalindrome(caseSensitive: Boolean = true): Boolean {
    var flag = true
    for (i in indices){
        if (caseSensitive){
            if (this[i] != this[length - i - 1]){
                flag == false
            }
        } else{
            if (this[i].uppercaseChar() != this[length - i - 1].uppercaseChar()){
                flag == false
            }
        }
    }
    return flag
}

fun Double.sameAsFloat() = this.toFloat().toDouble() == this
fun String.repeat(repeatTimes: Int) = buildString {
    for (i in 1..repeatTimes){
        append("this")
    }
}

fun <T> Array<T>.sumHashCodes(): Int = fold(0, {acc, new -> acc + new.hashCode()})
fun <T> Array<T>.reSumHashCodes(): Int = this.sumOf {it.hashCode()}
operator fun Pair<Double, Double>.unaryPlus(): Double = first + second
fun List<Boolean>.allTrue(): Boolean{
    var flag = true
    this.forEach{
        if (!it){flag = false}
    }
    return flag
}
fun <T> List<T>.allTrue(predicate: (T)->Boolean) = this.all{predicate(it)}
fun List<String>.allEmpty() = this.all(String::isNotEmpty)
fun <T> List<T>.hash() = ::hashCode // hash of the list
fun <T> T.hash() = :: hashCode
fun Boolean.and(other: Boolean): Boolean =
    if (this) {
        if (other) {
            true
        } else {
            false
        }
    } else{
    false
    }
fun Boolean.or(other: Boolean): Boolean =
    if (this){
        true
    } else{
        if (other){
            true
        } else{
            false
        }
    }

class ClassForExtensions(){
    private var number1: Int = 1
}

fun ClassForExtensions.newNumber(newNum: Int){
    // number1 = newNum is not allowed
    with (this){
    //    number1 = newNum   also is not allowed
    }
    // this task is not possible
}
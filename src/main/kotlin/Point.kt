package main.kotlin

fun main() {
    var a = Point (2,3)
    val b = Point (1,4)
    println(a in b)
    a += b
    a *= b
    a /= b


}

// note data classes are final and cannot be open
data class Point(var first: Int, var second: Int) {
    infix fun madeUpFun(other: Point) = (this + other) * other // infix: a madeUpFun b
    infix operator fun plus(other: Point): Point = Point(
        this.first + other.first,
        this.second + other.second
    ) // infix: a plus b // operator: a + b
    operator fun times(other: Point): Point = Point(
        this.first * other.first,
        this.second * other.second
    )
    // automatically create +=, *= etc
    operator fun times(factor: Int): Point = Point(
        this.first * factor,
        this.second * factor
    )
    operator fun get(index: Int): Int =
        when (index) {
            0 -> first
            1 -> second
            else -> throw UnsupportedOperationException("not 0 or 1")
        }
    operator fun set(index: Int, value: Int) = when (index) {
        0 -> first = value
        1 -> second = value
        else -> throw UnsupportedOperationException("not 0 or 1")
    }
    operator fun minus(other: Point): Point = this + (other * -1)
    private fun reciprocal(): Point =
        if (first != 0 && second != 0) {
            Point(1 / first, 1 / second)
        } else {
            throw UnsupportedOperationException("divide by 0")
        }
    operator fun div(other: Point): Point = this * (other.reciprocal())
    operator fun rem(other: Point): Point = this - (this / other) * other
    operator fun rangeTo(other: Point): List<Point> {
        var ongoingList: MutableList<Point> = mutableListOf()
        for (i in first..second) {
            for (j in other.first..other.second) {
                ongoingList.add(Point(i, j))
            }
        }
        return ongoingList
    }
    operator fun rangeUntil(other: Point) = this..Point(other.first - 1, other.second - 1)
    operator fun contains(other: Point): Boolean = (this.first <= other.first) && (this.second >= other.second)
    // contains automatically also adds !in
    operator fun compareTo(other: Point): Int = compareValuesBy(this, other, Point::first, Point::second)
    // unary funs don't have a standard definition (I made them up)
    operator fun unaryPlus(): Int = first + second
    operator fun unaryMinus(): Int = first - second
    operator fun not(): Point = Point(second, first)
    operator fun inc(): Point = Point(first++, second++)
    operator fun dec(): Point = Point(first--, second--)


}

operator fun Int.plus(addTo: Point): Point = addTo + this
// extension methods are visible to the package



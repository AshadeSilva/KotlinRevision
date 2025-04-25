package main.kotlin

interface KotlinMutableList<T> {
    fun addTo(newElement: T)
    fun remove()
    fun addAt(newElement: T, index: Int)
    fun removeAt(index: Int)
    fun contains(find: T): Boolean
    fun addManyTo(vararg newElements: T) = newElements.forEach { addTo(it) }
}

fun <T> KotlinMutableList<T>.eitherContains(other: KotlinMutableList<T>, find: T): Boolean =
    this.contains(find) || other.contains(find)
package main.kotlin

fun main() {
    val array = ResizingList<Int>(6)
    println(array)   // []
    array.addManyTo(1,2,3,4)
    println(array) // 1 2 3 4
    array.remove() // 1 2 3
    array.addTo(5) // 1 2 3 5
    array.addTo(6, 1) // 1 6 2 3 5
    array.removeAt(3) // 1 6 2 5
    array.addManyTo(7,8,9)
    println(array) // 1 6 2 5 7 8 9
}

class ResizingList<T> (val initialCapacity: Int){
    var size: Int = 0
        private set
    var currentCapacity: Int = initialCapacity
    private var elements: Array<T?> = if (initialCapacity < 0){
        throw  IllegalArgumentException()}
    else{
        arrayOfNulls<Any>(initialCapacity) as Array<T?>
    }

    operator fun iterator(): Iterator<T> = object: Iterator<T>{
        var current: Int? = null
        var next: Int = 0
        override fun hasNext(): Boolean = next<=size
        override fun next(): T{
            if (hasNext()){
                current = next
                return elements[next++]!!
            }
            else{
                throw UnsupportedOperationException("next called on end of list")
            }


        }
    }
    fun resize() {
        currentCapacity *= 2
        elements =  elements + arrayOfNulls<Any?>(currentCapacity) as Array<T?>
    }
    fun addManyTo(vararg elements: T) = elements.forEach { addTo(it) }
    fun addTo(toAdd: T, index: Int){
        if (size >= currentCapacity){
            resize()
        }
        if (size > index){
            for (i in size downTo index+1){
                elements[i] = elements[i-1]
            }
        }
        elements[index] = toAdd
        size++
    }
    fun removeAt(index: Int){
        if (index<0){
            throw UnsupportedOperationException("remove at negative index")
        }else{
            for (i in index..<size){
                elements[i] = elements[i+1]
            }
            size--
        }
    }
    fun addTo(toAdd: T) = addTo(toAdd, size)
    fun remove() = removeAt(size-1)
    fun removeAll() {
        for (i in 0..<size){
            remove()
        }
    }
    fun clear(){
        elements = arrayOfNulls<Any?>(initialCapacity) as Array<T?>
    }
    fun get(index: Int): T? {
        if (index !in 0..<size){
            throw IndexOutOfBoundsException("index not in list")
        }
        return elements[index]
    }
    fun contains(find: T) = elements.filter{it == find}.isNotEmpty()

    override fun toString(): String {
        return buildString {
            append("[")
            elements.slice(0..<size).forEach { append("$it, ") }
            append("]")
        }
    }
}
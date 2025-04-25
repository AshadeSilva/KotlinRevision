package main.kotlin


fun main() {
    val array = FixedCapacityList<Int>(6)
    println(array)   // []
    array.addManyTo(1,2,3,4)
    println(array) // 1 2 3 4
    array.remove()
    println(array) // 1 2 3
    array.addTo(5)
    println(array) // 1 2 3 5

    array.addAt(6, 1)
    println(array) // 1 6 2 3 5
    array.removeAt(3)
    println(array) // 1 6 2 5
    array.addTo(7)
    array.addTo(8)
    // array.addTo(9) // exception
}
class FixedCapacityList<T> (val capacity: Int): KotlinMutableList<T>{
    var size: Int = 0
        private set
    private var elements: Array<T?> = if (capacity < 0){
        throw  IllegalArgumentException()}
    else{
        arrayOfNulls<Any>(capacity) as Array<T?>
    }

    override fun addAt(toAdd: T, index: Int){
        if (size >= capacity){
            throw UnsupportedOperationException("list full")
        } else{
            for (i in size downTo index+1){
                elements[i] = elements[i-1]
            }
            elements[index] = toAdd
            size++
        }
    }
    operator fun iterator(): Iterator<T> = elements.slice(0..<size).iterator()
            as Iterator<T>

    override fun removeAt(index: Int){
        if (index<0){
            throw UnsupportedOperationException("remove at negative index")
        }else{
            for (i in index..<size){
                elements[i] = elements[i+1]
            }
            size--
        }
    }

    override fun addTo(toAdd: T) = addAt(toAdd, size)
    override fun addManyTo(vararg elements: T) = elements.forEach { addTo(it) }
    override fun remove() = removeAt(size-1)
    fun removeAll() {
        for (i in 0..<size){
            remove()
        }
    }
    fun clear(){
        elements = arrayOfNulls<Any?>(capacity) as Array<T?>
    }

    fun get(index: Int): T? {
        if (index !in 0..<size){
            throw IndexOutOfBoundsException("index not in list")
        }
        return elements[index]
    }
    override fun contains(find: T): Boolean = elements.contains(find)

    override fun toString(): String {
        return buildString {
            append("[")
            elements.slice(0..<size)
                .forEach { append("$it, ") }
            append("]")
        }
    }
}
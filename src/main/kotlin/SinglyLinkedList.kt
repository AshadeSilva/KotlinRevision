package main.kotlin

fun main() {
    val list: KotlinMutableList<Int> = SinglyLinkedList<Int>()
    list.addManyTo(1,2,3,4)
    println(list) // 1 2 3 4
    list.removeAt(3)
    println(list) // 1 2 4
    list.addAt(5,1)
    println(list) // 1 5 2 4
    list.removeAt(2)
    println(list) // 1 5 4

}

class SinglyLinkedList<T>: KotlinMutableList<T> {
    private var headNode: SinglyLinkedNode<T>? = null
    private val tailNode: SinglyLinkedNode<T>?
        get() = if (headNode == null){null} else{
            var currentNode = headNode
            while (currentNode!!.next != null){
                currentNode = currentNode.next
            }
            currentNode
        }
    private val size: Int
        get() {
            var counter = 0
            for (node in this){
                counter++
            }
            return counter
        }

    private class SinglyLinkedNode<T>(var element: T, var next: SinglyLinkedNode<T>? = null)
    private fun addNode(newNode: SinglyLinkedNode<T>){
        if (headNode == null){
            headNode = newNode
        } else{
            tailNode!!.next = newNode
        }
    }
    private operator fun iterator(): Iterator<SinglyLinkedNode<T>> = object: Iterator<SinglyLinkedNode<T>>{
        var currentNode: SinglyLinkedNode<T>? = null
        var nextNode = headNode
        override fun hasNext(): Boolean = nextNode != null
        override fun next(): SinglyLinkedNode<T> {
            currentNode = nextNode
            nextNode = nextNode!!.next
            return currentNode!!
        }
    }
    override fun addTo(newElement: T) = addNode(SinglyLinkedNode(newElement, null))
    override fun contains(toFind: T): Boolean{
        for (node in this){
            if (node.element == toFind){
                return true
            }
        }
        return false
        /* old version of contains
        if (headNode == null){
            return false
        } else{
            var currentNode = headNode
            while (currentNode!!.next != null){
                if (currentNode.element == toFind){
                    return true
                } else{
                    currentNode = currentNode.next
                }
            }
            return false
        }*/
    }
    fun remove(toRemove: T){
        if (!contains(toRemove)){
            throw UnsupportedOperationException("remove item not in list")
        }
        if (headNode!!.element == toRemove){
            headNode = headNode!!.next
        } else{
            var prevNode = headNode
            for (node in this){
                if (node.element == toRemove){
                    prevNode!!.next = node.next
                    break
                }
                prevNode = node
            }
        }
    }
    override fun toString(): String = buildString {
        append("[")
        for (node in this@SinglyLinkedList){
            append("${node.element}, ")
        }
        append("]")
    }
    fun getIndex(toFind: T): Int{
        if (!contains(toFind)){
            throw UnsupportedOperationException("remove item not in list")
        }
        var counter = 0
        for (node in this){
            if (node.element == toFind){
                break
            }
            else{
                counter ++
            }
        }
        return counter
    }
    private fun addNodeAt(newNode: SinglyLinkedNode<T>, addAtIndex: Int)= when {
        addAtIndex < 0 -> throw IndexOutOfBoundsException("add at negative index")
        addAtIndex == 0 -> {
            newNode.next = headNode
            headNode = newNode
        }
        else -> {
            var counter = 0
            var prevNode = headNode
            for (node in this){
                if (counter == addAtIndex){
                    newNode.next = node
                    prevNode!!.next = newNode
                    break
                }
                else{
                    prevNode = node
                    counter ++
                }
            }
        }


    }
    override fun addAt(newElement: T, addAtIndex: Int) = addNodeAt(SinglyLinkedNode(newElement), addAtIndex)
    override fun remove() = removeAt(size-1)
    override fun removeAt(removeIndex: Int){
        if (removeIndex >= size){
            throw IndexOutOfBoundsException("remove index greater than list")
        }
        var prevNode: SinglyLinkedNode<T>? = null
        var curNode = headNode
        for (i in 1..removeIndex){
            prevNode = curNode
            curNode = curNode!!.next
        }
        prevNode!!.next =curNode!!.next

    }

}


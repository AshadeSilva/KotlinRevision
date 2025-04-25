package main.kotlin

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

fun main() {
    // creating runnable objects
    val person1 = County("Aman")
    val person2 = County("Asha")

    // create Threads related to each object
    val thread1 = Thread(person1)
    val thread2 = Thread(person2)

    // invoke the run function
    thread1.start()
    thread2.start()

    // wait for the run function to finish
    thread1.join()
    thread2.join()

}

class Chatty(val name: String): Runnable {
    override fun run() {
        for (i in 0..5) {
            println("chatty object speaking: $name")
            Thread.sleep(500)
        }
    }
}

class County(val name: String):Runnable{
    private var counter = 0
    private val lock = ReentrantLock()
    override fun run() {
        for (i in 0..5){
            lock.withLock {
                counter++
                println("counter increased: $name, $counter")
            }
            Thread.sleep(1)
        }
    }
}
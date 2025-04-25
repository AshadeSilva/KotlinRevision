package main.kotlin

abstract class Shape(protected val height: Int,
    ){
    abstract val area: Int
}

open class Rectangle(protected val width: Int,
    iheight: Int): Shape(iheight){
        override val area
            get() = width * height
}

class Square(length: Int): Rectangle(length, length){
}

class Circle(iHeight: Int): Shape(iHeight){
    override val area: Int
        get() = 2 * Math.PI.toInt() * Math.pow((height.toDouble()/2.0), 2.0).toInt()
}

fun largestArea(vararg shapes: Shape) = shapes.maxBy { it.area }
// cannot be used on Shape's subclasses therefore

fun <T: Shape> largestArea(vararg shapes: T) = shapes.maxBy { it.area }
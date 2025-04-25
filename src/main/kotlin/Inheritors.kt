package main.kotlin

fun main() {
    val c = ClassWithInners()
    c.printInClassWithChange()
}

interface Interface{
    // default: public and open
    fun defaultFunction() = "default"                  // is a public fun
    public fun publicFunction(): String = "greetings"  // visible everywhere
    private fun privateFunction(): String = "hi"       // final +
                                             // only used inside interface definitions
    abstract fun abstractFunction(): String            // written in class
    open fun openFun(): String = "open"                // can be overwritten

    // protected fun protectedFunction(): String = "protected" is not allowed
    // final fun finalFunction() = "final" is not allowed
}

open class SuperClass(var stringValue: String): Interface {
    // from interface
    //override fun publicFunction(): Int = 3 type changes are not allowed
    override fun publicFunction(): String = "new public"
    // override private fun privateFunction()   private is final
    override fun abstractFunction(): String = "abstract"
    override fun openFun(): String = "open"
    // fun usingPrivate() = privateFunction()  is not allowed


    // default: public and final
    private fun printString(){
        println(stringValue)
    }    // can only be used in this class
    protected fun doubleString(){
        stringValue += stringValue
    } // can only be used in this class family
    public fun capsLockString(){
        stringValue = stringValue.uppercase()
    }  // can be used anywhere in the module
    public fun unCapsLockString(){
        stringValue = stringValue.lowercase()
    }

    final fun clearString(){
        stringValue = ""
    }                // cannot be overridden
    open fun spaceString() = buildString {
        for (char in stringValue){
            append("$char ")
        }
    }  // can be overridden in subclasses
    //abstract fun abstractFun()  is only allowed in abstract classes // must be implemented by a subclass
    // note extension methods cannot touch already-existing functions

}

class SubClass(var inputStringValue: String): SuperClass(inputStringValue){
    // practice with fields
    var property1: Int = 0
        set(newVal: Int){
            field = newVal
        }
    val property2: Int
        get() = property1

    // from interface: same rules as Superclass
    override fun publicFunction(): String = "new public"
    override fun abstractFunction(): String = "abstract"
    override fun openFun(): String = "open"

    /* none of these can be done because funs are default final
    override fun printString(){println("hello")}    // doesnt work
    override fun doubleString(){
        super.doubleString()
        super.doubleString()
    }
    override fun capsLockString() = super.unCapsLockString()
    override fun unCapsLockString() = super.capsLockString()
    */

    // final fun clearString(){
//        stringValue = ""
//    }          cannot override final functions
    override fun spaceString() = buildString {
        for (char in stringValue){
            append("$char ")
        }
    }  // can be overridden in subclasses

    // using super functions
    // fun reUsePrintString() = printString  cannot use private super functions
    fun reUseDoubleString() = doubleString()   // can use protected super functions
    fun reUseCapsLock() = capsLockString()     // can use public
    fun reUseSpaceString() = super.spaceString()  // use super to get original definition


}

fun SuperClass.spaceString() = " " // does not work, cannot use override
//but we can overload:
fun SuperClass.spaceString(num: Int) = " "


class ClassWithInners(){
    public var prop1 = "this is a public property of ClassWithInners"
    private val prop2 = "this is a private property of ClassWithInners"

    inner class InnerClass(){
        val prop3 = prop1  // these are a copy
        val prop4 = prop2
        val prop5 = "this is a public property of InnerClass"
        fun print(){
            println("printing from InnerClass: $prop1") // stays true to original property
            println("printing from InnerClass: $prop2")
            println("printing from InnerClass: $prop3") // copy - does not change with original
            println("printing from InnerClass: $prop4")
            println("printing from InnerClass: $prop5")
        }
    }
    fun printInnerClass(){
        val inner = InnerClass()
        inner.print()
    }

    fun printInClassWithChange(){
        val inner = InnerClass()
        inner.print()
        prop1 = "this property has been changed"
        inner.print()  // does update
    }

    class InClass(){
//        val prop3 = prop1
//        val prop4 = prop2 do not work
        val prop5 = "this is a public property of InClass"
        fun print(){
            println("printing from InClass: $prop5")
        }
    }

}
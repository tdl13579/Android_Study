package com.android.myapplication.kotlinStudy

data class Stu(val name: String = "", val age: Int = 0)
class Stu2(var name: String,var age: Int){
    operator fun component1() = name // 需要这样声明才可以用作结构赋值
    operator fun component2() = age
}
// 密封类
sealed class Bar
class Test(var name: String) : Bar()
class Test2(var age: Int) :Bar()
// 嵌套类
class Test3{
    var name = "Tom"
    class Nested{
        fun foo() = 2 // Test3.Nested().foo()
    }
    // 内部类
    inner class InnerTest4{
        fun foo() = name // Test3().InnerTest4.foo()
    }
}

enum class Color {
    RED, BLACK, BLUE, GREEN, WHITE
}
enum class Color2(var color:String){
    Red("red"),
    Blue("blue")
}
// 构造函数
class Person constructor(val name: String,val age: Int){
    val hobby = "爱好"
    fun sayHobby(){
        println("$hobby")
    }
    init {
        println("name is $name")
    }
    constructor(name: String,   age: Int,hello: String) : this(name,age){
        println("$hello")
    }
    inner class InnerPerson{
        fun innerHobby(){
            println("$hobby")
        }
    }
}



fun main() {
    /**
     * 基本数据类型
     * 数字：Byte(8--(-128,127)) Short(16) Int(32) Long(64) 浮点数：Float(32) Double(64)
     * 字符：Char (不能直接当做数字用 'a' 表示) 布尔： true/false  数组：Array 字符串：String
     * */
    for (item in 1..9 step 2) {
        print(item) // 13579
    }
    println("-----------------")
    for (item in 9 downTo 1 step 3) {
        print(item) // 963
    }
    println("-----------------")
    // 过滤 filter
    val arr: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6)
//    var filterArr = arr.filter{it -> it>2}
    var filterArr = arr.filter { it > 2 }
    println(filterArr) // [3, 4, 5, 6]
    var a = 1
    var b = 2
    a = b.also { b = a }
    println("$a--$b") // 2 -- 1
    // copy函数
    val jack = Stu(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)
    println("$olderJack -- $jack") // Stu(name=Jack, age=2) -- Stu(name=Jack, age=1)
    var (name, age) = olderJack
    println("$name -- $age") // Jack -- 2


    var color: Color = Color.BLUE

    println(Color.values())
    println(Color.valueOf("RED")) // RED
    println(color.name) // BLUE
    println(color.ordinal) // 2
    var (x,y) = Stu("Mike",2) //数据类直接解构赋值
    var (m,n) = Stu2("Jerry",3) // 不是数据类不支持直接解构复制，需要用component1,2 这样按顺序来声明
    println("$x--$y") // Mike--2
    println("$m--$n") // Jerry--3

    var list = mutableListOf("hello","world",1,2)
    for(item in list){
        println(item)
    }
    for(index in list.indices){
        println("$index -- ${list[index]}")
    }
    var map = mutableMapOf<String,Any>("hello" to "你好","world" to "世界","1" to 1)
    for ((key,value) in map){
        println("$key -- $value")
    }
    fun String.hello():String{
        return "hello"
    }
    println("str".hello())


}

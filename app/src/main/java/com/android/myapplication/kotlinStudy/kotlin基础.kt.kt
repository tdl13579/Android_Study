package com.android.myapplication.kotlinStudy

class User():A{
    override fun hello(): String {
        return  "重写hello方法"
    }
}
fun User.Hello(string: String):String{
    return string + "world"
}
interface A{
    fun hello():String{
        return "hello"
    }
}

fun main(){
    /**
     * 测试题：https://www.ucloud.cn/yun/8818.html
     *
     * 基本数据类型：Byte,Short, Int ,Long, Float,Double ||||  String Boolean
     * 访问控制符：默认是 public（全局可见） protected（当前类和子类可见）  private（仅当前文件可见） internal(仅在当前模块可见)
     * 创建匿名内部类：inner class
     * 扩展方法：String.Hello(){}
     * 14. 编辑错误 kotlin 严格区分可变和不可变集合 使用listOf声明 的是一个不可变集合 ，不能使用add这些方法
     * 15. 编辑错误 java访问kotlin端需要使用@jvmStatic 关键字声明 或者通过A.INSTANCE.init() 来访问
     * 16. 返回一个表达式 kotlin.Unit
     * 17. false 空指针异常
     * 18. null a 没有被提前声明
     * 19. true false
     * 20.
     * */
    fun String.hello():String{
        return String() + "hello"
    }
    println(User().Hello("hello ")) // hello world
    var s = "hello---"
    println( s.hello()) // hello
    println( User().hello()) // 重写hello方法
    // 接口实现
    fun sum(a:Int,b:Int){
        a+b
    }
    println(sum(1,2)) //  kotlin.Unit
    var student = Student("jerry",1)
    println(student.a)
}

class Student constructor(name:String){
    var a = "hello"
    init {
        println("$a--$name")
    }
    constructor(name: String,age:Int) : this(name) {
        println("$name--$age")
    }
    companion object {

    }
}

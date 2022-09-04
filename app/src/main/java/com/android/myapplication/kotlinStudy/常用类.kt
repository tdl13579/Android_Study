package com.android.myapplication.kotlinStudy

data class Hello(var x: Int, var y: Int) {
    val isBounds = x > 0 && y > 0
}

fun main() {
    println(Hello(10, 20)) // Hello(x=10, y=20)
    // == 是比较内容 === 是比较引用地址
    // 可变长参数函数
    fun vars(vararg v: Int) {
        for (item in v) {
            print("$item -- ") // 1 -- 2 -- 3 -- 4 -- 5 --
        }
    }
    vars(1, 2, 3, 4, 5)
    // 匿名函数
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }
    sumLambda(1, 3)
    // 区间
    for (i in 1..4) print(i) // 输出“1234”
    for (i in 4..1) print(i) // 什么都不输出
    // 使用 step 指定步长
    for (i in 1..4 step 2) print(i) // 输出“13”
    // downTo 从4到1
    for (i in 4 downTo 1 step 2) print(i) // 输出“42”
    // 使用 until 函数排除结束元素 不包括最后一项
    for (i in 1 until 10) {   // i in [1, 10) 排除了 10
        println(i)
    }
    val text = """
    |多行字符串
    |菜鸟教程
    |多行字符串
    |Runoob
    """.trimMargin()
    println(text)    // 前置空格删除了
    //    String 可以通过 trimMargin() 方法来删除多余的空白。
    // 作为表达式
    val a = 1
    val b = 2
    val max = if (a > b) a else b
    println(max)   // 2
    //    使用 in 运算符来检测某个数字是否在指定区间内
    if (a in 1..5) {
        println("在1到5区间内")
    }
    // when 表达式
    //    如果很多分支需要用相同的方式处理，则可以把多个分支条件放在一起，用逗号分隔：
    when (a) {
        0, 1 -> println("a == 0 or a == 1")
        else -> print("otherwise")
    }
    // 通过索引遍历
    val array = arrayListOf<String>("hello", "world")
    for (i in array.indices) {
        println(array[i])
    }
    //    do…while 循环 对于 while 语句而言，如果不满足条件，则不能进入循环。但有时候我们需要即使不满足条件，也至少执行一次。
    //    do…while 循环和 while 循环相似，不同的是，do…while 循环至少会执行一次。



    val demo = Outer().Inner().foo()
    println(demo) //   1
    val demo2 = Outer().Inner().innerTest()
    println(demo2)   // 内部类可以引用外部类的成员，例如：成员属性

}

//    内部类使用 inner 关键字来表示。内部类会带有一个对外部类的对象的引用，所以内部类可以访问外部类成员属性和成员函数。
// 类似于前端的闭包，内部可以访问到外部的变量
class Outer {
    private val bar: Int = 1
    var v = "成员属性"

    /**嵌套内部类**/
    inner class Inner {
        fun foo() = bar  // 访问外部类成员
        fun innerTest() {
            var o = this@Outer //获取外部类的成员变量
            println("内部类可以引用外部类的成员，例如：" + o.v)
        }
    }
}
// 抽象类
open class Base {
    open fun f() {}
}

abstract class Derived : Base() {
    abstract override fun f()
}

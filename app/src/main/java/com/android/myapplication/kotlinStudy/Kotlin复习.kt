package com.android.myapplication.kotlinStudy

data class Stu(val name: String = "", val age: Int = 0)
enum class Color {
    RED, BLACK, BLUE, GREEN, WHITE
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



}

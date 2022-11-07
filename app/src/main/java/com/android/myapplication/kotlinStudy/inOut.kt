package com.android.myapplication.kotlinStudy

// out (协变) 子类泛型对象可以给父类泛型对象赋值
interface Product<out T>{
    fun product():T
}
// in (逆变) 父类泛型对象可以给子类泛型对象赋值
interface Consume<in T>{
    fun consume(item:T)
}

// 不变
interface ProductConsume<T>{
    fun product():T
    fun consume(item:T)
}

fun main(){

}
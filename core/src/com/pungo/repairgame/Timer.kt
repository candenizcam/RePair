package com.pungo.repairgame

class Timer(private val time: Long) {
    private var startTime = 0L

    fun go(){
        startTime = System.currentTimeMillis()
    }

    fun done(): Boolean{
        val nowTime = System.currentTimeMillis() - startTime
        if(nowTime < time){
            return false
        }
        return true
    }

    fun now(): Long{
        return System.currentTimeMillis() - startTime
    }
}
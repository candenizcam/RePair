package com.pungo.repairgame

class Timer(private var time: Long) {
    private var startTime = 0L
    var running = false
    private var pauseTime =0L

    fun go(){
        startTime = System.currentTimeMillis()
        running = true
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

    fun timeLeft():Long{
        return time - (System.currentTimeMillis() - startTime)
    }

    fun pause(){
        pauseTime = now()
    }

    fun resume(){
        startTime = System.currentTimeMillis() - pauseTime
    }
}
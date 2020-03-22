package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Timer
import com.pungo.repairgame.SharedVariables as sv
import com.pungo.repairgame.gamescreen.devices.BigMonitorStatus

class BigMonitor {
    private var planetSprite : Sprite
    private var monitorSideSprite : Sprite
    //private val countdownList: List<String>
    private val countdownSprites: List<Sprite>
    private val p0Sprite =  sv.loadSprite("graphics/planets/p0.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val p1Sprite =  sv.loadSprite("graphics/planets/p1.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val p2Sprite =  sv.loadSprite("graphics/planets/p2.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val p3Sprite =  sv.loadSprite("graphics/planets/p3.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val p4Sprite =  sv.loadSprite("graphics/planets/p4.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val p5Sprite =  sv.loadSprite("graphics/planets/p5.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val psSprite =  sv.loadSprite("graphics/planets/ps.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val showAndTellSprite = sv.loadSprite("graphics/bigmonitor/show&tell.png", sv.monitorRatio).apply{setCenter(sv.monitorCentreX,sv.monitorCentreY)}
    private val hyperspaceSprites: List<Sprite>
    private val countdownTimer = Timer(1000)                // countdown timer
    private val hyperspaceTimer = Timer(100)                // countdown timer
    private var countdownIndex = 0
    private var hyperspaceIndex = 0
    var status = BigMonitorStatus.P0

    init{
        planetSprite = sv.loadSprite(sv.monitorPath, sv.monitorRatio).apply {
            setCenter(sv.monitorCentreX,sv.monitorCentreY)
        }
        monitorSideSprite = sv.loadSprite(sv.monitorFramePath, sv.monitorRatio).apply{
            setCenter(sv.monitorCentreX,sv.monitorCentreY)
        }
        var m = mutableListOf<Sprite>()
        listOf("graphics/bigmonitor/three.png", "graphics/bigmonitor/two.png", "graphics/bigmonitor/one.png", "graphics/bigmonitor/go.png").forEach {
            m.add(sv.loadSprite(it, sv.monitorRatio).apply{
                setCenter(sv.monitorCentreX,sv.monitorCentreY)
            })
        }
        countdownSprites = m
        var m2 = mutableListOf<Sprite>()
        for (i in 12 downTo 1){
            m2.add(sv.loadSprite("graphics/bigmonitor/anim/$i.png", sv.monitorRatio).apply{
                setCenter(sv.monitorCentreX,sv.monitorCentreY)
            })
        }
        hyperspaceSprites = m2

    }

    fun activateCountdown(){
        countdownTimer.go()
        status = BigMonitorStatus.Countdown
        countdownIndex = 0
        hyperspaceIndex = 0
    }

    fun draw(batch: SpriteBatch){
        when (status){
            BigMonitorStatus.P0 -> p0Sprite.draw(batch)
            BigMonitorStatus.P1 -> p1Sprite.draw(batch)
            BigMonitorStatus.P2 -> p2Sprite.draw(batch)
            BigMonitorStatus.P3 -> p3Sprite.draw(batch)
            BigMonitorStatus.P4 -> p4Sprite.draw(batch)
            BigMonitorStatus.P5 -> p5Sprite.draw(batch)
            BigMonitorStatus.Ps -> psSprite.draw(batch)
            BigMonitorStatus.Guide -> showAndTellSprite.draw(batch)
            BigMonitorStatus.Countdown -> countdownSprites[countdownIndex].draw(batch)
            BigMonitorStatus.Hyperspace -> hyperspaceSprites[hyperspaceIndex].draw(batch)
        }
        //planetSprite.draw(batch)
        monitorSideSprite.draw(batch)
    }

    fun loopAction(): Int {
        if(status==BigMonitorStatus.Countdown){
            if (countdownTimer.done()){
                countdownIndex += 1
                if (countdownIndex>=4){
                    countdownIndex = 0
                    hyperspaceIndex = 0
                    hyperspaceTimer.go()
                    status = BigMonitorStatus.Hyperspace
                } else {
                    countdownTimer.go()
                }

            }
        }
        if(status==BigMonitorStatus.Hyperspace){
            if (hyperspaceTimer.done()){
                hyperspaceIndex += 1
                if (hyperspaceIndex>=12){
                    hyperspaceIndex = 0
                    status = BigMonitorStatus.Guide
                    return 1
                } else {
                    hyperspaceTimer.go()
                }
            }
        }
        return 0
    }

    /*
    fun changeMonitor(path: String){
        planetSprite = sv.loadSprite(path, sv.monitorRatio)
        planetSprite.setCenterX(sv.monitorCentreX)
        planetSprite.setCenterY(sv.monitorCentreY)
    }

     */

}
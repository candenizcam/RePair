package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.*
import com.pungo.repairgame.SharedVariables.contains

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var leftestDevice: SimpleDevice
    private lateinit var iceTool: SimpleTool
    private lateinit var phText: TextIsland
    private lateinit var incomingText: IncomingText
    private val timer = Timer(10000)

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        leftestDevice.draw(batch)
        iceTool.draw(batch)
        incomingText.draw(batch,"Pak men herosneri, nuj verser volmeri\nSerserki na nanki henk a senq\nHay Hay",517f,453f,885f,180f)
    }

    override fun firstPress() {
        if (contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), iceTool.chosenSprite)) {
            iceTool.flying = true
        }
    }

    override fun pressing() {
        if(iceTool.flying){
            iceTool.flyingCentre(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        }
    }

    override fun released() {
        if (iceTool.flying){
            iceTool.flying = false
            if (contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), leftestDevice.chosenSprite)) {
                leftestDevice.status = DeviceStatus.NORMAL
            }

        }

    }

    override fun loopAction() {
        if (timer.done()) {
            leftestDevice.status = DeviceStatus.HOT
            timer.go()
        }

        if (contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), iceTool.chosenSprite)) {
            iceTool.status = ToolStatus.GLOW
        } else {
            iceTool.status = ToolStatus.IDLE
        }
    }

    override fun lateInitializer(){
        mainSprite = SharedVariables.loadSprite(SharedVariables.gameBackgroundPath, SharedVariables.gameBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)
        leftestDevice = SimpleDevice("graphics/placeholder_leftest", 0.25f)
        leftestDevice.relocateCentre(240f,410f)
        iceTool = SimpleTool("graphics/placeholder_tool", ratio = 0.25f)
        iceTool.relocateCentre(200f,900f)
        phText = TextIsland(Gdx.files.internal("planet_0/story.json"))
        incomingText = IncomingText()
        timer.go()
    }
}
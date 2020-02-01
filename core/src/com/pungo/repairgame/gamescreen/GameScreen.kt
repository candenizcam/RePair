package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.TextIsland
import com.pungo.repairgame.Timer

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var leftestDevice: SimpleDevice
    private lateinit var iceTool: SimpleTool
    private lateinit var phText: TextIsland
    private val timer = Timer(10000)
    private val textTimer = Timer(3000)

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        leftestDevice.draw(batch)
        iceTool.draw(batch)
    }

    override fun firstPress() {
        if (iceTool.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())){
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
            if (leftestDevice.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())){
                leftestDevice.status = DeviceStatus.NORMAL
            }

        }

    }

    override fun loopAction() {
        if (timer.done()) {
            leftestDevice.status = DeviceStatus.HOT
            timer.go()
        }

        if (iceTool.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())) {
            iceTool.status = ToolStatus.GLOW
        } else {
            iceTool.status = ToolStatus.IDLE
        }
        phText = TextIsland(Gdx.files.internal("planet_0/story.3json"))

        if (textTimer.done()) {
            println(phText.getCurrentLine())
            phText.nextPassage(1)
            textTimer.go()
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
        timer.go()
        textTimer.go()
    }

}
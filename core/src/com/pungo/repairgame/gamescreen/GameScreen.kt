package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.*

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var leftestDevice: SimpleDevice
    private lateinit var iceTool: SimpleTool
    private lateinit var phText: TextIsland
    private lateinit var incomingText: TextIslandTexts
    private lateinit var aText: TextIslandTexts
    private lateinit var bText: TextIslandTexts
    private lateinit var cText: TextIslandTexts

    private val timer = Timer(10000)

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        leftestDevice.draw(batch)
        iceTool.draw(batch)
        incomingText.draw(batch, true)
        if (incomingText.revealed){
            phText.getCurrentChoices().let{
                aText.draw(batch)
                bText.draw(batch)
                cText.draw(batch)
            }
        }
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
        if(timer.done()){
            leftestDevice.status = DeviceStatus.HOT
            timer.go()
        }

        if (iceTool.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())){
            iceTool.status = ToolStatus.GLOW
        } else {
            iceTool.status = ToolStatus.IDLE
        }


        aText.hovered = aText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        bText.hovered = bText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        cText.hovered = cText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())






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
        incomingText = TextIslandTexts().apply{
            setStuff(phText.getCurrentLine(),517f,453f,865f,180f)
        }
        phText.getCurrentChoices().let{
            aText = TextIslandTexts().apply {
                setStuff(it[0],250f,250f,1250f,65f)
            }
            bText = TextIslandTexts().apply {
                setStuff(it[1],250f,185f,1250f,65f)
            }
            cText = TextIslandTexts().apply {
                setStuff(it[2],250f,120f,1250f,65f)
            }
        }
        timer.go()
    }

}
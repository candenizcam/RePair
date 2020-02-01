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
    private lateinit var bigMonitor: BigMonitor

    private val travelTimer = Timer(200)
    private val timer = Timer(5000)

    override fun draw(batch: SpriteBatch) {

        mainSprite.draw(batch)
        bigMonitor.draw(batch)
        leftestDevice.draw(batch)
        iceTool.draw(batch)
        incomingText.draw(batch, true)
        if (incomingText.revealed && phText.sceneNotOver()) {
            phText.getCurrentChoices().let {
                aText.draw(batch)
                bText.draw(batch)
                cText.draw(batch)
            }
        }
    }

    override fun firstPress() {
        if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), iceTool.chosenSprite)) {
            iceTool.flying = true
        }

        when {
            aText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) -> {
                aText.pressing = true
            }
            bText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) -> {
                bText.pressing = true
            }
            cText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) -> {
                cText.pressing = true
            }
        }
    }

    override fun pressing() {
        if(iceTool.flying){
            val flyingX = Gdx.input.x.toFloat()/Gdx.graphics.width*SharedVariables.mainWidth
            val flyingY = Gdx.input.y.toFloat()/Gdx.graphics.height*SharedVariables.mainHeight
            iceTool.flyingCentre(flyingX,flyingY)
        }
    }

    override fun released() {
        if (iceTool.flying) {
            iceTool.flying = false
            if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), leftestDevice.chosenSprite)) {
                leftestDevice.status = DeviceStatus.NORMAL
            }
        }
        when {
            (aText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) && (aText.pressing)) -> {
                phText.nextPassage(1)
                updateIslandText()
            }
            (bText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) && (bText.pressing)) -> {
                phText.nextPassage(2)
                updateIslandText()
            }
            (cText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) && (cText.pressing)) -> {
                phText.nextPassage(3)
                updateIslandText()
            }

        }
        aText.pressing = false
        bText.pressing = false
        cText.pressing = false



    }

    private fun updateIslandText() {
        incomingText.setStuff(phText.getCurrentLine())
        incomingText.letterRevealReset()

        try {
            phText.getCurrentChoices().let {
                aText.setStuff(it[0])
                bText.setStuff(it[1])
                cText.setStuff(it[2])
            }
        } catch (ex: Exception) {

        }
    }

    fun redButton() {
        travelTimer.go()
        travelTimer.running = true
    }

    private fun zar(): Boolean {
        val rng = (0..10).random()
        if (rng < 5) return true
        return false
    }

    private fun breakShip() {
        val device = when ((0..3).random()) {
            0 -> leftestDevice
            1 -> leftestDevice
            2 -> leftestDevice
            3 -> leftestDevice
            else -> leftestDevice
        }

        if (device.status == DeviceStatus.NORMAL) {
            device.status = when ((0..1).random()) {
                0 -> DeviceStatus.HOT
                else -> DeviceStatus.BROKEN
            }
        }
    }

    override fun loopAction() {
        if (travelTimer.running) {
            if (travelTimer.now() < 20000 && timer.done()) {
                if (zar()) {
                    breakShip()
                }
                println("Mouse : ${Gdx.input.x} ${Gdx.input.y} ")
                println("Text top left : ${aText.top} ${aText.left} ")
                println("Text sizes : ${aText.modifiedHeight} ${aText.modifiedWidth} ")

                timer.go()
            } else if (travelTimer.done()) {
                travelTimer.running = false
                changePlanet()
            }
        }

        if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), iceTool.chosenSprite)) {
            iceTool.status = ToolStatus.GLOW
        } else {
            iceTool.status = ToolStatus.IDLE
        }

        aText.hovered = aText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        bText.hovered = bText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        cText.hovered = cText.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())

    }

    private fun changePlanet() {
        if (SharedVariables.planetIndex < 2)
            SharedVariables.planetIndex++
        phText.getPlanetPassage(SharedVariables.planets[SharedVariables.planetIndex].second)
        incomingText.letterRevealReset()
        incomingText.setStuff(phText.getCurrentLine())
    }

    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.gameBackgroundPath, SharedVariables.gameBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        leftestDevice = SimpleDevice("graphics/placeholder_leftest", 0.25f)
        leftestDevice.relocateCentre(240f, 410f)
        iceTool = SimpleTool("graphics/placeholder_tool", ratio = 0.25f)
        iceTool.relocateCentre(200f, 900f)
        phText = TextIsland(Gdx.files.internal("planet_0/story.json"), SharedVariables.planets[0].second)
        incomingText = TextIslandTexts().apply {
            setStuff(phText.getCurrentLine(), 517f, 453f, 865f, 180f)
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
        bigMonitor = BigMonitor()
        timer.go()
    }
}
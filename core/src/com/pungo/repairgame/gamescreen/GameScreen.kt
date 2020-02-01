package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.*

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var phText: TextIsland
    private lateinit var bigMonitor: BigMonitor
    private var devices = listOf<SimpleDevice>()
    private var tools = listOf<SimpleTool>()
    private var texts = mutableListOf<TextIslandTexts>()
    private val travelTimer = Timer(60000)
    private val timer = Timer(500)

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        bigMonitor.draw(batch)
        devices.forEach {
            it.draw(batch)
        }
        tools.forEach {
            it.draw(batch)
        }
        texts[0].draw(batch, true)
        if (texts[0].revealed && phText.sceneNotOver()) {
            for (k in 1..3) {texts[k].draw(batch)}
        }
    }

    override fun firstPress() {
        tools.forEach {
            if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it.chosenSprite)) {
                it.flying = true
            }
        }
        for (k in 1..3) {
            texts[k].pressing = texts[k].contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        }
    }

    override fun pressing() {
        tools.forEach {
            if(it.flying){
                val flyingX = Gdx.input.x.toFloat()/Gdx.graphics.width*SharedVariables.mainWidth
                val flyingY = Gdx.input.y.toFloat()/Gdx.graphics.height*SharedVariables.mainHeight
                it.flyingCentre(flyingX,flyingY)
            }
        }
    }

    override fun released() {
        tools.forEachIndexed {index,it ->
            if (it.flying) {
                it.flying = false
                devices.forEach {it2->
                    if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it2.getSprite())) {
                        if ((it2.status == DeviceStatus.HOT) && (index==1) ) it2.status = DeviceStatus.NORMAL
                        else if ((it2.status == DeviceStatus.BROKEN) && (index==2) ) it2.status = DeviceStatus.NORMAL
                        else if ((it2.status == DeviceStatus.STUCK) && (index==3) ) it2.status = DeviceStatus.NORMAL
                    }
                }
            }
        }

        for (k in 1..3) {
            if (texts[k].contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) && (texts[k].pressing)){
                phText.nextPassage(k)
                updateIslandText()
            }
            texts[k].pressing = false
        }
    }

    private fun updateIslandText() {
        texts[0].setStuff(phText.getCurrentLine())
        texts[0].letterRevealReset()

        try {
            phText.getCurrentChoices().let {
                texts[1].setStuff(it[0])
                texts[2].setStuff(it[1])
                texts[3].setStuff(it[2])
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
        if (rng < 9) return true
        return false
    }

    private fun breakShip() {
        val device = when ((0..3).random()) {
            0 -> devices[0]
            1 -> devices[1]
            2 -> devices[2]
            else -> devices[3]
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
                timer.go()
            } else if (travelTimer.done()) {
                travelTimer.running = false
                changePlanet()
            }
        }

        tools.forEach {
            if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it.chosenSprite)) {
                it.status = ToolStatus.GLOW
            } else {
                it.status = ToolStatus.IDLE
            }
        }

        for (k in 1..3) {
            texts[k].hovered = texts[k].contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        }

    }

    private fun changePlanet() {
        if (SharedVariables.planetIndex < 2)
            SharedVariables.planetIndex++
        phText.getPlanetPassage(SharedVariables.planets[SharedVariables.planetIndex].second)
        texts[0].letterRevealReset()
        texts[0].setStuff(phText.getCurrentLine())
    }

    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.gameBackgroundPath, SharedVariables.gameBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        devices = DevicesData.getDevices()
        tools = ToolsData.getTools()




        phText = TextIsland(Gdx.files.internal("planet_0/story.json"), SharedVariables.planets[0].second)
        TextIslandTexts().also {
            it.setStuff(phText.getCurrentLine(), 517f, 453f, 865f, 180f)
            texts.add(it)
        }
        phText.getCurrentChoices().let{
            TextIslandTexts().apply {
                setStuff(it[0],250f,250f,1250f,65f)
                texts.add(this)
            }
            TextIslandTexts().apply {
                setStuff(it[1],250f,185f,1250f,65f)
                texts.add(this)
            }
            TextIslandTexts().apply {
                setStuff(it[2],250f,120f,1250f,65f)
                texts.add(this)
            }
        }
        bigMonitor = BigMonitor()
        timer.go()
    }
}
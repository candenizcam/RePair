package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.*

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var redButton: SimpleDevice
    private var devices = mutableListOf<SimpleDevice>()
    private lateinit var iceTool: SimpleTool
    private lateinit var phText: TextIsland
    private lateinit var incomingText: TextIslandTexts
    private lateinit var aText: TextIslandTexts
    private lateinit var bText: TextIslandTexts
    private lateinit var cText: TextIslandTexts
    private lateinit var bigMonitor: BigMonitor
    private val items: MutableList<String> = mutableListOf()

    private val travelTimer = Timer(20000)
    private val timer = Timer(5000)

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        bigMonitor.draw(batch)
        devices.forEach {
            it.draw(batch)
        }
        iceTool.draw(batch)
        redButton.draw(batch)
        incomingText.draw(batch, true)
        if (incomingText.revealed && phText.sceneNotOver()) {
            aText.draw(batch)
            bText.draw(batch)
            cText.draw(batch)
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
            devices.forEach {
                if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it.chosenSprite)) {
                    it.status = DeviceStatus.NORMAL
                }
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

        if (!travelTimer.running && !phText.sceneNotOver()) {
            if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), redButton.chosenSprite)) {
                redButton()
            }
        }
        aText.pressing = false
        bText.pressing = false
        cText.pressing = false
    }

    private fun updateIslandText() {
        incomingText.setStuff(phText.getCurrentLine())
        incomingText.letterRevealReset()

        if (phText.sceneNotOver()) {
            phText.getCurrentChoices().let {
                aText.setStuff(it[0])
                bText.setStuff(it[1])
                cText.setStuff(it[2])
            }
        }
    }

    private fun redButton() {
        travelTimer.go()
        travelTimer.running = true
        incomingText.setStuff("")
        incomingText.letterRevealReset()
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
        when (SharedVariables.planetIndex) {
            0 -> {
                if (devices[1].status != DeviceStatus.NORMAL) { // speaker
                    phText.getPlanetPassage(27)
                } else if (devices[3].status != DeviceStatus.NORMAL) { //translator
                    phText.getPlanetPassage(16)
                } else {
                    phText.getPlanetPassage(7)
                }
            }
            1 -> {
                if (devices[1].status != DeviceStatus.NORMAL) {
                    phText.getPlanetPassage(115)
                } else {
                    phText.getPlanetPassage(96)
                }
            }
            2 -> {
                if (devices[1].status != DeviceStatus.NORMAL) { // speaker
                    phText.getPlanetPassage(64)
                } else if (devices[3].status != DeviceStatus.NORMAL) { //translator
                    phText.getPlanetPassage(3)
                } else {
                    phText.getPlanetPassage(39)
                }
            }
            3 -> {
                if (items.isEmpty()) {
                    phText.getPlanetPassage(136)
                } else if ("stacey" in items && "dessert" !in items) {
                    phText.getPlanetPassage(137)
                } else if ("stacey" !in items && "dessert" in items) {
                    phText.getPlanetPassage(138)
                } else if ("stacey" in items && "dessert" in items) {
                    phText.getPlanetPassage(139)
                }
            }
        }

        SharedVariables.planetIndex++
        updateIslandText()
    }

    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.gameBackgroundPath, SharedVariables.gameBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        redButton = SimpleDevice(DevicesData.redPath, DevicesData.redRatio)
        redButton.relocateCentre(DevicesData.redX, DevicesData.redY)
        SimpleDevice(DevicesData.micPath, DevicesData.micRatio).also {
            it.relocateCentre(DevicesData.micX, DevicesData.micY)
            devices.add(it)
        }
        SimpleDevice(DevicesData.spePath, DevicesData.speRatio).also {
            it.relocateCentre(DevicesData.speX, DevicesData.speY)
            devices.add(it)
        }
        SimpleDevice(DevicesData.disPath, DevicesData.disRatio).also {
            it.relocateCentre(DevicesData.disX, DevicesData.disY)
            devices.add(it)
        }
        SimpleDevice(DevicesData.traPath, DevicesData.traRatio).also{
            it.relocateCentre(DevicesData.traX,DevicesData.traY)
            devices.add(it)
        }
        iceTool = SimpleTool("graphics/placeholder_tool", ratio = 0.25f)
        iceTool.relocateCentre(200f, 900f)
        phText = TextIsland(Gdx.files.internal("planet_0/story.json"), 1)
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
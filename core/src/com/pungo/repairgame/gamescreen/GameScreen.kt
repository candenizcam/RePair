package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.*

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var redButton: SimpleDevice
    private lateinit var iceTool: SimpleTool
    private lateinit var phText: TextIsland
    private lateinit var bigMonitor: BigMonitor
    private val items: MutableList<String> = mutableListOf()
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
        iceTool.draw(batch)
        redButton.draw(batch)
        incomingText.draw(batch, true)
        if (incomingText.revealed && phText.sceneNotOver()) {
            aText.draw(batch)
            bText.draw(batch)
            cText.draw(batch)
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
        tools.forEachIndexed {index,it ->
            if (it.flying) {
                it.flying = false
                devices.forEach {it2->
                    if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it2.chosenSprite)) {
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
        }

        if (!travelTimer.running && !phText.sceneNotOver()) {
            if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), redButton.chosenSprite)) {
                redButton()
            }
            texts[k].pressing = false
        }
        aText.pressing = false
        bText.pressing = false
        cText.pressing = false
    }

    private fun updateIslandText() {
        texts[0].setStuff(phText.getCurrentLine())
        texts[0].letterRevealReset()

        if (phText.sceneNotOver()) {
            phText.getCurrentChoices().let {
                texts[1].setStuff(it[0])
                texts[2].setStuff(it[1])
                texts[3].setStuff(it[2])
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
        devices = DevicesData.getDevices()
        tools = ToolsData.getTools()




        phText = TextIsland(Gdx.files.internal("planet_0/story.json"), SharedVariables.planets[0].second)
        TextIslandTexts().also {
            it.setStuff(phText.getCurrentLine(), 517f, 453f, 865f, 180f)
            texts.add(it)
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
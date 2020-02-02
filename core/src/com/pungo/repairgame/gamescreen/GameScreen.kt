package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.*

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var cargo : Sprite
    private lateinit var phText: TextIsland
    private lateinit var bigMonitor: BigMonitor
    private lateinit var redButton: SimpleButton
    private var devices = listOf<SimpleDevice>()
    private var tools = listOf<SimpleTool>()
    private var items = mutableListOf<String>()
    private var texts = mutableListOf<TextIslandTexts>()
    private val travelTimer = Timer(20000)
    private val timer = Timer(1000)
    private val countdownTimer = Timer(1000)
    private var sfxBeep = Gdx.audio.newSound(Gdx.files.internal("sound/Beep.mp3"))
    private var sfxTake = Gdx.audio.newSound(Gdx.files.internal("sound/Take.mp3"))
    private var sfxFail = Gdx.audio.newSound(Gdx.files.internal("sound/Fail.mp3"))
    private var sfxChoose = Gdx.audio.newSound(Gdx.files.internal("sound/Choose.mp3"))
    private var sfxRed = Gdx.audio.newSound(Gdx.files.internal("sound/Red.mp3"))
    private var breakingList = listOf(0)

    private var countdownIndex = -1
    private val countdownList: List<String> = listOf("graphics/three.png", "graphics/two.png", "graphics/one.png", "graphics/go.png")

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        bigMonitor.draw(batch)
        devices.forEach {
            it.draw(batch)
        }
        tools.forEach {
            it.draw(batch)
        }
        redButton.draw(batch)
        texts[0].draw(batch, true)

        if (texts[0].revealed && phText.sceneNotOver()) {
            for (k in 1..3) {texts[k].draw(batch)}
        }
    }

    override fun firstPress() {
        tools.forEach {
            if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it.getSprite())) {
                it.flying = true
                sfxTake.play()
            }
        }
        if(SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), redButton.activeSprite)) {
            redButton.status = ButtonStatus.DOWN
        }
        for (k in 1..3) {
            texts[k].pressing = texts[k].contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
            sfxChoose.play()
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
        if(redButton.status == ButtonStatus.DOWN && !SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), redButton.activeSprite)) redButton.status = ButtonStatus.UP
    }

    override fun released() {
        tools.forEachIndexed { index, it ->
            if (it.flying) {
                it.flying = false
                devices.forEach { it2 ->
                    if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it2.getSprite())) {
                        if ((it2.status == DeviceStatus.SHORT) && (index == 0)) {
                            it.sfx.play()
                            it2.status = DeviceStatus.NORMAL
                        }
                        else if ((it2.status == DeviceStatus.HOT) && (index == 1)) {
                            it.sfx.play()
                            it2.status = DeviceStatus.NORMAL
                        }
                        else if ((it2.status == DeviceStatus.BROKEN) && (index == 2)){
                            it.sfx.play()
                            it2.status = DeviceStatus.NORMAL
                        }
                        else if ((it2.status == DeviceStatus.STUCK) && (index == 3)) {
                            it.sfx.play()
                            it2.status = DeviceStatus.NORMAL
                        }
                        else sfxFail.play()
                    }
                }
                it.flyingCentre(-500f,-500f)
            }
        }
        for (k in 1..3) {
            if (texts[k].contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat()) && (texts[k].pressing && phText.sceneNotOver())){
                phText.nextPassage(k)
                updateIslandText()
            }
            texts[k].pressing = false
        }

        if (!travelTimer.running && !phText.sceneNotOver()) {
            if(SharedVariables.planetIndex==1){
                tools.forEach {
                    it.status = ToolStatus.IDLE
                }
                breakingList = listOf(0,1,2,3)
            }
            if(redButton.status == ButtonStatus.DOWN) {
                if(SharedVariables.planetIndex==4){
                    SharedVariables.activeScreen = SharedVariables.endingScreen
                }
                sfxRed.play()
                redButton()
            }
        }
        redButton.status = ButtonStatus.UP
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

    private fun redButton() {
        countdownTimer.go()
        countdownTimer.running = true
        texts[0].setStuff("")
    }

    private fun zar(): Boolean {
        val rng = (0..10).random()
        if (rng < SharedVariables.planetIndex*2+2) return true
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
            device.status = when (breakingList.random()) {
                0 -> DeviceStatus.BROKEN
                1 -> DeviceStatus.STUCK
                2 -> DeviceStatus.SHORT
                else -> DeviceStatus.HOT
            }
        }
    }

    override fun loopAction() {
        if(countdownTimer.running){
            if(countdownTimer.done()){
                if(countdownIndex==3){
                    travelTimer.go()
                    travelTimer.running = true
                    countdownTimer.running = false
                    countdownIndex = -1
                    // bigMonitor.changeMonitor("graphics/spaceview.png")
                }
                else{
                    countdownIndex++
                    bigMonitor.changeMonitor(countdownList[countdownIndex])
                    countdownTimer.go()
                }
            }
        }

        if (travelTimer.running) {
            if (travelTimer.done()) {
                travelTimer.running = false
                changePlanet()
            } else if ((travelTimer.timeLeft()>1000)&&(timer.done())){
                if (zar()) {
                    breakShip()
                }
                timer.go()
            }
        }

        tools.forEach {
            if(it.status!= ToolStatus.INACTIVE){
                if (SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), it.getSprite())) {
                    it.status = ToolStatus.GLOW
                } else {
                    it.status = ToolStatus.IDLE
                }
            }
        }

        if(texts[0].revealed){
            sfxBeep.stop()
        }
        else{
            sfxBeep.play()
        }

        for (k in 1..3) {
            texts[k].hovered = texts[k].contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
        }
    }

    private fun changePlanet() {
        when (SharedVariables.planetIndex) {
            0 -> {
                when {
                    devices[1].status != DeviceStatus.NORMAL -> { // speaker
                        phText.getPlanetPassage(25)
                    }
                    devices[3].status != DeviceStatus.NORMAL -> { //translator
                        phText.getPlanetPassage(15)
                    }
                    else -> {
                        phText.getPlanetPassage(7)
                    }
                }
                bigMonitor.changeMonitor("graphics/planets/p1.png")
            }
            1 -> {
                if (devices[1].status != DeviceStatus.NORMAL) {
                    phText.getPlanetPassage(112)
                } else {
                    phText.getPlanetPassage(93)
                }
                bigMonitor.changeMonitor("graphics/planets/p2.png")
            }
            2 -> {
                when {
                    devices[1].status != DeviceStatus.NORMAL -> phText.getPlanetPassage(61) //speaker
                    devices[3].status != DeviceStatus.NORMAL -> phText.getPlanetPassage(3)//translator
                    else -> phText.getPlanetPassage(36)
                }
                bigMonitor.changeMonitor("graphics/planets/p3.png")
            }
            3 -> {
                if (items.isEmpty()) {
                    phText.getPlanetPassage(133)
                } else if ("stacey" in items && "dessert" !in items) {
                    phText.getPlanetPassage(134)
                } else if ("stacey" !in items && "dessert" in items) {
                    phText.getPlanetPassage(135)
                } else if ("stacey" in items && "dessert" in items) {
                    phText.getPlanetPassage(136)
                }
                bigMonitor.changeMonitor("graphics/planets/p5.png")
            }
        }
        SharedVariables.planetIndex++
        updateIslandText()
    }

    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.gameBackgroundPath, SharedVariables.gameBackgroundRatio)
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat() / 2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat() / 2)

        cargo = SharedVariables.loadSprite("graphics/cargo.png", SharedVariables.gameBackgroundRatio)
        cargo.setCenterX(1672f)
        cargo.setCenterY(610f)

        redButton = SimpleButton(DevicesData.redPath, DevicesData.redRatio)
        redButton.relocateCentre(DevicesData.redX, DevicesData.redY)

        devices = DevicesData.getDevices()
        tools = ToolsData.getTools()

        phText = TextIsland(Gdx.files.internal("planet_0/story.json"), SharedVariables.planets[0].second)
        TextIslandTexts().also {
            it.setStuff(phText.getCurrentLine(), 532f, 433f, 835f, 140f)
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
        bigMonitor = BigMonitor().apply{
            changeMonitor("graphics/planets/p0.png")
        }
        timer.go()
    }
}
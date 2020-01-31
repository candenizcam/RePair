package com.pungo.repairgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class MainGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture
    private var play: Boolean = false
    private var loadingScreen = LoadingScreen()
    private var musicPlaying = false
    private val mouse = MouseManager(loadingScreen)
    private var mainMenuScreen = MainMenuScreen()
    private var activeScreen : Screen = loadingScreen


    override fun create() {
        loadingScreen.lateInitializer()
        mainMenuScreen.lateInitializer()
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")

        MidiPlayer.open("sound/OST1.mid")
        MidiPlayer.setLooping(true)

        loadingScreen.timerGo()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0.05f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if(!musicPlaying && !loadingScreen.isLoading()){
            MidiPlayer.play()
            musicPlaying = true
        }

        activeScreen.loopAction()
        if (loadingScreen.loadingDone()) {
            activeScreen = mainMenuScreen

        }
        clickManager()
        batch.begin()
        activeScreen.draw(batch)
        batch.end()
    }

    fun clickManager(){
        //mouse.getClick()
        //activeScreen.clickHandler()
    }


    override fun dispose() {
        batch.dispose()
        img.dispose()
    }
}
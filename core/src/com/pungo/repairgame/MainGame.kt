package com.pungo.repairgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private var musicPlaying = false


    override fun create() {
        SharedVariables.loadingScreen.lateInitializer()
        SharedVariables.mainMenuScreen.lateInitializer()
        SharedVariables.gameScreen.lateInitializer()
        batch = SpriteBatch()


        MidiPlayer.open("sound/OST1.mid")
        MidiPlayer.setLooping(true)

        SharedVariables.loadingScreen.timer.go()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0.05f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        if (!musicPlaying && !SharedVariables.loadingScreen.isLoading()) {
            MidiPlayer.play()
            musicPlaying = true
        }

        SharedVariables.mouse.clickListener()
        SharedVariables.activeScreen.loopAction()
        if (!SharedVariables.loadingScreen.isLoading() && (SharedVariables.activeScreen == SharedVariables.loadingScreen)) {
            SharedVariables.activeScreen = SharedVariables.mainMenuScreen
        }
        batch.begin()
        SharedVariables.activeScreen.draw(batch)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
    }
}
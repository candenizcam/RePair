package com.pungo.repairgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.FitViewport
import kotlin.system.exitProcess


class MainGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private var musicPlaying = false
    private lateinit var camera: OrthographicCamera
    private lateinit var viewport: FitViewport


    override fun create() {
        camera = OrthographicCamera()
        viewport = FitViewport(SharedVariables.mainWidth.toFloat(), SharedVariables.mainHeight.toFloat(), camera)
        SharedVariables.loadingScreen.lateInitializer()
        SharedVariables.mainMenuScreen.lateInitializer()
        SharedVariables.gameScreen.lateInitializer()
        SharedVariables.endingScreen.lateInitializer()
        SharedVariables.creditsScreen.lateInitializer()
        SharedVariables.optionsScreen.lateInitializer()
        batch = SpriteBatch()
        MidiPlayer.open("sound/OST1.mid")
        MidiPlayer.setLooping(true)

        val pm = SharedVariables.loadPixmap("graphics/cursor/cursor.png",0.25)
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 0, 0))
        pm.dispose()

        SharedVariables.loadingScreen.timer.go()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        batch.projectionMatrix = camera.combined
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
        batch.begin()
        SharedVariables.activeScreen.draw(batch)
        batch.end()
        if (SharedVariables.kill){
            dispose()
        }
    }

    override fun dispose() {
        batch.dispose()

        exitProcess(0)
    }
}
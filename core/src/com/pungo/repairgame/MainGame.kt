package com.pungo.repairgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainGame : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    lateinit var img: Texture
    private var loadingScreen = LoadingScreen()
    override fun create() {
        loadingScreen.lateInitializer()
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
        loadingScreen.timerGo()
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0.05f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        loadingScreen.loopAction()
        batch.begin()
        loadingScreen.draw(batch)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        img.dispose()
    }
}
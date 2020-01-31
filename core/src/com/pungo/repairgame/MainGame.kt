package com.pungo.repairgame

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainGame : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch
    private lateinit var img: Texture
    private lateinit var beep: Sound
    private lateinit var bgm: Music
    private var play: Boolean = false
    private var loadingScreen = LoadingScreen()

    override fun create() {
        loadingScreen.lateInitializer()
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
      //  beep = Gdx.audio.newSound(Gdx.files.internal("C:\\Users\\mifu3\\Desktop\\PunGo\\RePair\\443026_71257-lq.mp3"))
     //   bgm = Gdx.audio.newMusic(Gdx.files.internal("C:\\Users\\mifu3\\Desktop\\PunGo\\RePair\\OST1.mid"))
      //  bgm.play()
        MidiPlayer.open("C:\\Users\\mifu3\\Desktop\\PunGo\\RePair\\OST1.mid")
        MidiPlayer.setLooping(true)
        MidiPlayer.play()
        play = true
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
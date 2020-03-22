package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

object MusicPlayer {
    private lateinit var bgm : Music
    var volume = 1.0f

    fun open(fileName: String) {
        val file = Gdx.files.internal(fileName)
        bgm = Gdx.audio.newMusic(file)
        bgm.volume = volume
    }

    fun setLooping(loop: Boolean) {
        bgm.isLooping = loop
    }

    fun play() {
        bgm.play()
    }

    fun pause() {
        bgm.pause()
    }

    fun stop() {
        bgm.stop()
    }

    fun release(){
        bgm.dispose()
    }

    fun changeVolume(vol: Float){
        if(!SharedVariables.musicMuted){
            volume = vol
            bgm.volume = vol
        }
        else{
            volume = 0.0f
            bgm.volume = 0.0f
        }
    }
}
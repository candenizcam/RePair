package com.pungo.repairgame.desktop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.pungo.repairgame.MainGame
import com.pungo.repairgame.MidiPlayer
import com.pungo.repairgame.SharedVariables
import kotlin.system.exitProcess

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()


        config.title = "yourGame"
        config.width = SharedVariables.mainWidth
        config.height = SharedVariables.mainHeight
        //config.fullscreen = true;
        config.forceExit = true
        config.addIcon("graphics/Icon32.png", com.badlogic.gdx.Files.FileType.Internal)
        try {
            LwjglApplication(MainGame(), config)
        } catch (ex: Exception) {
            MidiPlayer.release()
            Gdx.app.exit()
            exitProcess(0)
        }
    }
}
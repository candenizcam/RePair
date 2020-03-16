package com.pungo.repairgame.desktop

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.pungo.repairgame.MainGame
import com.pungo.repairgame.MidiPlayer
import com.pungo.repairgame.SharedVariables
import org.lwjgl.Sys


object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()


        config.title = "RePair"
        //config.width = 1920
        //config.height = 1080
        config.width = SharedVariables.mainWidth
        config.height = SharedVariables.mainHeight
        //config.fullscreen = true;
        config.forceExit = true
        config.addIcon("graphics/Icon32.png", com.badlogic.gdx.Files.FileType.Internal)

        Thread.setDefaultUncaughtExceptionHandler { _, ex ->
            System.err.println("Critical Failure: " + ex.localizedMessage)
            Sys.alert("Critical Failure", ex.localizedMessage)
            MidiPlayer.release()
            Gdx.app.exit()
        }

        LwjglApplication(MainGame(), config)
    }
}
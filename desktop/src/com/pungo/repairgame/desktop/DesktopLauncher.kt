package com.pungo.repairgame.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.pungo.repairgame.MainGame
import com.pungo.repairgame.SharedVariables

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()


        config.title = "yourGame";
        config.width = SharedVariables.mainWidth
        config.height = SharedVariables.mainHeight
        config.fullscreen = true;
        LwjglApplication(MainGame(), config)
    }
}
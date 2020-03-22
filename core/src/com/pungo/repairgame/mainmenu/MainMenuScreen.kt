package com.pungo.repairgame.mainmenu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.pungo.repairgame.AnimationHandler
import com.pungo.repairgame.MusicPlayer
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.SharedVariables.gameScreen
import com.pungo.repairgame.ui.ButtonStatus
import com.pungo.repairgame.ui.SetButton
import com.pungo.repairgame.ui.ToggleButton
import java.util.*
import kotlin.concurrent.schedule

class MainMenuScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var titleSprite: Sprite
    private lateinit var startButton: SetButton
    private lateinit var exitButton: SetButton
    private lateinit var optionsButton: SetButton
    lateinit var continueButton: SetButton
    private lateinit var muteButton: ToggleButton
    private var sfx = Gdx.audio.newSound(Gdx.files.internal("sound/Blip.mp3"))
    private var sfxLaunch = Gdx.audio.newSound(Gdx.files.internal("sound/Launch.mp3"))
    private var rocketAnimation = AnimationHandler().also{
        it.relocateCentre(SharedVariables.mainWidth/2f,SharedVariables.mainHeight/2f)
    }
    private var rocketAnimCalled = false

    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio).apply{
            setCenterX(SharedVariables.mainWidth.toFloat() / 2)
            setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        }
        titleSprite = SharedVariables.loadSprite(SharedVariables.titlePath, 0.5).apply{
            setCenterX(SharedVariables.mainWidth.toFloat() / 2)
            setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        }
        startButton = SetButton("graphics/menu_buttons/start", ratio = 1f).apply{
            relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 450f)
        }
        optionsButton = SetButton("graphics/menu_buttons/options", ratio = 1f).apply{
            relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 300f)
        }
        exitButton = SetButton("graphics/menu_buttons/exit", ratio = 1f).apply{
            relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 150f)
        }
        continueButton = SetButton("graphics/menu_buttons/continue", ratio = 1f).apply{
            relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 600f)
            visible = false
        }
        muteButton = ToggleButton("graphics/menu_buttons/mute", ratio = 0.25f).apply{
            relocateCentre(1820f, 980f)
        }
        rocketAnimation.lateInitializer(0.1f, TextureAtlas(Gdx.files.internal(SharedVariables.rocketAnimationPath)).regions)
    }

    override fun draw(batch: SpriteBatch) {
        rocketAnimation.draw(batch)
        mainSprite.draw(batch)
        titleSprite.draw(batch)
        startButton.draw(batch)
        exitButton.draw(batch)
        optionsButton.draw(batch)
        continueButton.draw(batch)
        muteButton.draw(batch)
    }

    override fun firstPress() {
        when {
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), startButton.getBoundSprite()) -> {
                startButton.status = ButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), exitButton.getBoundSprite()) -> {
                exitButton.status = ButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), optionsButton.getBoundSprite()) -> {
                optionsButton.status = ButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), continueButton.getBoundSprite()) -> {
                continueButton.status = ButtonStatus.DOWN

            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), muteButton.getBoundSprite() ) -> {
                muteButton.toggle().also {
                    SharedVariables.musicMuted = it != 0
                    SharedVariables.sfxMuted = it != 0
                    MusicPlayer.changeVolume(SharedVariables.bgmVolume)
                }
            }
        }
    }

    override fun pressing() {

        when {
            startButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), startButton.activeSprite)) startButton.status = ButtonStatus.UP
            }
            exitButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), exitButton.activeSprite)) exitButton.status = ButtonStatus.UP
            }
            optionsButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), optionsButton.activeSprite)) optionsButton.status = ButtonStatus.UP
            }
            continueButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), continueButton.activeSprite)) continueButton.status = ButtonStatus.UP
            }
        }
    }

    override fun released() {
        when {
            startButton.status == ButtonStatus.DOWN -> {
                startButton.status = ButtonStatus.UP
                if(!SharedVariables.sfxMuted){
                    sfx.play(SharedVariables.sfxVolume)
                }
                if(!SharedVariables.sfxMuted){
                    sfxLaunch.play(SharedVariables.sfxVolume * 2)
                }
                SharedVariables.resetGame()
                rocketAnimCalled = true
                rocketAnimation.animationGo()
            }
            exitButton.status == ButtonStatus.DOWN -> {
                exitButton.status = ButtonStatus.UP
                if(!SharedVariables.sfxMuted){
                    sfx.play(SharedVariables.sfxVolume)
                }
                if (exitButton.visible) {
                    MusicPlayer.release()
                    Gdx.app.exit()
                }
            }
            optionsButton.status == ButtonStatus.DOWN -> {
                if(!SharedVariables.sfxMuted){
                    sfx.play(SharedVariables.sfxVolume)
                }
                optionsButton.status = ButtonStatus.UP
                if (optionsButton.visible) {
                    SharedVariables.activeScreen = SharedVariables.optionsScreen
                    //SharedVariables.activeScreen = SharedVariables.creditsScreen
                }
            }
            continueButton.status == ButtonStatus.DOWN -> {
                if(!SharedVariables.sfxMuted){
                    sfx.play(SharedVariables.sfxVolume)
                }
                continueButton.status = ButtonStatus.UP
                if (continueButton.visible){
                    gameScreen.resumeTimers()
                    SharedVariables.activeScreen = gameScreen
                }
            }
        }
    }

    override fun loopAction() {
        if(rocketAnimation.isDone() && rocketAnimCalled){
            Timer().schedule(370) {
                continueButton.visible = true
                rocketAnimCalled = false
                SharedVariables.activeScreen = gameScreen
            }
        }
    }
}
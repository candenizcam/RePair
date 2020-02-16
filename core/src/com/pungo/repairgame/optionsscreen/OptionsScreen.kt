package com.pungo.repairgame.optionsscreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.ButtonStatus
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SetButton
import com.pungo.repairgame.SharedVariables

class OptionsScreen: Screen() {
    private lateinit var mainSprite: Sprite
    //private lateinit var startButton: SetButton
    private lateinit var backButton: SetButton
    //private lateinit var optionsButton: SetButton
    //private lateinit var muteButton: ToggleButton
    private var sfx = Gdx.audio.newSound(Gdx.files.internal("sound/Blip.mp3"))


    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio).apply{
            setCenterX(SharedVariables.mainWidth.toFloat() / 2)
            setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        }
        backButton = SetButton("graphics/options_buttons/back", ratio = 1f).apply{
            relocateCentre(SharedVariables.mainWidth*0.2f,SharedVariables.mainHeight*0.1f)
        }

        //startButton = SetButton("graphics/menu_buttons/start", ratio = 0.2f)
        //startButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 450f)
        //continueButton = SetButton("graphics/menu_buttons/continue", ratio = 0.2f)
        //continueButton.relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 300f)
        //optionsButton = SetButton("graphics/menu_buttons/options", ratio = 0.2f).apply{
        //    relocateCentre(SharedVariables.mainWidth.toFloat() / 2, 150f)
        //}
        //muteButton = ToggleButton("graphics/menu_buttons/mute",ratio=0.25f).apply{
        //    relocateCentre(1820f, 980f)
        //}
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        backButton.draw(batch)
        //startButton.draw(batch)
        //continueButton.draw(batch)
        //optionsButton.draw(batch)
        //muteButton.draw(batch)
    }

    override fun firstPress() {
        when {
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), backButton.getBoundSprite()) -> {
                backButton.status = ButtonStatus.DOWN
            }
            /*
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), startButton.getBoundSprite()) -> {
                startButton.status = ButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), continueButton.getBoundSprite()) -> {
                continueButton.status = ButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), optionsButton.getBoundSprite()) -> {
                optionsButton.status = ButtonStatus.DOWN
            }
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), muteButton.getBoundSprite() ) -> {
                muteButton.toggle().also {
                    if (it==0){
                        SharedVariables.sfxVolume = 1.0f
                        MidiPlayer.mute(false)
                    } else {
                        SharedVariables.sfxVolume = 0.0f
                        MidiPlayer.mute(true)
                    }

                }
            }

             */
        }
    }

    override fun pressing() {

        when {
            backButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), backButton.activeSprite)) backButton.status = ButtonStatus.UP
            }
            /*
            startButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), startButton.activeSprite)) startButton.status = ButtonStatus.UP
            }
            continueButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), continueButton.activeSprite)) continueButton.status = ButtonStatus.UP
            }
            optionsButton.status == ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), optionsButton.activeSprite)) optionsButton.status = ButtonStatus.UP
            }

             */
        }
    }

    override fun released() {
        when {
            backButton.status == ButtonStatus.DOWN -> {
                backButton.status = ButtonStatus.UP
                SharedVariables.activeScreen = SharedVariables.mainMenuScreen

            }
            /*
            startButton.status == ButtonStatus.DOWN -> {
                startButton.status = ButtonStatus.UP
                sfx.play(SharedVariables.sfxVolume)
                Timer().schedule(370) {
                    SharedVariables.activeScreen = SharedVariables.gameScreen                }

            }
            continueButton.status == ButtonStatus.DOWN -> {
                continueButton.status = ButtonStatus.UP
                MidiPlayer.release()
                Gdx.app.exit()
                exitProcess(0)
            }
            optionsButton.status == ButtonStatus.DOWN -> {
                optionsButton.status = ButtonStatus.UP
                SharedVariables.activeScreen = SharedVariables.creditsScreen
            }

             */
        }
    }

    override fun loopAction() {

    }




}
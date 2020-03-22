package com.pungo.repairgame.optionsscreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.MusicPlayer
import com.pungo.repairgame.Screen
import com.pungo.repairgame.SharedVariables
import com.pungo.repairgame.ui.ButtonStatus
import com.pungo.repairgame.ui.SetButton
import com.pungo.repairgame.ui.Slider

class OptionsScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var backButton: SetButton
    private lateinit var musicSlider: Slider
    private lateinit var soundsSlider: Slider
    private lateinit var bgSprite: Sprite
    private lateinit var musicSprite: Sprite
    private lateinit var soundSprite: Sprite
    private lateinit var redlineSprite: Sprite
    private var sfx = Gdx.audio.newSound(Gdx.files.internal("sound/Blip.mp3"))

    override fun lateInitializer() {
        mainSprite = SharedVariables.loadSprite(SharedVariables.mainMenuBackgroundPath, SharedVariables.menuBackgroundRatio).apply{
            setCenterX(SharedVariables.mainWidth.toFloat() / 2)
            setCenterY(SharedVariables.mainHeight.toFloat() / 2)
        }
        backButton = SetButton("graphics/options_buttons/back", ratio = 1f).apply{
            relocateCentre(SharedVariables.mainWidth*0.2f,SharedVariables.mainHeight*0.1f)
        }
        musicSlider = Slider("graphics/options_buttons/music_slider",1f).apply{
            relocateCentre(SharedVariables.mainWidth*0.525f,SharedVariables.mainHeight*0.6375f)
        }
        soundsSlider = Slider("graphics/options_buttons/sounds_slider",1f).apply{
            relocateCentre(SharedVariables.mainWidth*0.525f,SharedVariables.mainHeight*0.5125f)
        }
        bgSprite = SharedVariables.loadSprite("graphics/options_buttons/images/bg.png", 1.0).apply{
            setCenter(SharedVariables.mainWidth*0.5f,SharedVariables.mainHeight*0.575f)
        }
        musicSprite = SharedVariables.loadSprite("graphics/options_buttons/images/music.png", 1.0).apply{
            setCenter(SharedVariables.mainWidth*0.4f,SharedVariables.mainHeight*0.6375f)
        }
        soundSprite = SharedVariables.loadSprite("graphics/options_buttons/images/sound.png", 1.0).apply{
            setCenter(SharedVariables.mainWidth*0.4f,SharedVariables.mainHeight*0.5125f)
        }
        redlineSprite = SharedVariables.loadSprite("graphics/options_buttons/images/redline.png", 1.0).apply{
            setCenter(SharedVariables.mainWidth*0.4f,SharedVariables.mainHeight*0.5125f)
        }

    }

    override fun draw(batch: SpriteBatch) {

        mainSprite.draw(batch)
        backButton.draw(batch)
        bgSprite.draw(batch)
        musicSprite.draw(batch)
        soundSprite.draw(batch)
        musicSlider.draw(batch)
        soundsSlider.draw(batch)
        if (musicSlider.sliderValue==0f){
            redlineSprite.setCenter(SharedVariables.mainWidth*0.4f,SharedVariables.mainHeight*0.6375f)
            redlineSprite.draw(batch)
        }
        if (soundsSlider.sliderValue==0f){
            redlineSprite.setCenter(SharedVariables.mainWidth*0.4f,SharedVariables.mainHeight*0.5125f)
            redlineSprite.draw(batch)
        }
    }

    override fun firstPress() {
        musicSlider.firstPressed()
        soundsSlider.firstPressed()

        when {
            SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), backButton.getBoundSprite()) -> {
                backButton.status = ButtonStatus.DOWN
            }
        }
    }

    override fun pressing() {
        musicSlider.pressing()
        soundsSlider.pressing()

        when (backButton.status) {
            ButtonStatus.DOWN -> {
                if (!SharedVariables.contains(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), backButton.activeSprite)) backButton.status = ButtonStatus.UP
            }
        }
    }

    override fun released() {
        musicSlider.released()
        soundsSlider.released()

        when (backButton.status) {
            ButtonStatus.DOWN -> {
                if(!SharedVariables.sfxMuted){
                    sfx.play(SharedVariables.sfxVolume)
                }
                backButton.status = ButtonStatus.UP
                SharedVariables.activeScreen = SharedVariables.mainMenuScreen
            }
        }
    }

    override fun loopAction() {
        SharedVariables.sfxVolume = soundsSlider.sliderValue
        SharedVariables.bgmVolume = musicSlider.sliderValue
        MusicPlayer.changeVolume(SharedVariables.bgmVolume)
    }
}
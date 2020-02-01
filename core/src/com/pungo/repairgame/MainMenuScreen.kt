package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import javax.swing.text.html.Option

class MainMenuScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var bgPixmap: Pixmap
    private lateinit var StartButton : SimpleButton
    private lateinit var ContinueButton : SimpleButton
    private lateinit var OptionsButton : SimpleButton

    override fun firstPress() {

    }

    override fun pressing() {

    }

    override fun released() {

    }

    private fun loadImage(){
        val pixmap = Pixmap(Gdx.files.internal(SharedVariables.mainMenuBackgroundPath))
        bgPixmap = Pixmap(SharedVariables.mainWidth,SharedVariables.mainHeight,pixmap.format)
        bgPixmap.filter = Pixmap.Filter.NearestNeighbour
        bgPixmap.blending = Pixmap.Blending.None
        bgPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,bgPixmap.width,bgPixmap.height)
        pixmap.dispose()
        mainSprite = Sprite(Texture(bgPixmap))
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)
        StartButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        StartButton.relocateCentre(SharedVariables.mainWidth.toFloat()/2,500f)
        ContinueButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        ContinueButton.relocateCentre(SharedVariables.mainWidth.toFloat()/2,300f)
        OptionsButton = SimpleButton("graphics/placeholder_button", ratio = 0.5f)
        OptionsButton.relocateCentre(SharedVariables.mainWidth.toFloat()/2,100f)
    }


    override fun lateInitializer() {
        loadImage()
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        StartButton.draw(batch)
        ContinueButton.draw(batch)
        OptionsButton.draw(batch)
    }

    override fun firstPress() {
        when {
            StartButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat()) -> {
                StartButton.pressed = true
            }
            ContinueButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat()) -> {
                ContinueButton.pressed = true
            }
            OptionsButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat()) -> {
                OptionsButton.pressed = true
            }
        }

        println("fp : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun pressing() {

        when {
            StartButton.pressed -> {
                StartButton.pressed = StartButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())
            }
            ContinueButton.pressed -> {
                ContinueButton.pressed = ContinueButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())
            }
            OptionsButton.pressed -> {
                OptionsButton.pressed = OptionsButton.contains(Gdx.input.x.toFloat(),Gdx.input.y.toFloat())
            }
        }

        println("pr : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun released() {
        when {
            StartButton.pressed -> {
                StartButton.pressed = false
                println("Start go")

            }
            ContinueButton.pressed -> {
                ContinueButton.pressed = false
                println("Continue go")
            }
            OptionsButton.pressed -> {
                OptionsButton.pressed = false
                println("Options go")
            }
        }

        println("rel : ${Gdx.input.x} ${Gdx.input.y} ")
    }

    override fun loopAction() {

    }




}
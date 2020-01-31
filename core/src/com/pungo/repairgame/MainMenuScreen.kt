package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainMenuScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var bgPixmap: Pixmap

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
    }


    override fun lateInitializer() {
        loadImage()
    }

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
    }

    override fun loopAction() {

    }
}
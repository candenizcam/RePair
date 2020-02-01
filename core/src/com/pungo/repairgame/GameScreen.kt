package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class GameScreen: Screen() {
    private lateinit var mainSprite: Sprite
    private lateinit var leftestDevice: SimpleDevice

    override fun draw(batch: SpriteBatch) {
        mainSprite.draw(batch)
        leftestDevice.draw(batch)
    }

    override fun firstPress() {
    }

    override fun pressing() {
    }

    override fun released() {
    }

    override fun loopAction() {



    }

    override fun lateInitializer(){
        loadImage()
        leftestDevice = SimpleDevice("graphics/placeholder_leftest",0.25f)
        leftestDevice.relocateCentre(240f,410f)
    }




    private fun loadImage(){
        var pixmap = Pixmap(Gdx.files.internal(SharedVariables.gameBackgroundPath))
        var varPixmap = Pixmap((pixmap.width*SharedVariables.gameBackgroundRatio).toInt(),(pixmap.height*SharedVariables.gameBackgroundRatio).toInt(),pixmap.format)
        varPixmap.filter = Pixmap.Filter.NearestNeighbour
        varPixmap.blending = Pixmap.Blending.None
        varPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,varPixmap.width,varPixmap.height)
        pixmap.dispose()
        mainSprite = Sprite(Texture(varPixmap))
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)



    }
}
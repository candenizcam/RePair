package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SimpleButton(private val path: String, private val ratio: Float){
    private lateinit var upSprite: Sprite
    private lateinit var downSprite: Sprite
    var pressed = false
    init{
        loadImage()
    }

    private fun loadImage(){
        var pixmap = Pixmap(Gdx.files.internal(path + "/up.png"))
        var varPixmap = Pixmap((pixmap.width*ratio).toInt(),(pixmap.height*ratio).toInt(),pixmap.format)
        varPixmap.filter = Pixmap.Filter.NearestNeighbour
        varPixmap.blending = Pixmap.Blending.None
        varPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,varPixmap.width,varPixmap.height)
        pixmap.dispose()
        upSprite = Sprite(Texture(varPixmap))
        pixmap = Pixmap(Gdx.files.internal(path + "/down.png"))
        varPixmap = Pixmap((pixmap.width*ratio).toInt(),(pixmap.height*ratio).toInt(),pixmap.format)
        varPixmap.filter = Pixmap.Filter.NearestNeighbour
        varPixmap.blending = Pixmap.Blending.None
        varPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,varPixmap.width,varPixmap.height)
        pixmap.dispose()
        downSprite = Sprite(Texture(varPixmap))
    }

    fun relocateCentre(x: Float, y: Float){
        upSprite.setCenterX(x)
        upSprite.setCenterY(y)
        downSprite.setCenterX(x)
        downSprite.setCenterY(y)

    }

    fun draw(batch: SpriteBatch){
        if (pressed){
            downSprite.draw(batch)
        } else {
            upSprite.draw(batch)
        }
    }

    fun contains(x: Float, y: Float): Boolean {
        val xContains = (x>upSprite.x) and (x< (upSprite.x + upSprite.width))
        val yContains = (y>upSprite.y) and (y< (upSprite.y + upSprite.height))
        return xContains and yContains
    }



}
package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SimpleDevice(private val path: String, private val ratio: Float) {
    private lateinit var normalSprite: Sprite
    private lateinit var brokenSprite: Sprite
    private lateinit var hotSprite: Sprite
    private lateinit var chosenSprite: Sprite
    private var centreX = 0f
    private var centreY = 0f
    var status = DeviceStatus.NORMAL
    init{
        loadImage()
    }

    private fun loadImage(){
        var pixmap = Pixmap(Gdx.files.internal(path + "/normal.png"))
        var varPixmap = Pixmap((pixmap.width*ratio).toInt(),(pixmap.height*ratio).toInt(),pixmap.format)
        varPixmap.filter = Pixmap.Filter.NearestNeighbour
        varPixmap.blending = Pixmap.Blending.None
        varPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,varPixmap.width,varPixmap.height)
        pixmap.dispose()
        normalSprite = Sprite(Texture(varPixmap))
        pixmap = Pixmap(Gdx.files.internal(path + "/broken.png"))
        varPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,varPixmap.width,varPixmap.height)
        pixmap.dispose()
        brokenSprite = Sprite(Texture(varPixmap))
        pixmap = Pixmap(Gdx.files.internal(path + "/hot.png"))
        varPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,varPixmap.width,varPixmap.height)
        pixmap.dispose()
        hotSprite = Sprite(Texture(varPixmap))
        chosenSprite = normalSprite
    }


    fun relocateCentre(x: Float, y: Float){
        centreX = x
        centreY = y
    }


    fun draw(batch: SpriteBatch){
        chosenSprite = when(status){
            DeviceStatus.NORMAL -> normalSprite
            DeviceStatus.BROKEN -> brokenSprite
            DeviceStatus.HOT -> hotSprite
        }
        chosenSprite.setCenter(centreX,centreY)
        chosenSprite.draw(batch)

    }
}
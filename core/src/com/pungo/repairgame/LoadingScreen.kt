package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class LoadingScreen(){
    private lateinit var mainSprite: Sprite
    private lateinit var bgPixmap: Pixmap
    private var visible = true
    private var startTime = 0L


    fun lateInitializer(){
        loadImage()
    }

    private fun loadImage(){
        val pixmap = Pixmap(Gdx.files.internal("graphics/pungotitle.png"))
        bgPixmap = Pixmap((pixmap.width*SharedVariables.companyLogoRatio).toInt(),(pixmap.height*SharedVariables.companyLogoRatio).toInt(),pixmap.format)
        bgPixmap.filter = Pixmap.Filter.NearestNeighbour
        bgPixmap.blending = Pixmap.Blending.None
        bgPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,bgPixmap.width,bgPixmap.height)
        pixmap.dispose()
        mainSprite = Sprite(Texture(bgPixmap))
        mainSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        mainSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)
    }

    fun timerGo(){
        startTime = System.currentTimeMillis()
    }

    fun loopAction(){
        val nowTime = System.currentTimeMillis()-startTime
        when {
            nowTime<1000 -> {
                mainSprite.setAlpha(nowTime.toFloat()/1000)
            }
            nowTime<2000 -> {
                mainSprite.setAlpha(1f)
            }
            nowTime<3000 -> {
                mainSprite.setAlpha(3f-nowTime.toFloat()/1000)
            }
            else -> {
                visible = false
            }
        }


    }

    fun draw(batch: SpriteBatch){
        if (visible){
            mainSprite.draw(batch)
        }

    }





}
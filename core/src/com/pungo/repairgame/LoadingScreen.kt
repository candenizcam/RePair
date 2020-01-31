package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class LoadingScreen(): Screen() {
    private lateinit var bgSprite: Sprite
    private lateinit var bgPixmap: Pixmap
    private lateinit var menuSprite: Sprite
    private lateinit var menuPixmap: Pixmap
    private var menuVisible = false
    private var startTime = 0L
    private var doneSignalSent = false //this is so that end of loading switches to menu only once

    override fun lateInitializer(){
        loadImage()
    }



    private fun loadImage(){
        var pixmap = Pixmap(Gdx.files.internal(SharedVariables.companyLogoPath))
        bgPixmap = Pixmap((pixmap.width*SharedVariables.companyLogoRatio).toInt(),(pixmap.height*SharedVariables.companyLogoRatio).toInt(),pixmap.format)
        bgPixmap.filter = Pixmap.Filter.NearestNeighbour
        bgPixmap.blending = Pixmap.Blending.None
        bgPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,bgPixmap.width,bgPixmap.height)
        pixmap.dispose()
        bgSprite = Sprite(Texture(bgPixmap))
        bgSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        bgSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)


        pixmap = Pixmap(Gdx.files.internal(SharedVariables.mainMenuBackgroundPath))
        bgPixmap = Pixmap(SharedVariables.mainWidth,SharedVariables.mainHeight,pixmap.format)
        bgPixmap.filter = Pixmap.Filter.NearestNeighbour
        bgPixmap.blending = Pixmap.Blending.None
        bgPixmap.drawPixmap(pixmap, 0,0, pixmap.width,pixmap.height,0,0,bgPixmap.width,bgPixmap.height)
        pixmap.dispose()
        menuSprite = Sprite(Texture(bgPixmap))
        menuSprite.setCenterX(SharedVariables.mainWidth.toFloat()/2)
        menuSprite.setCenterY(SharedVariables.mainHeight.toFloat()/2)


    }

    fun timerGo(){
        startTime = System.currentTimeMillis()
    }

    override fun loopAction(){
        val nowTime = System.currentTimeMillis()-startTime
        when {
            nowTime<1000 -> {
                menuVisible = false
                bgSprite.setAlpha(nowTime.toFloat()/1000)

            }
            nowTime<2000 -> {
                bgSprite.setAlpha(1f)
            }
            nowTime<3000 -> {
                menuVisible = true
                bgSprite.setAlpha(3f-nowTime.toFloat()/1000)
            }
            else -> {
            }
        }


    }

    override fun draw(batch: SpriteBatch){
        if (menuVisible){
            menuSprite.draw(batch)
        }
        bgSprite.draw(batch)
    }

    fun isLoading() : Boolean {
        if(System.currentTimeMillis()-startTime < 3000){
            return true
        }
        return false
    }

    fun loadingDone(): Boolean{
        if (!doneSignalSent and !isLoading()){
            doneSignalSent = true
            return true
        }
        return false
    }
}
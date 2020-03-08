package com.pungo.repairgame.gamescreen.devices

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch


/* This class holds the graphics for the device
but it has a purpose that is not neatness
it will allow us to add animations to device troubles by simply overriding draw methods
 */
open class DeviceGraphics() {


    /* to check for a click
    we can replace this with a click checker in graphics instead of a shared one
     */
    open fun getNormalSprite(): Sprite {
        return Sprite()
    }

    open fun reset(){

    }

    open fun recentre(centreX: Float,centreY: Float){}

    open fun drawDead(batch: SpriteBatch){}

    open fun drawNormal(batch: SpriteBatch){}

    open fun drawHot(batch: SpriteBatch){}

    open fun drawBroken(batch: SpriteBatch){}

    open fun drawStuck(batch: SpriteBatch){}

    open fun drawShort(batch: SpriteBatch){}
}
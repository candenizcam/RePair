package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.pungo.repairgame.SharedVariables

class CargoBay {
    private var staceySprite = SharedVariables.loadSprite("graphics/cargo/stacey.png",0.25).also{
        it.setCenter(1732f,610f)
    }
    private var sweetSprite =  SharedVariables.loadSprite("graphics/cargo/sweet.png",0.25).also{
        it.setCenter(1732f,610f)
    }
    private var flowerSprite =  SharedVariables.loadSprite("graphics/cargo/flower.png",0.25).also{
        it.setCenter(1732f,610f)
    }



    fun draw(batch: SpriteBatch, displayList: List<String>){
        if ("stacey" in displayList) staceySprite.draw(batch)
        if ("dessert" in displayList) sweetSprite.draw(batch)
        if ("flower" in displayList) flowerSprite.draw(batch)

    }

}


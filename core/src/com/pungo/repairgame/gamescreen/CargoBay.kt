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
    private var bay = SharedVariables.loadSprite("graphics/cargo.png", SharedVariables.gameBackgroundRatio).apply{
        setCenterX(1672f)
        setCenterY(610f)
    }
    private var items = mutableListOf<String>()


    fun addToItems(s: String){
        items.add(s)
    }


    fun endingTree(): Int{
        if (items.isEmpty()) {
            SharedVariables.endingScreen.badEnder()
            return 133
        } else if ("stacey" in items && "dessert" !in items && "flower" !in items) {
            SharedVariables.endingScreen.badEnder()
            return 134
        } else if ("stacey" !in items && "dessert" in items && "flower" in items) {
            SharedVariables.endingScreen.goodEnder()
            return 135
        } else if ("stacey" in items && "dessert" in items && "flower" in items) {
            SharedVariables.endingScreen.goodEnder()
            return 136
        } else if ("stacey" !in items && "dessert" in items && "flower" !in items) {
            SharedVariables.endingScreen.badEnder()
            return 208
        } else if ("stacey" !in items && "dessert" !in items && "flower" in items) {
            SharedVariables.endingScreen.badEnder()
            return 210
        } else if ("stacey" in items && "dessert" in items && "flower" !in items) {
            SharedVariables.endingScreen.goodEnder()
            return 209
        } else if ("stacey" in items && "dessert" !in items && "flower" in items) {
            SharedVariables.endingScreen.goodEnder()
            return 207
        } else {
            return -1
        }
    }




    fun draw(batch: SpriteBatch){
        bay.draw(batch)
        val displayList = items.toList()
        if ("stacey" in displayList) staceySprite.draw(batch)
        if ("dessert" in displayList) sweetSprite.draw(batch)
        if ("flower" in displayList) flowerSprite.draw(batch)

    }

}


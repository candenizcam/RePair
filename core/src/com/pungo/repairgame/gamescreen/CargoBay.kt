package com.pungo.repairgame.gamescreen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.pungo.repairgame.SharedVariables

class CargoBay {
    private var baySprite : Sprite
    private var staceySprite: Sprite
    private var sweetSprite: Sprite
    private var flowerSprite: Sprite
    private var items = mutableListOf<String>()
    init {
        var atlas = TextureAtlas(Gdx.files.internal("graphics/cargo/cargo.atlas"))
        staceySprite = atlas.createSprite("stacey").also{
            it.setCenter(1672f,610f)
        }
        sweetSprite = atlas.createSprite("sweet").also{
            it.setCenter(1672f,610f)
        }
        flowerSprite = atlas.createSprite("flower").also{
            it.setCenter(1672f,610f)
        }
        baySprite = atlas.createSprite("normal").also{
            it.setCenter(1672f,610f)
        }

    }



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
        baySprite.draw(batch)
        val displayList = items.toList()
        if ("stacey" in displayList) staceySprite.draw(batch)
        if ("dessert" in displayList) sweetSprite.draw(batch)
        if ("flower" in displayList) flowerSprite.draw(batch)

    }

}


package com.pungo.repairgame

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Screen {
    open fun draw(batch: SpriteBatch) {

    }

    open fun loopAction() {

    }
}
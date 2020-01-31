package com.pungo.repairgame

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Screen {
    abstract fun draw(batch: SpriteBatch)

    abstract fun loopAction()
}
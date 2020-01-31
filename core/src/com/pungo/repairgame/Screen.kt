package com.pungo.repairgame

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Screen {
    abstract fun draw(batch: SpriteBatch)

    abstract fun firstPress()
    abstract fun pressing()
    abstract fun released()

    abstract fun loopAction()
    abstract fun lateInitializer()

}
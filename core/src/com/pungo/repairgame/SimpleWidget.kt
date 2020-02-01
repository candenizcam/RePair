package com.pungo.repairgame

abstract class SimpleWidget(private val path: String, private val ratio: Float)  {
    init {
        loadImage()
    }

    abstract fun loadImage()

}
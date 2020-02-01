package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator

class ChoiceText(){
    private lateinit var font: BitmapFont
    private var fontColour = Color(1f,0.95f,1f,1f)
    private var fontAddress = "fonts/plasmatic.ttf"
    private var fontSize = 32
    private var singleWidth = 0f
    private var singleHeight = 0f
    private var lineMargin = 0f
    init{
        fontGenerator()
    }

    private fun fontGenerator() {
        val generator = FreeTypeFontGenerator(Gdx.files.internal(fontAddress))
        val parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = fontSize
        parameter.color = fontColour
        font = generator.generateFont(parameter) // font size 12 pixels
        generator.dispose() // don't forget to dispose to avoid memory leaks!
        GlyphLayout().let{
            it.setText(font, "A")
            singleWidth = it.width
            singleHeight = it.height
            it.setText(font,"A\nA")
            lineMargin = it.height - 2*singleHeight
        }
    }




}
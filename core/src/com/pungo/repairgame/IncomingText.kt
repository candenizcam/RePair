package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import java.lang.Integer.max
import kotlin.math.min

class IncomingText {
    private lateinit var font: BitmapFont
    private var fontColour = Color(0f,0f,0f,1f)
    private var fontAddress = "fonts/plasmatic.ttf"
    private var fontSize = 32
    private var singleWidth = 0f
    private var singleHeight = 0f
    private var lineMargin = 0f
    private var centrePos = Pair(0f,0f)
    var visible = false
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
        println("Pak men herosneri nuj verser volmeri\nSerserki na nanki henk a senq\nHay Hay".split("\n"))


    }

    fun draw(batch: SpriteBatch, text: String, x: Float, y: Float, w: Float=0f,h: Float=0f){
        var modifiedText = text
        if (w>0){
            var newParagraphs = mutableListOf("")
            modifiedText.split("\n").forEach{
                it.split(" ").forEach{it2->
                    if ((newParagraphs.last().length + it2.length + 1)*singleWidth<w ){
                        newParagraphs[newParagraphs.lastIndex] += " $it2"
                    } else {
                        newParagraphs.add(it2)
                    }
                }
                newParagraphs.add("")
            }
            modifiedText = newParagraphs.joinToString("\n",prefix = "", postfix = "")
        }

        if (h>0){
            val allowedLine  = (h/(singleHeight+lineMargin)).toInt()
            modifiedText.split("\n").let{
                modifiedText = it.subList(0, min(allowedLine,it.size-1)).joinToString("\n",prefix = "", postfix = "")
            }

        }
        font.draw(batch, modifiedText, x,y)
    }

}
package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import java.lang.Integer.max
import kotlin.math.min

class TextIslandTexts {
    private lateinit var font: BitmapFont
    private var fontColour = Color(1f,0.95f,1f,1f)
    private var fontAddress = "fonts/plasmatic.ttf"
    private var fontSize = 32
    private var singleWidth = 0f
    private var singleHeight = 0f
    private var lineMargin = 0f
    private var letterReveal = 0f
    private var text = ""
    private var top = 0f
    private var left = 0f
    private var width = 0f
    private var height = 0f
    private var modifiedWidth = 0f
    private var modifiedHeight = 0f
    var revealed = false
    var hovered = false //true when mouse is hovering above
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

    fun letterRevealReset() {
        letterReveal = 0f
    }

    fun setStuff(t: String = text, x: Float = left, y: Float = top, w: Float = width, h: Float = height){
        text = t
        left = x
        top = y
        width = w
        height = h
        var modifiedText = text

        if (w>0){
            var newParagraphs = mutableListOf("")
            var longestLine = 0
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
            newParagraphs.forEach {

                longestLine = max(longestLine,it.length+1)
            }
            modifiedText = newParagraphs.joinToString("\n",prefix = "", postfix = "")
            modifiedWidth  = longestLine*singleWidth
        }

        if (h>0){
            val allowedLine  = (h/(singleHeight+lineMargin)).toInt()
            modifiedText.split("\n").let{
                modifiedText = it.subList(0, min(allowedLine,it.size-1)).joinToString("\n",prefix = "", postfix = "")
                modifiedHeight = it.size*singleHeight + (it.size-1)*lineMargin
            }

        }
        text = modifiedText
    }

    fun contains(x: Float, y: Float): Boolean {
        val y_corr = SharedVariables.mainHeight - y
        val xContains = (x > left) && (x < (left + modifiedWidth))
        val yContains = (y_corr > (top)) && (y_corr < (top + modifiedHeight))
        return xContains && yContains
    }


    fun draw(batch: SpriteBatch, slowReveal: Boolean = false){
        var modifiedText = text
        if(slowReveal){
            letterReveal += SharedVariables.letterRevealSpeed

            revealed = (letterReveal.toInt()>(modifiedText.length-1))
            if (!revealed){
                modifiedText = modifiedText.slice(0..letterReveal.toInt())
            }
        } else {
            revealed = true
        }
        if (hovered){
            font.color = Color(1f,1f,0f,1f)
        } else {
            font.color = fontColour
        }

        font.draw(batch, modifiedText, left,top)
    }



}
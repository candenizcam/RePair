package com.pungo.repairgame

import com.badlogic.gdx.Gdx

class TextIsland(path: String) {
    var responseMap = mapOf<String,String>()
    var mainText = ""
    var aText = ""
    var bText = ""
    var cText = ""
    init{
        loadText(path)
    }


    fun loadText(path: String){
        val allText = Gdx.files.internal(path).readString().split("__")
        println(allText)
        mainText = allText[0]
        aText = allText[1]
        bText = allText[2]
        cText = allText[3]
        val aRes = allText[4].split("->")[1]
        val bRes = allText[5].split("->")[1]
        val cRes = allText[6].split("->")[1]
        responseMap = mapOf("a" to aRes, "b" to bRes, "c" to cRes)
        println("$mainText $aText $bText $cText")
        println(responseMap)

    }
}
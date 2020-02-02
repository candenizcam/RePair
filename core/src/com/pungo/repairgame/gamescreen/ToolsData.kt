package com.pungo.repairgame.gamescreen

import javax.tools.Tool

object ToolsData {
    const val icePath = "graphics/tools/ice"
    const val iceRatio = 0.25f
    const val iceX = 343f
    const val iceY = 666f
    const val tapePath = "graphics/tools/tape"
    const val tapeRatio = 0.25f
    const val tapeX = 153f
    const val tapeY = 904f
    const val cirPath = "graphics/tools/circuit"
    const val cirRatio = 0.25f
    const val cirX = 153f
    const val cirY = 666f
    const val oilPath = "graphics/tools/oil"
    const val oilRatio = 0.25f
    const val oilX = 343f
    const val oilY = 904f
    
    
    fun getTools(): List<SimpleTool> {
        var theList = mutableListOf<SimpleTool>()
        SimpleTool(cirPath, ratio = cirRatio).also{
            it.relocateCentre(cirX,cirY)
            it.status = ToolStatus.INACTIVE
            theList.add(it)
        }
        SimpleTool(icePath, ratio = iceRatio).also{
            it.relocateCentre(iceX,iceY)
            it.status = ToolStatus.INACTIVE
            theList.add(it)
        }
        SimpleTool(tapePath, ratio = tapeRatio).also{
            it.relocateCentre(tapeX,tapeY)

            theList.add(it)
        }
        SimpleTool(oilPath, ratio = oilRatio).also{
            it.relocateCentre(oilX,oilY)
            it.status = ToolStatus.INACTIVE
            theList.add(it)
        }
        return theList.toList()
    }
    
}
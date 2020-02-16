package com.pungo.repairgame.gamescreen

object ToolsData {
    private const val icePath = "graphics/tools/ice"
    private const val iceRatio = 1f
    private const val iceX = 343f
    private const val iceY = 666f
    private const val tapePath = "graphics/tools/tape"
    private const val tapeRatio = 1f
    private const val tapeX = 153f
    private const val tapeY = 904f
    private const val cirPath = "graphics/tools/circuit"
    private const val cirRatio = 1f
    private const val cirX = 153f
    private const val cirY = 666f
    private const val oilPath = "graphics/tools/oil"
    private const val oilRatio = 1f
    private const val oilX = 343f
    private const val oilY = 904f
    
    
    fun getTools(): List<SimpleTool> {
        val theList = mutableListOf<SimpleTool>()
        SimpleTool(cirPath, ratio = cirRatio, fixing = DeviceStatus.SHORT).also{
            it.relocateCentre(cirX,cirY)
            it.status = ToolStatus.INACTIVE
            theList.add(it)
        }
        SimpleTool(icePath, ratio = iceRatio, fixing = DeviceStatus.HOT).also{
            it.relocateCentre(iceX,iceY)
            it.status = ToolStatus.INACTIVE
            theList.add(it)
        }
        SimpleTool(tapePath, ratio = tapeRatio, fixing = DeviceStatus.BROKEN).also{
            it.relocateCentre(tapeX,tapeY)

            theList.add(it)
        }
        SimpleTool(oilPath, ratio = oilRatio, fixing = DeviceStatus.STUCK).also{
            it.relocateCentre(oilX,oilY)
            it.status = ToolStatus.INACTIVE
            theList.add(it)
        }
        return theList.toList()
    }
    
}
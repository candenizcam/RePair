package com.pungo.repairgame.gamescreen

object DevicesData {
    const val redPath = "graphics/devices/redbutton"
    const val redRatio = 0.25f
    const val redX = 152f
    const val redY = 363f
    private const val micPath = "graphics/devices/microphone"
    private const val micRatio = 0.25f
    private const val micX = 377f
    private const val micY = 448f
    private const val spePath = "graphics/devices/speaker"
    private const val speRatio = 0.25f
    private const val speX = 882f
    private const val speY = 505f
    private const val disPath = "graphics/devices/display"
    private const val disRatio = 0.25f
    private const val disX = 959f
    private const val disY = 363f
    private const val traPath = "graphics/devices/translator"
    private const val traRatio = 0.25f
    private const val traX = 1712f
    private const val traY = 455f


    fun getDevices(): List<SimpleDevice>{
        val theList = mutableListOf<SimpleDevice>()
        SimpleDevice(micPath, micRatio).also{
            it.relocateCentre(micX,micY)
            theList.add(it)
        }
        SimpleDevice(spePath, speRatio, true).also{
            it.relocateCentre(speX,speY)
            theList.add(it)
        }
        SimpleDevice(disPath, disRatio).also{
            it.relocateCentre(disX,disY)
            theList.add(it)
        }
        SimpleDevice(traPath,traRatio).also{
            it.relocateCentre(traX,traY)
            theList.add(it)
        }
        return theList.toList()
    }
}
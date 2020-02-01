package com.pungo.repairgame.gamescreen

object DevicesData {
    const val redPath = "graphics/devices/redbutton"
    const val redRatio = 0.25f
    const val redX = 162f
    const val redY = 363f
    const val micPath = "graphics/devices/microphone"
    const val micRatio = 0.25f
    const val micX = 377f
    const val micY = 448f
    const val spePath = "graphics/devices/speaker"
    const val speRatio = 0.25f
    const val speX = 882f
    const val speY = 505f
    const val disPath = "graphics/devices/display"
    const val disRatio = 0.25f
    const val disX = 959f
    const val disY = 363f
    const val traPath = "graphics/devices/translator"
    const val traRatio = 0.25f
    const val traX = 1712f
    const val traY = 455f


    fun getDevices(): List<SimpleDevice>{
        var theList = mutableListOf<SimpleDevice>()
        SimpleDevice(micPath, micRatio).also{
            it.relocateCentre(micX,micY)
            theList.add(it)
        }
        SimpleDevice(spePath, speRatio).also{
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
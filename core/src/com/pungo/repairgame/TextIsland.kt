package com.pungo.repairgame

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.utils.JsonValue

class TextIsland(path: FileHandle, private var currentPid: Int) {
    private val jsonReader = JsonReader()
    private var status = TextStatus.NO_DIALOGUE         // TextStatus.CHOICES = A passage and 3 choices will be visible
    private var passages: JsonValue            // TextStatus.NO_CHOICES = Only a passage will be visible
    private var currentPassage: JsonValue      // TextStatus.NO_DIALOGUE = No text visible

    init {
        val text = path.readString()
        val story = jsonReader.parse(text)
        passages = story.get("passages")
        currentPassage = passages[currentPid - 1]
        setStatus()
    }

    private fun setStatus() {
        status = when {
            status == TextStatus.NO_CHOICES -> {
                TextStatus.NO_DIALOGUE
            }
            currentPassage.get("links") == null -> {
                TextStatus.NO_CHOICES
            }
            else -> {
                TextStatus.CHOICES
            }
        }
    }

    fun getPlanetPassage(planetID: Int) { // for each planet, we will hold a starting id, and when we get to this planet,
        currentPid = planetID               // we will use this id to load the related passage
        currentPassage = passages[currentPid - 1]
        setStatus()
    }

    fun nextPassage(choice: Int) {   // if a choice is made, we will use this to load the chosen passage
        if (choice > 0) {               // else, currently nothing happens, but i can add stuff if we need it
            val links = currentPassage.get("links")
            currentPid = links[choice - 1].get("pid").asInt()
            currentPassage = passages[currentPid - 1]
        }
        setStatus()
    }

    fun getCurrentLine(): String {       // get current passage's text
        return currentPassage.get("text").asString().split("[[")[0]
    }

    fun getCurrentChoices(): List<String> {      // returns the current passage's choices as a list of strings
        val choices: MutableList<String> = mutableListOf()
        val links = currentPassage.get("links")

        for (i in 0 until links.size) {
            choices.add(links[i].get("name").asString())
        }

        return choices
    }

    fun sceneNotOver(): Boolean {
        try {
            currentPassage.get("tags")[0]

        } catch (ex: Exception) {
            return true
        }
        return false
    }

    fun getTag(): String{
        return currentPassage.get("tags")[0].asString()
    }

}
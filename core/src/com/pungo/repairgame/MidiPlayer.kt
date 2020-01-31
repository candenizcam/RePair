package com.pungo.repairgame

import com.badlogic.gdx.Gdx
import javax.sound.midi.MidiSystem
import javax.sound.midi.Sequence
import javax.sound.midi.Sequencer


object MidiPlayer {
    private var isLooping: Boolean = false
    private lateinit var sequence: Sequence
    private var sequencer: Sequencer = MidiSystem.getSequencer()

    fun open(fileName: String) {
        val file = Gdx.files.internal(fileName)
        sequence = MidiSystem.getSequence(file.read())
        sequencer.open()
        sequencer.sequence = sequence
    }

    fun isLooping(): Boolean {
        return sequencer.loopCount != 0
    }


    fun setLooping(loop: Boolean) {
        if (!loop) {
            sequencer.loopCount = 0
            return
        }
        sequencer.loopCount = Sequencer.LOOP_CONTINUOUSLY
    }

    fun play() {
        sequencer.start()
    }

    fun pause() {
        stop()
    }

    fun stop() {
        sequencer.stop()
    }

    fun release() {
        sequencer.close()
    }

    fun isPlaying(): Boolean {
        return sequencer.isRunning
    }

    fun setVolume(volume: Float) { //Not implemented
    }
}
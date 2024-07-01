package com.example.pacman;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class GameSounds {


    MediaPlayer start_sound;
    MediaPlayer eatPellet ;
    MediaPlayer deathSound;
    MediaPlayer btnSound;
    MediaPlayer winSound;
    public GameSounds() {

        // create background music
        Media sound = new Media(getClass().getResource("/start.mp3").toString());
        start_sound = new MediaPlayer(sound);
        start_sound.setVolume(.3);


        // create eat PELLET & POWERPELLET sound

        Media soundEatPELLET = new Media(getClass().getResource("/munch_1.mp3").toString());
        eatPellet = new MediaPlayer(soundEatPELLET);
        eatPellet.setVolume(.7);
        eatPellet.setCycleCount(1);


        // create death sound
        Media death_Sound = new Media(getClass().getResource("/over.mp3").toString());
        deathSound = new MediaPlayer(death_Sound);
        deathSound.setVolume(1);
        deathSound.setCycleCount(1);

        //create buttons sound

        Media btn = new Media(getClass().getResource("/btnSound.mp3").toString());
        btnSound = new MediaPlayer(btn);
        btnSound.setVolume(.5);
        btnSound.setCycleCount(1);


        //create win sound

        Media win = new Media(getClass().getResource("/bravo.mp3").toString());
        winSound = new MediaPlayer(win);
        winSound.setVolume(1);
        winSound.setCycleCount(1);




    }

}
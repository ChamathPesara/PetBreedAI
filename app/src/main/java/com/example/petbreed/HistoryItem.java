package com.example.petbreed;

public class HistoryItem {
    String imageUri, animal, breed, datetime;
    int confidence;

    public HistoryItem(String imageUri, String animal, String breed, int confidence, String datetime) {
        this.imageUri = imageUri;
        this.animal = animal;
        this.breed = breed;
        this.confidence = confidence;
        this.datetime = datetime;
    }
}

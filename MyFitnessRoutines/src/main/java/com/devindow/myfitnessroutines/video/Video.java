package com.devindow.myfitnessroutines.video;

import java.io.Serializable;

public class Video implements Serializable {

    // Public Fields
    public String name;
    public String description;
    public String url;


    // Constructors
    public Video(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }


    // Overrides
    @Override
    public String toString() {
        return name + " - " + url;
    }

}

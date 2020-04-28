package com.devindow.myfitnessroutines.video;

import com.devindow.myfitnessroutines.generic.Generic;

public class Video extends Generic {

    // Public Fields
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

package com.devindow.myfitnessroutines.generic;

import java.io.Serializable;

public abstract class Generic implements Serializable {

    // Public Fields
    public String name;
    public String description;
    public boolean isFree;
    public boolean ranRecently;


    // Overrides
    @Override
    public String toString() {
        return name + " - " + description;
    }

}

package org.daw.model;

import lombok.AccessLevel;
import lombok.Getter;

public class Person extends MediaItem{
    @Getter(AccessLevel.NONE)
    private String name;

    @Override
    public String getDisplayName() {
        return name;
    }
}

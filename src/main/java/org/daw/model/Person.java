package org.daw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Person extends MediaItem{
    @Getter(AccessLevel.NONE)
    private String name;

    @Override
    public String getDisplayName() {
        return name;
    }
}

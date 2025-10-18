package org.daw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Anime extends MediaContent {
    private int year;
    private String source;
    private int episodes;
    private boolean airing;
    private String duration;
    private Trailer trailer;
}

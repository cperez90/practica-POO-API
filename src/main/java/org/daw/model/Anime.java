package org.daw.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Anime extends MediaContent {
    private String year;
    private String source;
    private int episodes;
    private boolean airing;
    private String duration;
    private Trailer trailer;

}

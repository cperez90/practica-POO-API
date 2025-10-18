package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class Anime extends MediaContent {
    private int year;
    private String source;
    private int episodes;
    private boolean airing;
    private String duration;
    private Trailer trailer;
}

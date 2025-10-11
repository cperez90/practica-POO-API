package org.daw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Anime extends MediaItem {
    private String title;
    private String title_english;
    private String title_japanese;
    private List<Titles> titles;
    private String synopsis;
    private String type;
    private String year;
    private List<Genres> genres;
    private Boolean approved;
    private String source;
    private int episodes;
    private String status;
    private boolean airing;
    private Trailer trailer;
    private double score;
    private String rank;
    private String popularity;

}

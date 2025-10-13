package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Manga extends MediaItem{
    private String title;
    private String title_english;
    private String title_japanese;
    private List<Titles> titles;
    private String synopsis;
    private String type;
    private Boolean approved;
    private int chapters;
    private int volumes;
    private String status;
    private Boolean publishing;
    private double score;
    @SerializedName("scored_by")
    private int scoredBy;
    private String rank;
    private String popularity;
    private int members;
    private List<Genres> genres;
}

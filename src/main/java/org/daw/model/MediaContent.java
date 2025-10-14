package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MediaContent extends MediaItem{
    @Getter(AccessLevel.NONE)
    private String title;
    private String title_english;
    private String title_japanese;
    private List<Titles> titles;
    private String synopsis;
    private String backgorund;
    private String type;
    private Boolean approved;
    private String status;
    private double score;
    @SerializedName("scored_by")
    private int scoredBy;
    private String rank;
    private String popularity;
    private int favorites;
    private List<Genres> genres;

    @Override
    public String getDisplayName() {
        return title;
    }

    @Override
    public int compareTo(MediaItem o) {
        int nameComparator = this.getDisplayName().compareTo(o.getDisplayName());
        if (nameComparator != 0) {
            return nameComparator;
        }else {
            MediaContent other = (MediaContent) o;
            return this.genres.getFirst().getType().compareTo(other.genres.getFirst().getType());
        }
    }
}

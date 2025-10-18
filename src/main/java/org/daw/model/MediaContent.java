package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaContent other)) return false;
        if (malId != other.malId) return false;
        if (type == null) {
            if (other.type != null) return false;
        } else if (!type.equalsIgnoreCase(other.type)) {
            return false;
        }
        if (getDisplayName() == null) {
            return other.getDisplayName() == null;
        } else {
            return getDisplayName().equalsIgnoreCase(other.getDisplayName());
        }
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(malId);
        result = 31 * result + (type == null ? 0 : type.toLowerCase().hashCode());
        result = 31 * result + (getDisplayName() == null ? 0 : getDisplayName().toLowerCase().hashCode());
        return result;
    }
}

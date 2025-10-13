package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MediaItem implements Comparable<MediaItem> {
    @SerializedName("mal_id")
    protected int malId;
    protected String url;
    protected Image images;

    public abstract String getDisplayName();

    @Override
    public int compareTo(MediaItem o) {
        return this.getDisplayName().compareToIgnoreCase(o.getDisplayName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MediaItem)) return false;
        MediaItem mediaItem = (MediaItem) o;
        return malId == mediaItem.malId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(malId);
    }
}

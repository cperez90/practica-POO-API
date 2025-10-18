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
        if (!(o instanceof MediaItem other)) return false;
        if (malId != other.malId) return false;
        String nameThis = this.getDisplayName();
        String nameOther = other.getDisplayName();

        if (nameThis == null && nameOther == null) return true;
        if (nameThis == null || nameOther == null) return false;

        return nameThis.equalsIgnoreCase(nameOther);
    }

    @Override
    public int hashCode() {
        int result = Integer.hashCode(malId);
        String name = getDisplayName();
        result = 31 * result + (name == null ? 0 : name.toLowerCase().hashCode());
        return result;
    }
}

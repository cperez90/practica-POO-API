package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class MediaItem {
    @SerializedName("mal_id")
    protected int malId;
    protected String url;
}

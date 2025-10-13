package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Genres {
    @SerializedName("mal_id")
    protected int malId;
    protected String url;
    private String type;
    private String name;
}

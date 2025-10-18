package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Genres {
    @SerializedName("mal_id")
    protected int malId;
    protected String url;
    private String type;
    private String name;
}

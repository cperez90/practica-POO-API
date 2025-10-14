package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Character extends MediaItem {
    @Getter(AccessLevel.NONE)
    private String name;
    @SerializedName("name_kanji")
    private String nameKanji;
    private List<String> nicknames;
    private int favorites;
    private String about;


    @Override
    public String getDisplayName() {
        return name;
    }
}

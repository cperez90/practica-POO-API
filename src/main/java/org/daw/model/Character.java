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
public class Character extends MediaItem {
    @Getter(AccessLevel.NONE)
    private String name;
    @SerializedName("name_kanji")
    private String nameKanji;
    private List<String> nicknames;
    private int favorites;
    private String about;
    private List<MediaCharactersRelation> anime;
    private List<MediaCharactersRelation> manga;
    private List<VoiceActor> voices;


    @Override
    public String getDisplayName() {
        return name;
    }
}

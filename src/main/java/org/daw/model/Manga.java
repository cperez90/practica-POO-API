package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Manga extends MediaContent {
    private int chapters;
    private int volumes;
    private Boolean publishing;
    private int members;
}

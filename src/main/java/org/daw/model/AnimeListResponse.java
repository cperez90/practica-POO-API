package org.daw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnimeListResponse {

    private List<Pagination> pagination;
    private List<Anime> data;
}

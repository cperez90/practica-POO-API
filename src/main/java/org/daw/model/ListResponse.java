package org.daw.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListResponse<T extends MediaItem> {

    private Pagination pagination;
    private List<T> data;
}

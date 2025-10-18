package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pagination {
    private int last_visible_page;
    private boolean has_next_page;
    private int current_page;
    private PaginationItems items;
}

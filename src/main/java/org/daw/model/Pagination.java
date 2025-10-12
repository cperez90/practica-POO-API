package org.daw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int last_visible_page;
    private boolean has_next_page;
    private int current_page;
    private PaginationItems items;
}

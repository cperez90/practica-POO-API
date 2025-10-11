package org.daw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationItems {
    private int count;
    private int total;
    private int per_page;
}

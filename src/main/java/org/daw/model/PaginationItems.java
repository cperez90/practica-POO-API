package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaginationItems {
    private int count;
    private int total;
    private int per_page;
}

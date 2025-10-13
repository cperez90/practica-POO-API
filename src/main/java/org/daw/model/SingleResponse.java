package org.daw.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse <T extends MediaItem> {
    private T data;
}

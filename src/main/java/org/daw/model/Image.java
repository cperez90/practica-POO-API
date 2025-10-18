package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Image {
    private ImageFormat jpg;
    private ImageFormat webp;
}

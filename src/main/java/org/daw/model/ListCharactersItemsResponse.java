package org.daw.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ListCharactersItemsResponse {
    private List<CharacterItems> data;
}

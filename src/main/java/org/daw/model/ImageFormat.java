package org.daw.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageFormat {
    @SerializedName("image_url")
    private  String imageUrl;
    @SerializedName("small_image_url")
    private String smallImageUrl;
    @SerializedName("large_image_url")
    private String largeImageUrl;
}

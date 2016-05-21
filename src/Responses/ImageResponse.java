package Responses;


import Media.Image;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class ImageResponse extends BaseResponse {
    private Image image;

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

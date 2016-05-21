package Responses;

import Media.Video;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class VideoResponse extends BaseResponse {
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}

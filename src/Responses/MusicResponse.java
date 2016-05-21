package Responses;

import Media.Music;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class MusicResponse extends BaseResponse {
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}

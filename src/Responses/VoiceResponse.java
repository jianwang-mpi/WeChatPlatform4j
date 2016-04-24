package Responses;

import Media.Voice;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class VoiceResponse extends BaseResponse{
    private Voice voice;

    public Voice getVoice() {
        return voice;
    }

    public void setVoice(Voice voice) {
        this.voice = voice;
    }
}

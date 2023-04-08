package audio.streaming.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ListenEvent extends Event {

    @JsonProperty
    public String artist;
    @JsonProperty
    public String song;
    @JsonProperty
    public float duration;


    public ListenEvent () {}

    public ListenEvent(String artist, String song, float duration, long ts, long sessionId, String auth, String level, int itemInSession, String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        super(ts, sessionId, auth, level, itemInSession, city, zip, state, userAgent, lon, lat, userId, lastName, firstName, gender, registration, tag);

        this.artist = artist;
        this.song = song;
        this.duration = duration;

    }

    @Override
    public Map<String, Object> getEventMap() {

        Map<String, Object> record = super.getEventMap();

        record.put("artist", this.artist);
        record.put("song", this.song);
        record.put("duration", this.duration);

        return record;
    }

}

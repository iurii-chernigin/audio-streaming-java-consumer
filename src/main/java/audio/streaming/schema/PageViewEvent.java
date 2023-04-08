package audio.streaming.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.EAN;

import java.util.HashMap;
import java.util.Map;
import java.lang.Float;

public class PageViewEvent extends Event {

    @JsonProperty
    public String page;
    @JsonProperty
    public String method;
    @JsonProperty
    public int status;
    @JsonProperty
    public String artist;
    @JsonProperty
    public String song;
    @JsonProperty
    public float duration;


    public PageViewEvent() {}

    public PageViewEvent(String page, String method, int status, long ts, long sessionId, String auth, String level, int itemInSession,String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        super(ts, sessionId, auth, level, itemInSession, city, zip, state, userAgent, lon, lat, userId, lastName, firstName, gender, registration, tag);
        this.page = page;
        this.method = method;
        this.status = status;

    }

    // Constructor with additional fields: artist, song, duration
    public PageViewEvent(String artist, String song, float duration, String page, String method, int status, long ts, long sessionId, String auth, String level, int itemInSession,String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        super(ts, sessionId, auth, level, itemInSession, city, zip, state, userAgent, lon, lat, userId, lastName, firstName, gender, registration, tag);
        this.page = page;
        this.method = method;
        this.status = status;
        this.artist = artist;
        this.song = song;
        this.duration = duration;

    }

    @Override
    public Map<String, Object> getEventMap() {

        Map<String, Object> record = super.getEventMap();

        record.put("page", this.page);
        record.put("method", this.method);
        record.put("status", this.status);

        // Optional fields, usually they set up with page=NextSong
        record.put("artist", this.artist);
        record.put("song", this.song);
        record.put("duration", this.duration);

        return record;
    }


}

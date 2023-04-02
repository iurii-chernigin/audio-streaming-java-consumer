package audio.streaming.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListenEvent {

    @JsonProperty
    public String artist;

    @JsonProperty
    public String song;

    @JsonProperty
    public float duration;

    @JsonProperty
    public long ts;

    @JsonProperty
    public long sessionId;

    @JsonProperty
    public String auth;

    @JsonProperty
    public String level;

    @JsonProperty
    public int itemInSession;

    @JsonProperty
    public String city;

    @JsonProperty
    public int zip;

    @JsonProperty
    public String state;

    @JsonProperty
    public String userAgent;

    @JsonProperty
    public float lon;

    @JsonProperty
    public float lat;

    @JsonProperty
    public int userId;

    @JsonProperty
    public String lastName;

    @JsonProperty
    public String firstName;

    @JsonProperty
    public String gender;

    @JsonProperty
    public long registration; // Unix time in milliseconds

    @JsonProperty
    public String tag; // Custom tag (enters in cli)

    public ListenEvent() {}

    public ListenEvent(String artist, String song, float duration, long ts, long sessionId, String auth, String level, int itemInSession,String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {
        this.artist = artist;
        this.song = song;
        this.duration = duration;
        this.ts = ts;
        this.sessionId = sessionId;
        this.auth = auth;
        this.level = level;
        this.itemInSession = itemInSession;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.userAgent = userAgent;
        this.lon = lon;
        this.lat = lat;
        this.userId = userId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.registration = registration; // Unix time in milliseconds
        this.tag = tag; // Custom tag (enters in cli)
    }

}

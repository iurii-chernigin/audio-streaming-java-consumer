package audio.streaming.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class Event {

    @JsonProperty
    public int userId;
    @JsonProperty
    public long sessionId;
    @JsonProperty
    public long registration;
    @JsonProperty
    public long ts;
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
    public String lastName;
    @JsonProperty
    public String firstName;
    @JsonProperty
    public String gender;
    @JsonProperty
    public String tag;


    public Event() {}

    public Event(long ts, long sessionId, String auth, String level, int itemInSession, String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        this.ts = ts; // Unix time in milliseconds
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


    public Map<String, Object> getRecordBasedMap() {

        Map<String, Object> record = new HashMap<>();

        record.put("userId", this.userId);
        record.put("sessionId", this.sessionId);
        record.put("auth", this.auth);
        record.put("ts", this.ts);
        record.put("registration", this.registration);
        record.put("level", this.level);
        record.put("itemInSession", this.itemInSession);
        record.put("city", this.city);
        record.put("zip", this.zip);
        record.put("state", this.state);
        record.put("userAgent", this.userAgent);
        record.put("lon", this.lon);
        record.put("lat", this.lat);
        record.put("lastName", this.lastName);
        record.put("firstName", this.firstName);
        record.put("gender", this.gender);
        record.put("tag", this.tag);

        return record;
    }

}

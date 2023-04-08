package audio.streaming.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class Event {

    @JsonProperty
    public int userId;
    @JsonProperty
    public long sessionId;
    @JsonProperty
    public Timestamp registration;
    @JsonProperty
    public Timestamp ts;
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

        this.ts = new Timestamp(ts);
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
        this.registration = new Timestamp(registration);
        this.tag = tag; // Custom tag (enters in cli)

    }

    // Constructor to support the optionality of the auth field
    public Event(long ts, long sessionId, String level, int itemInSession, String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        this.ts = new Timestamp(ts); // Unix time in milliseconds
        this.sessionId = sessionId;
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
        this.registration = new Timestamp(registration); // Unix time in milliseconds
        this.tag = tag; // Custom tag (enters in cli)

    }


    public Map<String, Object> getEventMap() {

        Map<String, Object> record = new HashMap<>();

        if (this.ts != null) {
            record.put("ts", this.ts.toString());
        }
        if (this.registration != null) {
            record.put("registration", this.registration.toString());
        }
        if (this.auth != null) {
            record.put("auth", this.auth);
        }

        record.put("userId", this.userId);
        record.put("sessionId", this.sessionId);
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

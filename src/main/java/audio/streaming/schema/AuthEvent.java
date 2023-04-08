package audio.streaming.schema;

import audio.streaming.PageViewEventKafkaConsumer;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.Map;

public class AuthEvent extends Event {

    @JsonProperty
    private boolean success;

    public AuthEvent() {}

    public AuthEvent(boolean success, long ts, long sessionId, String auth, String level, int itemInSession, String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        super(ts, sessionId, auth, level, itemInSession, city, zip, state, userAgent, lon, lat, userId, lastName, firstName, gender, registration, tag);

        this.success = success;
    }

    @Override
    public Map<String, Object> getEventMap() {

        Map<String, Object> event = super.getEventMap();
        event.put("success", this.success);

        return event;
    }

}

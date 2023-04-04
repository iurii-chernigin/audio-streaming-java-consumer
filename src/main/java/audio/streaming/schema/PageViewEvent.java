package audio.streaming.schema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class PageViewEvent extends Event {

    @JsonProperty
    public String page;
    @JsonProperty
    public String method;
    @JsonProperty
    public int status;


    public PageViewEvent() {}

    public PageViewEvent(String page, String method, int status, long ts, long sessionId, String auth, String level, int itemInSession,String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {

        super(ts, sessionId, auth, level, itemInSession, city, zip, state, userAgent, lon, lat, userId, lastName, firstName, gender, registration, tag);
        this.page = page;
        this.method = method;
        this.status = status;

    }

    public Map<String, Object> getRecordMap() {

        Map<String, Object> record = getRecordBasedMap();

        record.put("page", this.page);
        record.put("method", this.method);
        record.put("status", this.status);

        return record;
    }


}

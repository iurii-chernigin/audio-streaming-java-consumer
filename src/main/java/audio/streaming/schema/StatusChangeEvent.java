package audio.streaming.schema;

import java.util.Map;
public class StatusChangeEvent extends Event {

    public StatusChangeEvent (String artist, String song, float duration, String page, String method, int status, long ts, long sessionId, String auth, String level, int itemInSession,String city, int zip, String state, String userAgent, float lon, float lat, int userId, String lastName, String firstName, String gender, long registration, String tag) {
        super(ts, sessionId, auth, level, itemInSession, city, zip, state, userAgent, lon, lat, userId, lastName, firstName, gender, registration, tag);
    }

    @Override
    public Map<String, Object> getEventMap(){
        return super.getEventMap();
    }
}

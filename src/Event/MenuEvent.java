package Event;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class MenuEvent extends BaseEvent {
    private String EventKey;

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
}

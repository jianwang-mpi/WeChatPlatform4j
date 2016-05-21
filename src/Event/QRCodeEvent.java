package Event;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class QRCodeEvent extends BaseEvent {
    private String EventKey;
    private String Ticket;
    public String getEventKey(){
        return EventKey;
    }
    public String getTicket(){return Ticket;}
    public void setEventKey(String eventKey){this.EventKey=eventKey;}
    public void setTicket(String ticket){this.Ticket=ticket;}
}

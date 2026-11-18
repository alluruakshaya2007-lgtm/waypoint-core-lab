package waypoint.model;

public class TicketAlreadyClosed extends RuntimeException {
    public TicketAlreadyClosed(String message) {
        super(message);
    }
}
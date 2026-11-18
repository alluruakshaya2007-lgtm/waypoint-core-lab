
package waypoint.model;

import java.util.Objects;

record TicketId(String value) {
    TicketId {
        Objects.requireNonNull(value);
        value = value.trim();

        if (value.isBlank()) {
            throw new IllegalArgumentException("Ticket ID cannot be blank");
        }
    }
}

record SpotId(String value) {
    SpotId {
        Objects.requireNonNull(value);
        value = value.trim();

        if (value.isBlank()) {
            throw new IllegalArgumentException("Spot ID cannot be blank");
        }
    }
}

record VehicleId(String value) {
    VehicleId {
        Objects.requireNonNull(value);
        value = value.trim();

        if (value.isBlank()) {
            throw new IllegalArgumentException("Vehicle ID cannot be blank");
        }
    }
}

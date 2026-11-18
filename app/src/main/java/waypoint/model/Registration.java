
package waypoint.model;

import java.util.Objects;

public record Registration(String value) {

    public Registration {
        Objects.requireNonNull(value, "Registration cannot be null");

        value = value.trim().toUpperCase();

        if (!value.matches("[A-Z]{2}\\d{2}[A-Z]{1,2}\\d{4}")) {
            throw new IllegalArgumentException(
                "Invalid vehicle registration: " + value
            );
        }
    }
}


package waypoint.model;

import java.util.Currency;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

/**
 * VALUE OBJECT.
 * Represents exact money using paise.
 */
public final class Money implements Comparable<Money> {

    private final long paise;
    private final Currency currency;

    private Money(long paise, Currency currency) {
        this.paise = paise;
        this.currency = Objects.requireNonNull(currency);
    }

    public static Money ofMinor(long paise, Currency currency) {
        return new Money(paise, currency);
    }

    public static Money rupees(long rupees) {
        return new Money(Math.multiplyExact(rupees, 100),
                Currency.getInstance("INR"));
    }

    public Money plus(Money other) {
        checkCurrency(other);
        return new Money(Math.addExact(this.paise, other.paise), currency);
    }

    public Money minus(Money other) {
        checkCurrency(other);
        return new Money(Math.subtractExact(this.paise, other.paise), currency);
    }

    public Money times(int multiplier) {
        return new Money(Math.multiplyExact(paise, multiplier), currency);
    }

    public boolean isNegative() {
        return paise < 0;
    }

    @Override
    public int compareTo(Money other) {
        checkCurrency(other);
        return Long.compare(this.paise, other.paise);
    }

    public List<Money> allocate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Number of shares must be positive");
        }

        List<Money> shares = new ArrayList<>();

        long quotient = paise / n;
        long remainder = paise % n;

        for (int i = 0; i < n; i++) {
            long amount = quotient;

            if (i < Math.abs(remainder)) {
                amount += Long.signum(remainder);
            }

            shares.add(new Money(amount, currency));
        }

        return List.copyOf(shares);
    }

    private void checkCurrency(Money other) {
        Objects.requireNonNull(other);

        if (!currency.equals(other.currency)) {
            throw new CurrencyMismatch("Currencies do not match");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Money other)) return false;

        return paise == other.paise &&
                currency.equals(other.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paise, currency);
    }

    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " +
                paise / 100 + "." +
                String.format("%02d", Math.abs(paise % 100));
    }
}

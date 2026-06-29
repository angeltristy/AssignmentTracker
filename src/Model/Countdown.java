package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Countdown {
    LocalDateTime now;
    LocalDateTime then;
    boolean active;

    public Countdown() {
        now = LocalDateTime.now();
    }

    public void set(LocalDateTime d) {
        then = d;
        active = true;
    }

    public void cancel() {
        active = false;
    }

    public LocalDateTime getRemaining() {
        if (!active) {
            return null;
        }
        Duration diff =  Duration.between(now, then);
        long days = diff.toDays();
        long minutes = diff.toMinutes();
        long seconds = diff.toSeconds();
        return LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(), (int) days, (int) minutes, (int) seconds);
    }
}
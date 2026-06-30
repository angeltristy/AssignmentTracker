package Model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Countdown {
    private LocalDateTime target;;

    public Countdown(LocalDateTime targetTime) {
        this.target = targetTime;
    }

    public Countdown() {
    }

    public void set(LocalDateTime targetTime) {
        this.target = targetTime;
    }

    public LocalDateTime getTarget() {
        return this.target;
    }



    /**
     * Calculates raw duration remaining.
     * Returns Duration.ZERO if the countdown has expired.
     */
    public Duration getRemainingDuration() {
        if (target == null) {
            return Duration.ZERO;
        }

        return Duration.between(LocalDateTime.now(), target);
    }
}
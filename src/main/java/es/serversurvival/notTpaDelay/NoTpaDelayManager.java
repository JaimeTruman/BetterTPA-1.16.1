package es.serversurvival.notTpaDelay;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class NoTpaDelayManager {
    private static final Set<NoTpaDelay> cantTpDelays = new HashSet<>();

    public static void add (NoTpaDelay delay) {
        cantTpDelays.add(delay);
    }

    public static Optional<NoTpaDelay> findByPlayer (String sender) {
        return cantTpDelays.stream()
                .filter(tpa -> tpa.getSender().equalsIgnoreCase(sender))
                .findAny();
    }

    public static void remove (NoTpaDelay noTpaDelay) {
        cantTpDelays.remove(noTpaDelay);
    }
}

package es.serversurvival.solicitudTp;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SolicitudManager {
    private static final Set<Solicitud> solicitudTPSet = new HashSet<>();

    public static void nuevaSolicitud(Solicitud solicitud) {
        solicitudTPSet.add(solicitud);
    }

    public static Optional<Solicitud> encontrarConTarget(String target) {
        return solicitudTPSet.stream()
                .filter(sol -> sol.getPlayerToTP().getName().equalsIgnoreCase(target))
                .findAny();
    }

    public static void borrar(String player) {
        solicitudTPSet.removeIf(sol -> sol.getSender().getName().equalsIgnoreCase(player)
                || sol.getPlayerToTP().getName().equalsIgnoreCase(player));
    }

}

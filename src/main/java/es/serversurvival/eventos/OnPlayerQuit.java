package es.serversurvival.eventos;

import es.serversurvival.notTpaDelay.NoTpaDelay;
import es.serversurvival.notTpaDelay.NoTpaDelayManager;
import es.serversurvival.solicitudTp.SolicitudManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

public class OnPlayerQuit implements Listener {
    @EventHandler
    public void playerQuit (PlayerQuitEvent event) {
        String nombreDelQueSeFue = event.getPlayer().getName();

        SolicitudManager.borrar(nombreDelQueSeFue);

        Optional<NoTpaDelay> delayOptional = NoTpaDelayManager.findByPlayer(nombreDelQueSeFue);

        delayOptional.ifPresent(NoTpaDelayManager::remove);
    }
}

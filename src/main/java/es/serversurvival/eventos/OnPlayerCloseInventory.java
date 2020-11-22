package es.serversurvival.eventos;

import es.serversurvival.solicitudTp.SolicitudManager;
import es.serversurvival.solicitudTp.Solicitud;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Optional;

public class OnPlayerCloseInventory implements Listener {
    @EventHandler
    public void playerCloseInventory (InventoryCloseEvent event){
        String player = event.getPlayer().getName();

        Optional<Solicitud> solicitudOptional = SolicitudManager.encontrarConTarget(player);

        if(solicitudOptional.isPresent()){
            SolicitudManager.borrar(player);
        }
    }
}

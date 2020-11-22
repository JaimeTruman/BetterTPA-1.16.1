package es.serversurvival.eventos;

import es.serversurvival.solicitudTp.SolicitudManager;
import es.serversurvival.solicitudTp.Solicitud;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Optional;

public class OnPlayerInventoryClick implements Listener {
    @EventHandler
    public void onClickInventory (InventoryClickEvent event) {
        try {
            Optional<Solicitud> solicitudOptional = SolicitudManager.encontrarConTarget(event.getWhoClicked().getName());

            if(solicitudOptional.isPresent()){
                Solicitud solicitud = solicitudOptional.get();

                event.setCancelled(true);
                String clickeadTypeItem = event.getCurrentItem().getType().toString();

                if(clickeadTypeItem.equalsIgnoreCase("GREEN_WOOL")){
                    solicitud.aceptar();
                }else if (clickeadTypeItem.equalsIgnoreCase("RED_WOOL")) {
                    solicitud.cancelar();
                }

            }
        }catch (NullPointerException ignored) {

        }
    }
}

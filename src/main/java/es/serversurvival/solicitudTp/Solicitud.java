package es.serversurvival.solicitudTp;

import es.serversurvival.notTpaDelay.NoTpaDelay;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Solicitud {
    private Inventory inventory;
    private Player sender;
    private Player playerToTP;

    public Solicitud(Player sender, Player playertoTP) {
        this.playerToTP = playertoTP;
        this.sender = sender;
        this.inventory = buildInventory();

        SolicitudManager.nuevaSolicitud(this);

        playertoTP.openInventory(inventory);
    }

    private Inventory buildInventory () {
        String titulo = ChatColor.DARK_RED + "   " + ChatColor.BOLD + sender.getName() + " tp solicitud";
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, titulo);

        ItemStack aceptar = new ItemStack(Material.GREEN_WOOL);
        ItemMeta aceptarMeta = aceptar.getItemMeta();
        aceptarMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "ACEPTAR");
        aceptar.setItemMeta(aceptarMeta);

        ItemStack cancelar = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelarMeta = cancelar.getItemMeta();
        cancelarMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "CANCELAR");
        cancelar.setItemMeta(cancelarMeta);

        inventory.setItem(1, aceptar);
        inventory.setItem(3, cancelar);

        return inventory;
    }

    public Player getSender() {
        return sender;
    }

    public Player getPlayerToTP() {
        return playerToTP;
    }

    public void aceptar () {
        sender.closeInventory();
        sender.teleport(playerToTP.getLocation());
        playerToTP.playSound(playerToTP.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);

        borrarDeMomoria();
    }

    public void cancelar () {
        playerToTP.closeInventory();
        sender.sendMessage(ChatColor.RED + playerToTP.getName() + " te ha cencelado el tp");

        borrarDeMomoria();
        NoTpaDelay delay = new NoTpaDelay(sender.getName(), playerToTP.getName());
    }

    private void borrarDeMomoria () {
        SolicitudManager.borrar(playerToTP.getName());
    }
}
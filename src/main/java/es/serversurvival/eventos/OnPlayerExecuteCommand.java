package es.serversurvival.eventos;

import es.serversurvival.notTpaDelay.NoTpaDelay;
import es.serversurvival.notTpaDelay.NoTpaDelayManager;
import es.serversurvival.solicitudTp.SolicitudManager;
import es.serversurvival.solicitudTp.Solicitud;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class OnPlayerExecuteCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) return true;

        String comnandName = command.getName();

        if(comnandName.equalsIgnoreCase("tpa")){
            executeCommandTPA((Player) sender, args);
        }

        return true;
    }

    private void executeCommandTPA (Player sender, String[] args) {
        if(args == null || args.length != 1){
            sender.sendMessage(ChatColor.DARK_RED + "Uso incorrecto: /tpa <jugador>");
            return;
        }
        Player playerToTP = Bukkit.getPlayer(args[0]);
        if(playerToTP == null){
            sender.sendMessage(ChatColor.DARK_RED + "El jugado necesita estar online");
            return;
        }
        if(playerToTP.getName().equalsIgnoreCase(sender.getName())){
            sender.sendMessage(ChatColor.DARK_RED + "No te puedes teletransportar a ti mismo xd");
            return;
        }
        Optional<Solicitud> solicitudOptional = SolicitudManager.encontrarConTarget(playerToTP.getName());
        if(solicitudOptional.isPresent()){
            sender.sendMessage(ChatColor.DARK_RED + "Ese jugador ya ha sido solicitado, probar en 3 minutoss");
            return;
        }
        Optional<NoTpaDelay> noTpaDelayOptional = NoTpaDelayManager.findByPlayer(sender.getName());
        if(!noTpaDelayOptional.isPresent()){
            sender.sendMessage(ChatColor.DARK_RED + "Ya le has enviado una solicitud, espera unos 3 minutos");
            return;
        }

        Solicitud nuevaSolicitud = new Solicitud(sender, playerToTP);
    }
}

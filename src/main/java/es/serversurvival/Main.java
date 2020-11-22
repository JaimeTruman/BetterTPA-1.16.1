package es.serversurvival;

import es.serversurvival.eventos.OnPlayerExecuteCommand;
import es.serversurvival.eventos.OnPlayerCloseInventory;
import es.serversurvival.eventos.OnPlayerInventoryClick;
import es.serversurvival.eventos.OnPlayerQuit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Main main;

    @Override
    public void onEnable () {
        setUpCommands();
        setUpEvents();

        main = this;
    }

    private void setUpCommands () {
        getCommand("tpa").setExecutor(new OnPlayerExecuteCommand());
    }

    private void setUpEvents () {
        getServer().getPluginManager().registerEvents(new OnPlayerCloseInventory(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInventoryClick(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerQuit(), this);
    }
    
    public static Main getInstance() {
        return main;
    }

}

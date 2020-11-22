package es.serversurvival.notTpaDelay;

import es.serversurvival.Main;
import org.bukkit.scheduler.BukkitRunnable;

public class NoTpaDelay extends BukkitRunnable {
    private String sender;
    private String target;

    public NoTpaDelay (String sender, String target) {
        this.sender = sender;
        this.target = target;

        NoTpaDelayManager.add(this);
        runTaskLater(Main.getInstance(), 20 * 60 * 3);
    }

    public String getSender() {
        return sender;
    }

    public String getTarget() {
        return target;
    }

    @Override
    public void run() {
        NoTpaDelayManager.remove(this);
    }
}

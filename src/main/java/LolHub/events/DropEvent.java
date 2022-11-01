package LolHub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropEvent implements Listener{

    @EventHandler
    public void dropEvent(PlayerDropItemEvent event){
        if(!event.getPlayer().hasPermission("LolHub.dropItem")) event.setCancelled(true);
    }
}

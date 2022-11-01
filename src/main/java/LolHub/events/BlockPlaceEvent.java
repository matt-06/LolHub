package LolHub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockPlaceEvent implements Listener{

    @EventHandler
    public void blockPlaceEvent(org.bukkit.event.block.BlockPlaceEvent event){
        event.setCancelled(true);
    }
}

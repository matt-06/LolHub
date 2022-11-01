package LolHub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockBreakEvent implements Listener{

    @EventHandler
    public void blockBreakEvent(org.bukkit.event.block.BlockBreakEvent event){
        if(!event.getPlayer().hasPermission("lolhub.bypassblockbreak")){
            event.setCancelled(true);
        }
    }
}

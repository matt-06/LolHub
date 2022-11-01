package LolHub.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelEvent implements Listener{

    @EventHandler
    public void foodLevelEvent(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}

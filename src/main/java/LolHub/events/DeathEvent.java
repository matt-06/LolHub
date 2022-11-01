package LolHub.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener{

   @EventHandler
   public void deathEvent(PlayerDeathEvent event){
       Player player = event.getEntity();

       player.spigot().respawn();
   }
}

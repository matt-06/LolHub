package LolHub.events;

import LolHub.Core;
import LolHub.utils.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.io.IOException;

public class DamageEvent implements Listener{
    private final Core plugin;

    private FileUtils fileUtils;

    public DamageEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void damageEvent(EntityDamageEvent event){
        fileUtils = new FileUtils(plugin);

        if(event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();

            try{
                if(event.getCause() == EntityDamageEvent.DamageCause.VOID) {
                    player.setVelocity(player.getVelocity().setY(0));

                    player.setFallDistance(0f);
                    player.teleport(fileUtils.getSpawn());
                    event.setCancelled(true);
                }
            }catch(IOException exception){
                exception.printStackTrace();
            }
            event.setCancelled(true);
        }
    }
}

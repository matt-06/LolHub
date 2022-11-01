package LolHub.events;

import LolHub.Core;
import LolHub.utils.game.ItemUtils;
import LolHub.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ProjectileEvent implements Listener{
    private final Core plugin;

    public ProjectileEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void projectileEvent(ProjectileLaunchEvent event){
        if (event.getEntity().getShooter() instanceof Player && event.getEntity() instanceof EnderPearl) {
            Player player = (Player) event.getEntity().getShooter();

            ItemStack item = ItemUtils.createItem(Material.ENDER_PEARL, StringUtils.convertString(plugin.getConfig().getString("inventory.enderbutt.name")));
            player.getInventory().addItem(item);

            player.setVelocity(new Vector(player.getLocation().getDirection().multiply(10).getX(), 2, player.getLocation().getDirection().multiply(10).getZ()));
            event.setCancelled(true);
        }
    }
}

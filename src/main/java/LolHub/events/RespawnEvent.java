package LolHub.events;

import LolHub.Core;
import LolHub.utils.FileUtils;
import LolHub.utils.game.InventoryUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.IOException;

public class RespawnEvent implements Listener{
    private FileUtils fileUtils;
    private final Core plugin;

    public RespawnEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void respawnEvent(PlayerRespawnEvent event){
        fileUtils = new FileUtils(plugin);

        Player player = event.getPlayer();

        try{
            player.teleport(fileUtils.getSpawn());
        }catch (IOException exception){
            exception.printStackTrace();
        }

        InventoryUtils inventoryUtils = new InventoryUtils(plugin);
        inventoryUtils.createDefaultInventory(player);
    }
}

package LolHub.events;

import LolHub.Core;
import LolHub.utils.FileUtils;
import LolHub.utils.game.InventoryUtils;
import LolHub.utils.game.ScoreboardUtils;
import LolHub.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class JoinEvent implements Listener{
    private FileUtils fileUtils;
    private ScoreboardUtils scoreboardUtils;
    private final Core plugin;
    private InventoryUtils inventoryUtils;
    public JoinEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent event){
       Player player = event.getPlayer();

       inventoryUtils = new InventoryUtils(plugin);
       fileUtils = new FileUtils(plugin);
       scoreboardUtils = new ScoreboardUtils(plugin);

       inventoryUtils.createDefaultInventory(player);

       try{
           if(fileUtils.getSpawn() != null){
               player.teleport(fileUtils.getSpawn());
           }
       }catch(IOException exception){
            exception.printStackTrace();
       }

       for(Player user : Bukkit.getOnlinePlayers()){
           scoreboardUtils.createScoreboard(user);
       }

       event.setJoinMessage(StringUtils.convertString(plugin.getConfig().getString("playerJoinsMessage").replaceAll("%Player%", player.getDisplayName())));
    }
}

package LolHub.events;

import LolHub.Core;
import LolHub.tools.HidePlayersTool;
import LolHub.tools.LinkTool;
import LolHub.tools.ParkourTool;
import LolHub.tools.ServerSelectorTool;
import LolHub.utils.FileUtils;
import LolHub.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEvent implements Listener{
    private FileUtils fileUtils;
    private final Core plugin;

    public InteractEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryEvent(PlayerInteractEvent event){
        Player player = event.getPlayer();

        String linksToolName = StringUtils.convertString(plugin.getConfig().getString("inventory.links.name"));
        String parkourToolName = StringUtils.convertString(plugin.getConfig().getString("inventory.parkour.name"));
        String selectorToolName = StringUtils.convertString(plugin.getConfig().getString("inventory.selector.name"));
        String hidePlayerToolName = StringUtils.convertString(plugin.getConfig().getString("inventory.hidePlayer.playersShownName"));
        String showPlayerToolName = StringUtils.convertString(plugin.getConfig().getString("inventory.hidePlayer.playersHideName"));

        LinkTool linkTool = new LinkTool(plugin);
        ParkourTool parkourTool = new ParkourTool(plugin);
        ServerSelectorTool serverSelectorTool = new ServerSelectorTool(plugin);
        HidePlayersTool hidePlayersTool = new HidePlayersTool(plugin);

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) return;

        try {
            if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
                if (event.getItem().getItemMeta().getDisplayName().equals(linksToolName)) {
                    linkTool.linkTool(player);
                } else if (event.getItem().getItemMeta().getDisplayName().equals(parkourToolName)) {
                    parkourTool.parkourTool(player);
                } else if (event.getItem().getItemMeta().getDisplayName().equals(selectorToolName)) {
                    serverSelectorTool.serverSelector(player);
                } else if (event.getItem().getItemMeta().getDisplayName().equals(hidePlayerToolName)) {
                    ItemMeta itemMeta = event.getItem().getItemMeta();
                    itemMeta.setDisplayName(showPlayerToolName);
                    event.getItem().setItemMeta(itemMeta);

                    hidePlayersTool.hidePlayers(player);
                } else if (event.getItem().getItemMeta().getDisplayName().equals(showPlayerToolName)) {
                    ItemMeta itemMeta = event.getItem().getItemMeta();
                    itemMeta.setDisplayName(hidePlayerToolName);
                    event.getItem().setItemMeta(itemMeta);

                    hidePlayersTool.showPlayers(player);
                } else if (event.getItem().getItemMeta().getDisplayName() == null) {
                    event.setCancelled(true);
                    //băga-mi-aș pula
                }
            }
        }catch (NullPointerException exception){
            return;
        }
    }
}

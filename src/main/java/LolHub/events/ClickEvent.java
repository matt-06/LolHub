package LolHub.events;

import LolHub.Core;
import LolHub.tools.ServerSelectorTool;
import LolHub.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener{
    private final Core plugin;

    public ClickEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void clickEvent(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        ServerSelectorTool serverSelectorTool = new ServerSelectorTool(plugin);

        if(event.getView().getTitle().equals(StringUtils.convertString(plugin.getConfig().getString("inventory.selector.server.gui_setting.gui_name")))){
            for(String path : plugin.getConfig().getConfigurationSection("server_gui").getKeys(false)){
                if(event.getCurrentItem().getItemMeta().getDisplayName().equals(StringUtils.convertString(plugin.getConfig().getString("server_gui." + path + ".name")))){
                    String server_name = plugin.getConfig().getString("server_gui." + path + ".server_name");
                    serverSelectorTool.connectPlayer(player, server_name);
                }
            }
            event.setCancelled(true);
        }else{
            return;
        }

        event.setCancelled(true);
    }
}

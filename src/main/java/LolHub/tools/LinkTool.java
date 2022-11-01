package LolHub.tools;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.entity.Player;

public class LinkTool {
    private final Core plugin;

    public LinkTool(Core plugin) {
        this.plugin = plugin;
    }

    public void linkTool(Player player){
        player.sendMessage(StringUtils.convertString(StringUtils.convertString(plugin.getConfig().getString("inventory.links.message"))));

        for(String link : plugin.getConfig().getStringList("inventory.links.links")){
            player.sendMessage(StringUtils.convertString(link));
        }
    }
}

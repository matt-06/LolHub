package LolHub.tools;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class HidePlayersTool {
    private final Core plugin;

    public HidePlayersTool(Core plugin) {
        this.plugin = plugin;
    }

    public void hidePlayers(Player player){
        Bukkit.getOnlinePlayers().forEach(player::hidePlayer);
        player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("playersHiddenMessage")));
    }

    public void showPlayers(Player player){
        Bukkit.getOnlinePlayers().forEach(player::showPlayer);
        player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("playersShownMessage")));
    }
}

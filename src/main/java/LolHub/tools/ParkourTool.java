package LolHub.tools;

import LolHub.Core;
import org.bukkit.entity.Player;

public class ParkourTool {
    private final Core plugin;

    public ParkourTool(Core plugin) {
        this.plugin = plugin;
    }

    public void parkourTool(Player player){
        player.performCommand(plugin.getConfig().getString("inventory.parkour.command"));
    }
}

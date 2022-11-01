package LolHub.events;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveEvent implements Listener{
    private final Core plugin;

    public LeaveEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void leaveEvent(PlayerQuitEvent event){
        event.setQuitMessage(StringUtils.convertString(plugin.getConfig().getString("playerLeavesMessage").replaceAll("%Player%", event.getPlayer().getDisplayName())));
    }
}

package LolHub.events;

import LolHub.Core;
import LolHub.utils.ListUtils;
import LolHub.utils.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class MessageEvent implements Listener{
    private final Core plugin;

    public MessageEvent(Core plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void messageEvent(PlayerChatEvent event){
        Player player = event.getPlayer();

        if(!ListUtils.isLockChat){
            for(String badWorld : plugin.getConfig().getStringList("filtered_messages")){
                String message = event.getMessage();

                message = message.replaceAll(badWorld, plugin.getConfig().getString("filter"));
                event.setMessage(message);
            }
        }else{
            player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("chatLockedError")));
            event.setCancelled(true);
        }
    }
}

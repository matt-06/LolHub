package LolHub.commands.lobby;

import LolHub.Core;
import LolHub.tools.ServerSelectorTool;
import LolHub.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubCommand implements CommandExecutor {
    private final Core plugin;
    public HubCommand(Core plugin) {
        this.plugin = plugin;
    }

    private ServerSelectorTool serverSelector;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        serverSelector = new ServerSelectorTool(plugin);

        if(!(sender instanceof Player)) sender.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPlayerError")));

        Player player = (Player) sender;

        serverSelector.connectPlayer(player, plugin.getConfig().getString("inventory.selector.server.gui_setting.main_server"));

        return true;
    }
}

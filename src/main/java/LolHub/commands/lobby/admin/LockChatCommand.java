package LolHub.commands.lobby.admin;

import LolHub.Core;
import LolHub.utils.ListUtils;
import LolHub.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockChatCommand implements CommandExecutor {
    private final Core plugin;

    public LockChatCommand(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) sender.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPlayerError")));

        Player player = (Player) sender;

        if(!player.hasPermission("lolhub.cmd.admin.lockchat")) player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPlayerError")));

        ListUtils.isLockChat = !ListUtils.isLockChat;

        return true;
    }
}

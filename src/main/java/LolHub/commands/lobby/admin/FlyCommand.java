package LolHub.commands.lobby.admin;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    private final Core plugin;

    public FlyCommand(Core plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) sender.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPlayerError")));

        Player player = (Player) sender;

        if(!player.hasPermission("lolhub.cmd.admin.fly")) sender.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPermsError")));

        if(player.getAllowFlight()){
            player.setAllowFlight(true);
            player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("flyEnabled")));
        }else{
            player.setAllowFlight(false);
            player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("flyDisabled")));
        }

        return true;
    }
}

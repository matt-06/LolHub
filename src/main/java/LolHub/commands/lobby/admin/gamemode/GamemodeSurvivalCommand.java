package LolHub.commands.lobby.admin.gamemode;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeSurvivalCommand implements CommandExecutor {
    private final Core plugin;

    public GamemodeSurvivalCommand(Core plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) sender.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPlayerError")));

        Player player = (Player) sender;

        if(!player.hasPermission("lolhub.cmd.admin.gamemode")) player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPermsError")));

        if(args.length == 0){
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("gamemodeCreativeMessage")));
        }else if(args.length == 1){
            if(args[0].equals("help")) player.sendMessage("command: /gms\noptional arg: /gms <player>");

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null){
                player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("argsInvalidError")));
            }else{
                target.setGameMode(GameMode.SURVIVAL);
            }
        }

        return true;
    }
}

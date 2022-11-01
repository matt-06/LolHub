package LolHub.commands.lobby;

import LolHub.Core;
import LolHub.utils.FileUtils;
import LolHub.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;

public class SpawnCommand implements CommandExecutor {
    private final Core plugin;

    public SpawnCommand(Core plugin) {
        this.plugin = plugin;
    }

    private File spawnData;

    private FileUtils fileUtils;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        fileUtils = new FileUtils(plugin);

        if(!(sender instanceof Player)){
            sender.sendMessage(StringUtils.convertString(plugin.getConfig().getString("noPlayerError")));
            return true;
        }

        Player player = (Player) sender;
        if(args.length == 0 && player.hasPermission("lolhub.cmd.admin-spawn")){
            try {
                player.teleport(fileUtils.getSpawn());
            }catch (IOException exception){
                player.sendMessage(ChatColor.RED + "Spawn not set");
            }

        }else if(args.length == 1 && player.hasPermission("lolhub.cmd.admin-spawn")){
            if(args[0].equals("setspawn")){
                Location location = player.getLocation();
                try{
                    fileUtils.createSpawn(location);
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }else if(args[0].equals("help")){
                player.sendMessage(ChatColor.GREEN + "Spawn commands: \n /spawn: teleport to the spawn if there's one\n /spawn setspawn: set the spawn");
            }else {
                player.sendMessage(StringUtils.convertString(plugin.getConfig().getString("argsInvalidError")));
            }
        }
        return true;
    }
}

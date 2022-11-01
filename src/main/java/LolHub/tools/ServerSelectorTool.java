package LolHub.tools;

import LolHub.Core;
import LolHub.utils.game.ItemUtils;
import LolHub.utils.StringUtils;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ServerSelectorTool {
    private final Core plugin;

    public ServerSelectorTool(Core plugin) {
        this.plugin = plugin;
    }

    public void serverSelector(Player player){
        Inventory serverSelector = Bukkit.createInventory(player, plugin.getConfig().getInt("inventory.selector.server.gui_setting.gui_size"), StringUtils.convertString(plugin.getConfig().getString("inventory.selector.server.gui_setting.gui_name")));

        for(String path : plugin.getConfig().getConfigurationSection("server_gui").getKeys(false)){
            ItemStack item = ItemUtils.createItem(Material.valueOf(plugin.getConfig().getString("server_gui." + path + ".icon_material")), StringUtils.convertString(plugin.getConfig().getString("server_gui." + path + ".name")));

            serverSelector.setItem(plugin.getConfig().getInt("server_gui." + path + ".inventory_index"), item);
        }
        player.openInventory(serverSelector);
    }

    public void connectPlayer(Player player, String server){
        ByteArrayDataOutput byteArrayOutputStream = ByteStreams.newDataOutput();

        byteArrayOutputStream.writeUTF("Connect");
        byteArrayOutputStream.writeUTF(server);

        player.sendPluginMessage(plugin, "BungeeCord", byteArrayOutputStream.toByteArray());
    }
}

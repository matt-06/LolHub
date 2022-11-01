package LolHub.utils.game;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {
    private final Core plugin;

    public InventoryUtils(Core plugin) {
        this.plugin = plugin;
    }

    public void createDefaultInventory(Player player){
        ItemStack links = ItemUtils.createItem(Material.valueOf(plugin.getConfig().getString("inventory.links.material")), StringUtils.convertString(plugin.getConfig().getString("inventory.links.name")));
        ItemStack EnderButt = ItemUtils.createItem(Material.ENDER_PEARL, StringUtils.convertString(plugin.getConfig().getString("inventory.enderbutt.name")));
        ItemStack parkour = ItemUtils.createItem(Material.valueOf(plugin.getConfig().getString("inventory.parkour.material")), StringUtils.convertString(plugin.getConfig().getString("inventory.parkour.name")));
        ItemStack selector = ItemUtils.createItem(Material.valueOf(plugin.getConfig().getString("inventory.selector.material")), StringUtils.convertString(plugin.getConfig().getString("inventory.selector.name")));
        ItemStack hidePlayerTool = ItemUtils.createItem(Material.NETHER_STAR, StringUtils.convertString(plugin.getConfig().getString("inventory.hidePlayer.playersShownName")));

        player.getInventory().setItem(plugin.getConfig().getInt("inventory.links.inventory_index"), links);
        player.getInventory().setItem(plugin.getConfig().getInt("inventory.enderbutt.inventory_index"), EnderButt);
        player.getInventory().setItem(plugin.getConfig().getInt("inventory.parkour.inventory_index"), parkour);
        player.getInventory().setItem(plugin.getConfig().getInt("inventory.selector.inventory_index"), selector);
        player.getInventory().setItem(plugin.getConfig().getInt("inventory.hidePlayer.inventory_index"), hidePlayerTool);
    }
}

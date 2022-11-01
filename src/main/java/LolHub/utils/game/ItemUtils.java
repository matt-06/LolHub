package LolHub.utils.game;

import LolHub.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
    public static ItemStack createItem(Material material, String name){
        ItemStack item = new ItemStack(material);

        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(StringUtils.convertString(name));
        item.setItemMeta(itemMeta);

        return item;
    }
}

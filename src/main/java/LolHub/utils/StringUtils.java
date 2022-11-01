package LolHub.utils;

import org.bukkit.ChatColor;

public class StringUtils {
    public static String convertString(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String replaceString(String string, String oldString, String newString){
        return string.replaceAll(oldString, newString);
    }
}

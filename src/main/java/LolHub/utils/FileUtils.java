package LolHub.utils;

import LolHub.Core;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.Location;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    private final File spawnDataFile = new File(Bukkit.getServer().getPluginManager().getPlugin("LolHub").getDataFolder(), "spawndata.yml");
    private FileConfiguration spawnDataConfiguration;
    private Location spawn;
    private final Core plugin;
    public FileUtils(Core plugin) {
        this.plugin = plugin;
    }

    public void createSpawn(Location location) throws IOException {

        if (!spawnDataFile.exists()) {
            spawnDataFile.createNewFile();
        }

        spawnDataConfiguration = YamlConfiguration.loadConfiguration(spawnDataFile);

        double posX = location.getX();
        double posY = location.getY();
        double posZ = location.getZ();
        float pitch = location.getPitch();
        float yaw = location.getYaw();
        String world = location.getWorld().getName();

        spawnDataConfiguration.set("spawn.X", posX);
        spawnDataConfiguration.set("spawn.Y", posY);
        spawnDataConfiguration.set("spawn.Z", posZ);
        spawnDataConfiguration.set("spawn.pitch", pitch);
        spawnDataConfiguration.set("spawn.yaw", yaw);
        spawnDataConfiguration.set("spawn.world", world);

        spawnDataConfiguration.save(spawnDataFile);
    }

    public Location getSpawn() throws IOException {
        if(spawnDataConfiguration == null) spawnDataConfiguration = YamlConfiguration.loadConfiguration(spawnDataFile);

        double posX = spawnDataConfiguration.getDouble("spawn.X");
        double posY = spawnDataConfiguration.getDouble("spawn.Y");
        double posZ = spawnDataConfiguration.getDouble("spawn.Z");
        float pitch = (float) spawnDataConfiguration.getDouble("spawn.pitch");
        float yaw = (float) spawnDataConfiguration.getDouble("spawn.yaw");
        String world = spawnDataConfiguration.getString("spawn.world");

        spawn = new Location(Bukkit.getWorld(world), posX, posY, posZ, pitch, yaw);

        return spawn;
    }
}

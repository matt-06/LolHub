package LolHub;

import LolHub.commands.lobby.HubCommand;
import LolHub.commands.lobby.SpawnCommand;
import LolHub.commands.lobby.admin.FlyCommand;
import LolHub.commands.lobby.admin.gamemode.GamemodeCreativeCommand;
import LolHub.commands.lobby.admin.LockChatCommand;
import LolHub.commands.lobby.admin.gamemode.GamemodeSpectatorCommand;
import LolHub.commands.lobby.admin.gamemode.GamemodeSurvivalCommand;
import LolHub.events.*;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public final class Core extends JavaPlugin implements PluginMessageListener {

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        createConfig();
        registerChannels();

        getLogger().info("LolHUB enabled");
    }
    @Override
    public void onDisable() {
        unregisterChannels();

        getLogger().info("LolHUB disabled");
    }
    void registerCommands(){
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("hub").setExecutor(new HubCommand(this));
        getCommand("lockchat").setExecutor(new LockChatCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("gmc").setExecutor(new GamemodeCreativeCommand(this));
        getCommand("gms").setExecutor(new GamemodeSurvivalCommand(this));
        getCommand("gmsp").setExecutor(new GamemodeSpectatorCommand(this));
    }
    void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinEvent(this), this);
        pm.registerEvents(new InteractEvent(this), this);
        pm.registerEvents(new ClickEvent(this), this);
        pm.registerEvents(new ProjectileEvent(this), this);
        pm.registerEvents(new DamageEvent(this), this);
        pm.registerEvents(new DeathEvent(), this);
        pm.registerEvents(new RespawnEvent(this), this);
        pm.registerEvents(new BlockPlaceEvent(), this);
        pm.registerEvents(new DropEvent(), this);
        pm.registerEvents(new BlockBreakEvent(), this);
        pm.registerEvents(new LeaveEvent(this), this);
        pm.registerEvents(new MessageEvent(this), this);
    }

    void createConfig(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    void registerChannels(){
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }

    void unregisterChannels(){
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!channel.equals("BungeeCord")) return;

        ByteArrayDataInput input = ByteStreams.newDataInput(message);
        String subchannel = input.readUTF();

        if(subchannel.equals("Connect")) return;
    }

}

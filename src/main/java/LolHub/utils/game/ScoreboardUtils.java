package LolHub.utils.game;

import LolHub.Core;
import LolHub.utils.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class ScoreboardUtils {
    private final Core plugin;

    public ScoreboardUtils(Core plugin) {
        this.plugin = plugin;
    }

    public void createScoreboard(Player player){
        ScoreboardManager manager =  Bukkit.getScoreboardManager();
        Scoreboard scoreboard = manager.getNewScoreboard();

        Objective obj = scoreboard.registerNewObjective(StringUtils.convertString(plugin.getConfig().getString("scoreboard_title")), "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        int _score = 0;

        for(String path : plugin.getConfig().getConfigurationSection("scoreboard").getKeys(true)){
            _score = plugin.getConfig().getConfigurationSection("scoreboard").getKeys(true).size();

            Score score = obj.getScore(StringUtils.convertString(plugin.getConfig().getString("scoreboard."+path)
                    .replaceAll("%Player%", player.getDisplayName())
                    .replaceAll("%OnlinePlayers%", String.valueOf(Bukkit.getOnlinePlayers().size()))
                    .replaceAll("%MaxPlayers%", String.valueOf(Bukkit.getMaxPlayers())
                    )));

            score.setScore(_score-1);

            player.setScoreboard(scoreboard);
        }
    }
}

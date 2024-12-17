package cho.info.helixCore.core.functions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class Messages implements Listener {

    public Plugin plugin;

    public Messages(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.welcome-message")));
        }else {
            player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.first-join-message")));
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("messages.quit-message")));
    }
}

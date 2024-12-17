package cho.info.helixCore.core.functions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;

public class PlayerMessages implements Listener {

    public Plugin plugin;

    public PlayerMessages(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerSendMessage(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        String color = plugin.getConfig().getString("messages.player-message-color");
        String contentColor = plugin.getConfig().getString("messages.player-message-content-color");
        event.callEvent();

        for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
            switch (plugin.getConfig().getString("messages.player-message-style")) {
                case "<>":
                    onlinePlayer.sendMessage(color + "<" + player.getName() + "> " + contentColor + message);
                    break;

                case "[]":
                    onlinePlayer.sendMessage(color + "[" + player.getName() + "] " + contentColor + message);
                    break;

                case null:
                    break;

                default:
                    onlinePlayer.sendMessage(color + player.getName() + ": " + contentColor + message);
                    plugin.getLogger().info("Invalid player message style in config.yml. Please use '<>' or '[]'.");
                    break;
            }
        }
    }

}

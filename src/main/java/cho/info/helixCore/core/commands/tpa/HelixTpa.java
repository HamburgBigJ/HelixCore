package cho.info.helixCore.core.commands.tpa;

import cho.info.helixCore.util.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class HelixTpa implements CommandExecutor {

    public DataManager dataManager;

    public HelixTpa(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (commandSender instanceof Player && args.length == 1) {
            Player player = (Player) commandSender;
            Player target = player.getServer().getPlayer(args[0]);

            if (target != null) {
                player.sendMessage("Request sent to " + target.getName());
                target.sendMessage(player.getName() + " wants to teleport to you. Type /tpaccept to accept the request.");

                String requestKey = "tpa." + target.getUniqueId().toString();
                dataManager.setPublicVar(requestKey, player.getUniqueId().toString());

                // Schedule the request to expire after 60 seconds
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // Check if the request still exists
                        if (dataManager.getPublicVar(requestKey) != null) {
                            dataManager.removePublicVar(requestKey);
                            player.sendMessage("Your teleport request to " + target.getName() + " has expired.");
                            target.sendMessage("The teleport request from " + player.getName() + " has expired.");
                        }
                    }
                }.runTaskLater(Bukkit.getPluginManager().getPlugin("HelixCore"), 20 * 60); // 60 seconds
            } else {
                player.sendMessage("Player not found");
            }
            return true;
        }

        commandSender.sendMessage("Usage: /tpa <player>");
        return false;
    }
}

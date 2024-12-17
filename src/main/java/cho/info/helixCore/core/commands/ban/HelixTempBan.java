package cho.info.helixCore.core.commands.ban;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelixTempBan implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 3) {
            commandSender.sendMessage("Player " + strings[0] + " has been banned.");

            Player target = commandSender.getServer().getPlayer(strings[0]);

            target.kickPlayer("You have been banned by " + commandSender.getName() + " for " + strings[1] + ". Reason: " + strings[2]);

            return true;
        }

        return false;
    }
}

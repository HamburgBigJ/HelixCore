package cho.info.helixCore.core.esseinsials.commands.ban;

import cho.info.helixCore.util.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelixBan implements CommandExecutor {

    public DataManager dataManager;
    public HelixBan(DataManager dataManager) {
        this.dataManager = dataManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 1) {
            commandSender.sendMessage("Player " + strings[0] + " has been banned.");

            Player target = commandSender.getServer().getPlayer(strings[0]);

            target.kickPlayer("You have been banned by " + commandSender.getName() + ".");

            return true;
        }

        return false;
    }
}

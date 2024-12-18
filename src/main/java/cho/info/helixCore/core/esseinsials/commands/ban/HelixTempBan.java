package cho.info.helixCore.core.esseinsials.commands.ban;

import cho.info.helixCore.util.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelixTempBan implements CommandExecutor {

    public DataManager dataManager;

    public HelixTempBan(DataManager dataManager) {
        this.dataManager = dataManager;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (strings.length == 3) {
            commandSender.sendMessage("Player " + strings[0] + " has been banned.");

            Player target = commandSender.getServer().getPlayer(strings[0]);

            target.kickPlayer("You have been banned by " + commandSender.getName() + " for " + strings[1] + ". Reason: " + strings[2]);

            dataManager.addPlayerValue(target, "punish.duration", strings[1]);
            dataManager.addPlayerValue(target, "punish.reason", strings[2]);

            return true;
        }

        return false;
    }
}

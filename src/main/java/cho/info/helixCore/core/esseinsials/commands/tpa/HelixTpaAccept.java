package cho.info.helixCore.core.esseinsials.commands.tpa;

import cho.info.helixCore.util.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HelixTpaAccept implements CommandExecutor {

    private final DataManager dataManager;

    public HelixTpaAccept(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command.");
            return true;
        }

        Player target = (Player) commandSender;

        // Schlüssel für die Anfrage basierend auf dem Zielspieler
        String requestKey = "tpa." + target.getUniqueId().toString();

        // Überprüfen, ob eine TPA-Anfrage existiert
        Object senderUuidObj = dataManager.getPublicVar(requestKey);
        if (senderUuidObj == null) {
            target.sendMessage("You have no pending teleport requests.");
            return true;
        }

        // Sender der TPA-Anfrage abrufen
        String senderUuidStr = senderUuidObj.toString();
        Player sender = Bukkit.getPlayer(senderUuidStr);

        if (sender == null) {
            target.sendMessage("The player who sent the teleport request is no longer online.");
            // Entferne die veraltete Anfrage
            dataManager.removePublicVar(requestKey);
            return true;
        }

        // Teleportation durchführen
        sender.teleport(target.getLocation());
        target.sendMessage(sender.getName() + " has been teleported to you.");
        sender.sendMessage("You have been teleported to " + target.getName() + ".");

        // Anfrage entfernen
        dataManager.removePublicVar(requestKey);

        return true;
    }
}

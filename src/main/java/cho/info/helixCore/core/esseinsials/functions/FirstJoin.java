package cho.info.helixCore.core.esseinsials.functions;

import cho.info.helixCore.util.DataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class FirstJoin implements Listener {

    public DataManager dataManager;

    public FirstJoin(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void onFirstJoin(PlayerJoinEvent event) {

        if (!event.getPlayer().hasPlayedBefore()) {

            Player player = event.getPlayer();

            // Personal Info
            dataManager.addPlayerValue(player, "info.name", player.getName());
            dataManager.addPlayerValue(player, "info.sessions", 1);
            dataManager.addPlayerValue(player, "info.first-join", System.currentTimeMillis());
            dataManager.addPlayerValue(player, "info.uuid", player.getUniqueId().toString());

            // Faction Info
            dataManager.addPlayerValue(player, "faction.name", "null");
            dataManager.addPlayerValue(player, "faction.role", "null");
            dataManager.addPlayerValue(player, "faction.permissionLv", "null");



        }
    }

}

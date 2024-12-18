package cho.info.helixCore.core.factions.team;

import cho.info.helixCore.util.DataManager;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HelixTeam {

    public DataManager dataManager;

    public HelixTeam(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void factionCreate(Player owner, String factionName, List<Player> members) {
        // create faction

        String prefix = factionName + ".";

        dataManager.addFactionVar(prefix + "factionName", factionName);
        dataManager.addFactionVar(prefix + "owner", owner.getName());
        dataManager.addFactionVar(prefix + "members", members);
        dataManager.addFactionVar(prefix + "balance", 0);

        // Claim

        List<Chunk> claimChunks = new ArrayList<>();
        claimChunks.add(owner.getLocation().getChunk());

        dataManager.addFactionVar(prefix + "claim.claimChunksLeft", 0);
        dataManager.addFactionVar(prefix + "claim.claimChunksList", claimChunks);
        dataManager.addFactionVar(prefix + "claim.Spawnpoint", owner.getLocation().toString());



    }

}

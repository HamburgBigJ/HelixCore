package cho.info.helixCore.core.factions;

import cho.info.helixCore.util.DataManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class HelixFactions {

    public JavaPlugin javaPlugin;
    public Plugin plugin;
    public DataManager dataManager;

    public HelixFactions(JavaPlugin javaPlugin, Plugin plugin) {
        this.javaPlugin = javaPlugin;
        this.plugin = plugin;
        this.dataManager = new DataManager(plugin.getDataFolder());
    }

    public void enable() {
        javaPlugin.getLogger().info("HelixFactions has been enabled!");



    }

}

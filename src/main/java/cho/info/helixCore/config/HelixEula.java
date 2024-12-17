package cho.info.helixCore.config;

import cho.info.helixCore.HelixCore;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class HelixEula {

    public Plugin plugin;

    public HelixEula(Plugin plugin) {
        this.plugin = plugin;
    }

    public void checkEula() {
        if (!plugin.getConfig().getBoolean("eula")) {
            PluginManager pluginManager = plugin.getServer().getPluginManager();

            pluginManager.disablePlugin(plugin);
        }
    }



}

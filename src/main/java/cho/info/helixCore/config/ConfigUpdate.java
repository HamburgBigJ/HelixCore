package cho.info.helixCore.config;

import org.bukkit.plugin.Plugin;

import java.io.File;

public class ConfigUpdate {

    public String version;
    public Plugin plugin;

    public ConfigUpdate(Plugin plugin, String version) {
        this.plugin = plugin;
        this.version = version;
    }

    public void updateConfig() {
        // Check if the config version is outdated
        if (!version.equals(plugin.getConfig().getString("plugin-info.version"))) {
            plugin.getLogger().info("Updating config file...");

            // Get the config file
            File configFile = new File(plugin.getDataFolder(), "config.yml");

            // Delete the old config file
            if (configFile.exists() && configFile.delete()) {
                plugin.getLogger().info("Old config file deleted.");
            } else {
                plugin.getLogger().warning("Failed to delete the old config file.");
            }

            // Save the default config to generate a new one
            plugin.saveDefaultConfig();
            plugin.getLogger().info("New config file has been generated.");

            // Update the in-memory config to the new version
            plugin.getConfig().set("plugin-info.version", version);
            plugin.saveConfig();

            plugin.getConfig().set("eula", true);

            plugin.getServer().getPluginManager().disablePlugin(plugin);

            plugin.getLogger().info("Config file has been updated to version: " + version);
        } else {
            plugin.getLogger().info("Config file is up to date.");
        }
    }

}

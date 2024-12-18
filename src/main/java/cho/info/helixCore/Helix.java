package cho.info.helixCore;

import cho.info.helixCore.config.ConfigUpdate;
import cho.info.helixCore.core.esseinsials.commands.ban.HelixBan;
import cho.info.helixCore.core.esseinsials.commands.HelixCommand;
import cho.info.helixCore.core.esseinsials.commands.ban.HelixTempBan;
import cho.info.helixCore.core.esseinsials.commands.tpa.HelixTpa;
import cho.info.helixCore.core.esseinsials.commands.tpa.HelixTpaAccept;
import cho.info.helixCore.core.esseinsials.functions.FirstJoin;
import cho.info.helixCore.core.esseinsials.functions.Messages;
import cho.info.helixCore.core.esseinsials.functions.PlayerMessages;
import cho.info.helixCore.core.factions.HelixFactions;
import cho.info.helixCore.util.DataManager;
import cho.info.helixCore.config.HelixEula;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Helix {

    public String version = "0.0.1";

    public Plugin plugin;
    public JavaPlugin javaPlugin;


    /**
     * Tools
     */
    public DataManager dataManager;

    public Helix(Plugin plugin, JavaPlugin javaPlugin) {
        this.plugin = plugin;
        this.javaPlugin = javaPlugin;
    }

    /**
     * Initialize the HelixCore
     */
    public void helixCoreInit() {
        plugin.getLogger().info("HelixCore initialized");
        this.dataManager = new DataManager(plugin.getDataFolder());

    }

    /**
     * Enable the plugin
     */
    public void enable() {
        plugin.getLogger().info("Helix enabled");

        // Eula
        HelixEula helixEula = new HelixEula(plugin);
        helixEula.checkEula();

        // Config
        ConfigUpdate configUpdate = new ConfigUpdate(plugin, version);
        configUpdate.updateConfig();

        // Factions
        HelixFactions helixFactions = new HelixFactions(javaPlugin, plugin);
        helixFactions.enable();

        // DataManager
        dataManager.setPublicVar("version", version);

        // Register events
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new Messages(plugin), plugin);
        pluginManager.registerEvents(new PlayerMessages(plugin), plugin);
        pluginManager.registerEvents(new FirstJoin(dataManager), plugin);

        // Register commands
        javaPlugin.getCommand("helix").setExecutor(new HelixCommand());
        javaPlugin.getCommand("tpa").setExecutor(new HelixTpa(dataManager));
        javaPlugin.getCommand("tpaccept").setExecutor(new HelixTpaAccept(dataManager));
        javaPlugin.getCommand("tempban").setExecutor(new HelixTempBan(dataManager));
        javaPlugin.getCommand("helixban").setExecutor(new HelixBan(dataManager));

    }

    /**
     * Disable the plugin
     */
    public void disable() {
        plugin.getLogger().info("Helix disabled");


    }

    /**
     * Initialize the debug mode :: TO DO
     */
    public void debugInit() {

    }

}

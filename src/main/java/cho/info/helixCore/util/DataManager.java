package cho.info.helixCore.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private final File playerDataFolder;
    private final File publicVarsFile;
    private final File factionVarsFile;
    private final Map<UUID, FileConfiguration> configCache = new HashMap<>();
    private FileConfiguration publicVarsConfig;

    public DataManager(File pluginFolder) {
        // Create a folder for player files if it doesn't exist
        playerDataFolder = new File(pluginFolder, "playerdata");
        if (!playerDataFolder.exists()) {
            playerDataFolder.mkdirs();
        }

        // Initialize the public variables file
        File serverVarsFolder = new File(pluginFolder, "ServerVars");
        if (!serverVarsFolder.exists()) {
            serverVarsFolder.mkdirs();
        }
        publicVarsFile = new File(serverVarsFolder, "PublicVars.yml");

        // Factions
        factionVarsFile = new File(serverVarsFolder, "FactionVars.yml");

        // Load public variables
        loadPublicVars();
        loadFactionVars();
    }

    // Retrieves the player's config file
    private FileConfiguration getPlayerConfig(UUID playerUUID) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        FileConfiguration config = configCache.get(playerUUID);

        if (config == null || !playerFile.exists()) {
            if (!playerFile.exists()) {
                try {
                    playerFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            config = YamlConfiguration.loadConfiguration(playerFile);
            configCache.put(playerUUID, config);
        }

        return config;
    }

    // Saves the player's config file
    public void savePlayerConfig(UUID playerUUID, FileConfiguration config) {
        File playerFile = new File(playerDataFolder, playerUUID.toString() + ".yml");
        try {
            config.save(playerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sets a value for the player
    public void setPlayerValue(Player player, String path, Object value) {
        FileConfiguration config = getPlayerConfig(player.getUniqueId());
        config.set(path, value);
        savePlayerConfig(player.getUniqueId(), config);
    }

    // Retrieves a value for the player
    public Object getPlayerValue(Player player, String path) {
        FileConfiguration config = getPlayerConfig(player.getUniqueId());
        return config.get(path);
    }

    // Adds a new variable with a default value
    public void addPlayerValue(Player player, String variableName, Object defaultValue) {
        FileConfiguration config = getPlayerConfig(player.getUniqueId());

        if (!config.contains(variableName)) {
            config.set(variableName, defaultValue);
            savePlayerConfig(player.getUniqueId(), config);
        }
    }

    // Checks if a variable exists
    public boolean contains(Player player, String path) {
        FileConfiguration config = getPlayerConfig(player.getUniqueId());
        return config.contains(path);
    }

    // Loads the public variables
    private void loadPublicVars() {
        if (!publicVarsFile.exists()) {
            try {
                publicVarsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        publicVarsConfig = YamlConfiguration.loadConfiguration(publicVarsFile);
    }

    // Retrieves a public variable value
    public Object getPublicVar(String path) {
        return publicVarsConfig.get(path);
    }

    // Sets a public variable value
    public void setPublicVar(String path, Object value) {
        publicVarsConfig.set(path, value);
        savePublicVars();
    }

    // Saves the public variables
    private void savePublicVars() {
        try {
            publicVarsConfig.save(publicVarsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void containsPublicVar(String path) {
        publicVarsConfig.contains(path);
    }

    // Adds a new public variable with a default value
    public void addPublicVar(String variableName, Object defaultValue) {
        if (!publicVarsConfig.contains(variableName)) {
            publicVarsConfig.set(variableName, defaultValue);
            savePublicVars();
        }
    }

    // Removes a public variable
    public void removePublicVar(String path) {
        if (publicVarsConfig.contains(path)) {
            publicVarsConfig.set(path, null);
            savePublicVars();
        }
    }

    public void loadFactionVars() {
        if (!factionVarsFile.exists()) {
            try {
                factionVarsFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        publicVarsConfig = YamlConfiguration.loadConfiguration(factionVarsFile);
    }

    public Object getFactionVar(String path) {
        return publicVarsConfig.get(path);
    }

    public void setFactionVar(String path, Object value) {
        publicVarsConfig.set(path, value);
        saveFactionVars();
    }

    private void saveFactionVars() {
        try {
            publicVarsConfig.save(factionVarsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFactionVar(String variableName, Object defaultValue) {
        if (!publicVarsConfig.contains(variableName)) {
            publicVarsConfig.set(variableName, defaultValue);
            saveFactionVars();
        }
    }

    public void removeFactionVar(String path) {
        if (publicVarsConfig.contains(path)) {
            publicVarsConfig.set(path, null);
            saveFactionVars();
        }
    }

    public boolean containsFactionVar(String path) {
        return publicVarsConfig.contains(path);
    }

}

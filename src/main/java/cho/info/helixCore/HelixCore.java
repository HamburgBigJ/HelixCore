package cho.info.helixCore;

import cho.info.helixCore.config.HelixEula;
import org.bukkit.plugin.java.JavaPlugin;

public final class HelixCore extends JavaPlugin {

    public Helix helix;
    @Override
    public void onEnable() {
        this.helix = new Helix(this, this);
        helix.helixCoreInit();
        helix.enable();

        if (getConfig().getBoolean("plugin-info.debug")) {
            helix.debugInit();
        }


    }

    @Override
    public void onDisable() {
        helix.disable();
    }
}

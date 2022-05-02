package guilded.guilded;

import doublewhaleapi.dwapi.DWAPI;
import guilded.guilded.Command.GuildCommand;


import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Guilded Bukkit Plugin
 *
 * @author WhaleOpop, BlackWarlow
 */
public final class Guilded extends JavaPlugin {
    /**
     * Singleton implementation.
     */
    private static Guilded instance;
    private PluginManager pluginManager = Bukkit.getPluginManager();
    /**
     * Plugin logger.
     */
    public Logger logger = getLogger();

    /**
     * Public available DWAPI core and integration settings.
     */
    public DWAPI core;
    public static Boolean coinmaterialinstalled = false;

    /**
     * Enables plugin - finds DWAPI core, reloads config file, initializes all
     * Commands, registers EventListener
     */
    @Override
    public void onEnable() {
        // Singleton
        instance = this;

        logger.info("Guilded Start");

        // Get DWAPI core
        Plugin loadCore = Bukkit.getPluginManager().getPlugin("DWAPI");
        if (loadCore == null) {
            logger.severe("CoinMaterial requires DWAPI core plugin to work, please install it!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        if (pluginManager.isPluginEnabled("DWAPI")) {
            logger.info("DWAPI core was successfuly loaded.");
            core = (DWAPI) pluginManager.getPlugin("DWAPI");
        } else {
            logger.severe("CoinMaterial requires DWAPI core plugin to work, please install it!");
            pluginManager.disablePlugin(this);
            return;
        }
		// Save config file
        saveDefaultConfig();
        core.coinPath.deserialize();
        core.coinPath.deserialize();


        // Seamless integrations with plugins
        if (getConfig().getString("settings.general.integrateDW").equalsIgnoreCase("enabled")) {
            coinmaterialinstalled=core.getAddonEnabled("Guilded");

            // Notify server admin in console
            if (coinmaterialinstalled) {
                logger.info("DoubleWhale Guilded plugin is found. DW Plugin Integration is active.");
            } else {
				logger.info("Check out our other DoubleWhale plugin 'CoinMaterial' at https://github.com/whaleopop/CoinMaterial");
				logger.info("If you see this and have CoinMaterial plugin and integrateDW enabled in config.yml, reffer to https://github.com/whaleOpop/Guilded README.md under Any DoubleWhale plugin integration");
            }
        } else {
			logger.info("integrateDW is disabled in config.yml, skipping integrations.");
        }


        // Register command
        new GuildCommand();

        // Register EventListener
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }
	/**
	 * Singleton implementation.
	 *
	 * @return CoinMaterial Plugin instance
	 */
    public static Guilded getInstance() {
        // Simple Singleton implementation
        return instance;
    }
	/**
	 * Handles plugin shutdown:<br>
	 * Saves guildStorage data to .json file.
	 */
    @Override
    public void onDisable() {
    }
}

package guilded.guilded;

import doublewhaleapi.dwapi.Test;
import guilded.guilded.Command.GuildCommand;
import guilded.guilded.GuildSerializer.GuildSerializer;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Implements abstract Guilded command
 * Usage:        import guilded.guilded package
 * Requirements: Bukkit, Gson
 */
public final class Guilded extends JavaPlugin {

	private static Guilded instance;

	public static Boolean coinmaterialinstalled = false;
	public Logger log = getLogger();

	@Override
	public void onEnable() {
		// Enables plugin - loads guilds from .json, initializes all commands, registers
		// EventListeners

		log.info("Guilded Start");

        Test test = new Test();
		test.Vivod();
		// Load guilds

		saveDefaultConfig();
		GuildSerializer.LoadGuild();

		// Seamless integrations with plugins
		if (getConfig().getString("settings.general.integrateDW").equalsIgnoreCase("enabled")) {
			for (final File fileEntry : new File("plugins/").listFiles()) {
				if (!fileEntry.isDirectory()) {
					String fileName = fileEntry.getName().toLowerCase();
					if (fileName.contains("coinmaterial") && fileName.endsWith(".jar")) {
						coinmaterialinstalled = true;
						break;
					}
				}
			}

			// Notify server admin in console
			if (coinmaterialinstalled) {
				log.info("DoubleWhale CoinMaterial plugin is found. DW Plugin Integration is active.");
			} else {
				log.info("Check out our other DoubleWhale plugin 'CoinMaterial' at https://github.com/whaleopop/CoinMaterial");
				log.info("If you see this and have CoinMaterial plugin and integrateDW enabled in config.yml, reffer to https://github.com/whaleOpop/Guilded README.md under Any DoubleWhale plugin integration");
			}
		} else {
			log.info("integrateDW is disabled in config.yml, skipping integrations.");
			coinmaterialinstalled = false;
		}

		// Singleton
		instance = this;

		// Register command
		new GuildCommand();

		// Register EventListener
		Bukkit.getPluginManager().registerEvents(new EventListener(), this);
	}

	public static Guilded getInstance() {
		// Simple Singleton implementation
		return instance;
	}

	@Override
	public void onDisable() {
		// Disables plugin - saves guilds to .json file
		GuildSerializer.SaveGuild();
	}
}

package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import guilded.guilded.GuildSerializer.GuildSerializer;

/**
 * Implements handler for Guilded command /guild new
 * Usage:        /guild new
 * Requirements: none
 */
public class GuildNewCommand {
	public static void execute(CommandSender sender, String[] args) {
		// execute method - handles plugin new command
		
		// TODO: link with Mojang provided teams command set

		if (args.length >= 2) {
			// Name provided
			
			if (args.length >= 3) {
				// Prefix provided
				
				if (args.length >= 4) {
					// Color provided
					
					if (!GuildSerializer.guildExistsByCreator(sender.getName())) {
						// Player is not creator of any other guild
						
						GuildSerializer.addGuild(sender.getName(), args[1], args[2], args[3]);

						sender.sendMessage("Гильдия успешно создана");
					} else
						sender.sendMessage("Вы уже создали гильдию");
				} else
					sender.sendMessage("Введите цвет гильдии");
			} else
				sender.sendMessage("Введите префикс гильдии");
		} else
			sender.sendMessage("Введите название гильдии");
	}

	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender

		// If no arguments - list possible modify attributes
		if (args.length == 2)
			return Lists.newArrayList("<name>");

		if (args.length == 3)
			return Lists.newArrayList("<prefix>");

		// Namecoded colors
		if (args.length == 4)
			return Lists.newArrayList("aqua", "black", "blue",
					"dark_aqua", "dark_blue", "dark_gray",
					"dark_green", "dark_purple", "dark_red",
					"gold", "gray", "green", "light_purple",
					"red", "white", "yellow");

		return Lists.newArrayList();
	}
}

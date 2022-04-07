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
		
		if (args.length >= 1) {
			// Name provided
			
			if (args.length >= 2) {
				// Prefix provided
				
				if (args.length >= 3) {
					// Color provided
					
					if (!GuildSerializer.guildExists(sender.getName())) {
						// Player is not creator of any other guild
						
						// TODO: test for player in any guild and any role
						
						GuildSerializer.addGuild(sender.getName(), args[0], args[1], args[2]);
						sender.sendMessage("Гильдия создана");
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
		if (args.length == 0)
			return Lists.newArrayList("<guild name>");

		if (args.length == 1)
			return Lists.newArrayList("<guild prefix>");

		if (args.length == 2)
			return Lists.newArrayList("<guild color>");

		return Lists.newArrayList();
	}
}

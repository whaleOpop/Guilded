package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import guilded.guilded.GuildSerializer.GuildSerializer;

/**
 * Implements handler for Guilded command /guild modify
 * Usage:        /guild modify
 * Requirements: none
 */
public class GuildModifyCommand {
	public static void execute(CommandSender sender, String[] args) {
		// execute method - handles plugin modify command

		// TODO: splice modifiable attributes
		
		if (args.length >= 2) {
			if (args.length >= 3) {
				if (args.length >= 4) {
					if (GuildSerializer.guildExists(sender.getName())) {
						
						// TODO: test for player's role in guild
						GuildSerializer.addGuild(sender.getName(), args[1], args[2], args[3]);
						GuildSerializer.SaveGuild();
						sender.sendMessage("Изменения сохранены");
					} else
						sender.sendMessage("Вы не состоите в гильдии");
				} else
					sender.sendMessage("Введите новый цвет");
			} else
				sender.sendMessage("Введите новый префикс");
		} else
			sender.sendMessage("Введите новое название");
	}
	
	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender

		// If no arguments - list possible modify attributes
		if (args.length == 2)
			return Lists.newArrayList("name", "prefix", "color");
		
		// Suggest some help to player
		if (args.length > 2) {
			if(args[1].equalsIgnoreCase("name"))
				return Lists.newArrayList("<newGuildName>");
			
			if(args[1].equalsIgnoreCase("prefix"))
				return Lists.newArrayList("<newGuildPrefix>");
			
			if(args[1].equalsIgnoreCase("color"))
				return Lists.newArrayList("<newGuildColor>");
		}
		
		return Lists.newArrayList();
	}
}

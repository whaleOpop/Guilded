package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import guilded.guilded.GuildSerializer.GuildSerializer;

/**
 * Implements handler for Guilded command /guild delete
 * Usage:        /guild delete
 * Requirements: none
 */
public class GuildDeleteCommand {
	public static void execute(CommandSender sender, String[] args) {
		// execute method - handles plugin delete command
		
		if (args.length >= 2) {
			// Player provided an argument after /guild delete
			
			if (args[1].equals("confirm")) {
				// An argument is keyword confirm
				
				if (GuildSerializer.guildExists(sender.getName())) {
					// Test if player is in a guild
					
					// TODO: test player's role
					GuildSerializer.listGuild.remove(sender.getName());
					GuildSerializer.SaveGuild();
					sender.sendMessage("Гильдия успешно удалена");
				} else
					sender.sendMessage("Вы не состоите в гильдии");
			} else
				sender.sendMessage("Пожалуйста, подтвердите удаление: введите /guild delete confirm");
		} else
			sender.sendMessage("Пожалуйста, подтвердите удаление: введите /guild delete confirm");
	}
	
	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender
		
		// TODO: implement
		
		return Lists.newArrayList();
	}
}

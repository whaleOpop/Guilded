package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import guilded.guilded.GuildModel;
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
				
				String playerName = sender.getName();
				GuildModel gm = GuildSerializer.getGuildByPlayername(playerName);
				
				if (gm != null) {
					// Player is a member of the guild
					
					if (gm.testOwnership(playerName)) {
						// Test if player is the guild's creator
						
						// TODO: message player about guild's debt, 
						GuildSerializer.deleteGuild(playerName);
						sender.sendMessage(gm.nameGuild);
						sender.sendMessage("Гильдия была распущена");
					} else
						sender.sendMessage("Недостаточно прав - вы не являетесь главой гильдии");
				} else
					sender.sendMessage("Вы не состоите в гильдии");
			} else
				sender.sendMessage("Пожалуйста, подтвердите удаление: введите /guild delete confirm");
		} else
			sender.sendMessage("Пожалуйста, подтвердите удаление: введите /guild delete confirm");
	}
	
	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender
		
		// Test if sender is a guild owner
		if (args.length >= 2)
			if (GuildSerializer.guildExistsByCreator(sender.getName()))
				return Lists.newArrayList("confirm");
			
		return Lists.newArrayList();
	}
}

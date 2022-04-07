package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

/**
 * Implements handler for Guilded command /guild help
 * Usage:        /guild help
 * Requirements: none
 */
public class GuildHelpCommand {
	public static void execute(CommandSender sender, String[] args) {
		// execute method - handles plugin help command
		
		// TODO: code help to show all of the commands
		// TODO: code help for the requested command
	}
	
	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender
		// TODO: test if sender is in a guild
		// if so, test sender's role
		if (args.length == 0)
			return Lists.newArrayList("new", "delete", "modify", "request", "add", "exile", "leave", "op", "deop", "role", "list", "info", "help");
		return Lists.newArrayList();
	}
}

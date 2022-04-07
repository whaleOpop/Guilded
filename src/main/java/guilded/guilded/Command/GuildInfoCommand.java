package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

/**
 * Implements handler for Guilded command /guild info
 * Usage:        /guild info
 * Requirements: none
 */
public class GuildInfoCommand {
	public static void execute(CommandSender sender, String[] args) {
		// execute method - handles plugin info command
		
		// TODO: implement
	}
	
	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender
		// TODO: implement
		return Lists.newArrayList();
	}
}

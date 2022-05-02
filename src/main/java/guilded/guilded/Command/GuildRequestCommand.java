package guilded.guilded.Command;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.command.CommandSender;

/**
 * Implements handler for Guilded command /guild request
 * Usage:        /guild request
 * Requirements: none
 */
public class GuildRequestCommand {
	public static void execute(CommandSender sender, String[] args) {
		// execute method - handles plugin request command

			// TODO: implement
			// Name provided
		if (args.length == 2) {

		}

		}
	
	public static List<String> complete(CommandSender sender, String[] args) {
		// complete method - returns a list of all available commands to sender
		// TODO: implement
		List<String> ls = new ArrayList<String>();


		return ls;
	}
}

package guilded.guilded.Command;

import com.google.common.collect.Lists;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

/**
 * Implements upper-level handler for Guilded command /guild
 * Usage:        /guild ...
 * Requirements: none
 */
public class GuildCommand extends AbstractCommand {
	public GuildCommand() {
		super("guild");
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		// Overridden execute method - handles plugin guild ... commands
		if (args.length == 0) {
			// Guild help command
			// Pass whole args array
			GuildHelpCommand.execute(sender, args);
			return;
		}
		
		
		if (args[0].equals("help")) {
			// Also a guild help command
			// Pass whole args array
			GuildHelpCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
			return;
			
		}else if (args[0].equals("new")) {
			// Guild new command execute
			GuildNewCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
			return;
			
		} else if (args[0].equals("delete")) {
			// Guild delete command execute
			GuildDeleteCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
			return;

		} else if (args[0].equals("modify")) {
			// Guild modify command execute
			GuildModifyCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
			return;

		} else {
			sender.sendMessage("Неизвестная команда, введите /guild help!");
		}
	}

	@Override
	public List<String> complete(CommandSender sender, String[] args) {
		// Overridden complete method - returns reload as only available command

		// guild [subcommand] - presumably empty or help
		if (args.length == 0)
			GuildHelpCommand.complete(sender, args);
		if (args[0].equalsIgnoreCase("help"))
			return Lists.newArrayList();
		
		// guild [subcommand] {arguments...}
		if (args.length > 0) {
			// Complete /guild new command
			if (args[0].equalsIgnoreCase("new"))
				return GuildNewCommand.complete(sender, Arrays.copyOfRange(args, 1, args.length));

			// Complete /guild modify command
			if (args[0].equalsIgnoreCase("modify"))
				return GuildModifyCommand.complete(sender, Arrays.copyOfRange(args, 1, args.length));
		}

		return Lists.newArrayList();
	}
}

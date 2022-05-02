package guilded.guilded.Command;

import com.google.common.collect.Lists;
import org.bukkit.command.CommandSender;

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
            GuildHelpCommand.execute(sender, args);
            return;

        } else if (args[0].equals("new")) {
            // Guild new command execute
            GuildNewCommand.execute(sender, args);
            return;

        } else if (args[0].equals("list")) {
            // Guild new command execute
            GuildListCommand.execute(sender, args);
            return;


        } else if (args[0].equals("modify")) {
            // Guild modify command execute
            GuildModifyCommand.execute(sender, args);
            return;

        } else if (args[0].equals("delete")) {
            // Guild delete command execute
            GuildDeleteCommand.execute(sender, args);
            return;
        } else if (args[0].equals("op")) {
            // Guild delete command execute
            GuildRoleCommand.execute(sender, args);
            return;
        } else if (args[0].equals("add")) {
            // Guild delete command execute
            GuildAddCommand.execute(sender, args);
            return;

        } else
            sender.sendMessage("Неизвестная команда, введите /guild help!");
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        // Overridden complete method - returns reload as only available command

        // guild [subcommand] - presumably empty or help
        if (args.length == 1)
            return Lists.newArrayList("help", "new", "request", "delete", "op", "add","list");



        // guild [subcommand] {arguments...}
        if (args.length >= 2) {

            // Complete /guild help command
            if (args[0].equalsIgnoreCase("help"))
                return GuildHelpCommand.complete(sender, args);
            // Complete /guild op command
            if (args[0].equalsIgnoreCase("op"))
                return GuildRoleCommand.complete(sender, args);
            // Complete /guild list command
            if (args[0].equalsIgnoreCase("list"))
                return GuildListCommand.complete(sender, args);
            // Complete /guild new command
            if (args[0].equalsIgnoreCase("new"))
                return GuildNewCommand.complete(sender, args);

            // Complete /guild modify command
            if (args[0].equalsIgnoreCase("modify"))
                return GuildModifyCommand.complete(sender, args);

            // Complete /guild modify command
            if (args[0].equalsIgnoreCase("delete"))
                return GuildDeleteCommand.complete(sender, args);

            // Complete /guild request command
            if (args[0].equalsIgnoreCase("request"))
                return GuildRequestCommand.complete(sender, args);

            if (args[0].equalsIgnoreCase("add"))
                return GuildAddCommand.complete(sender, args);

        }

        return Lists.newArrayList();
    }
}

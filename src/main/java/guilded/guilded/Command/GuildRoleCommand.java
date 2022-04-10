package guilded.guilded.Command;

import java.util.ArrayList;
import java.util.List;

import guilded.guilded.GuildSerializer.GuildSerializer;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

/**
 * Implements handler for Guilded command /guild role
 * Usage:        /guild role
 * Requirements: none
 */
public class GuildRoleCommand {
    public static void execute(CommandSender sender, String[] args) {
		for (String guildkey: GuildSerializer.listGuild.keySet()) {
			for(String playerkey: GuildSerializer.listGuild.keySet()) {
				GuildSerializer.listGuild.get(guildkey).roleGuild.get(playerkey);
				System.out.println(GuildSerializer.listGuild.get(guildkey).roleGuild.get(playerkey));
			}
		}
        // execute method - handles plugin role command
        if (args.length >= 2) {
            if (GuildSerializer.listGuild.get(sender.getName()).testOperatorship(sender.getName())) {
                if (GuildSerializer.guildExistsByCreator(sender.getName())) {
                    if (!GuildSerializer.listGuild.get(sender.getName()).testOperatorship(args[1])) {

                    }
                } else {

                }
            } else {

            }
        }
        // TODO: implement
    }

    public static List<String> complete(CommandSender sender, String[] args) {
        // complete method - returns a list of all available commands to sender
        // TODO: implement



        List<String> ls = new ArrayList<String>();
        //if()
        //ls.add(GuildSerializer.listGuild.get(sender.getName()).roleGuild)

        return Lists.newArrayList();
    }
}

package guilded.guilded.Command;


import java.util.List;

import doublewhaleapi.dwapi.DataModels.GuildModel;
import doublewhaleapi.dwapi.DataModels.PlayerModel;

import doublewhaleapi.dwapi.Vaults.GuildVault;
import guilded.guilded.Guilded;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;


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
                GuildCommand guildCommand = new GuildCommand();
                String playerName = sender.getName();
                GuildModel gm = guildCommand.plugin.core.guildVault.getGuildByPlayer(playerName);


                if (gm != null) {
                    //Player is a member of the guild
					PlayerModel player = gm.getPlayerByName(playerName);
                    if (player.testCreatorship()) {
                        //Test if player is the guild 's creator

                        // TODO: message player about guild's debt,
						GuildVault guildStorage=guildCommand.plugin.core.guildVault;
						guildStorage.removeGuild(gm.getCreatorName());
						sender.sendMessage(gm.getGuildName());
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
		String playerName = sender.getName();
		GuildCommand guildCommand = new GuildCommand();
		GuildModel gm = guildCommand.plugin.core.guildVault.getGuildByPlayer(playerName);
		PlayerModel player = gm.getPlayerByName(playerName);
        // Test if sender is a guild owner
        if (args.length >= 2)
            if (player.testCreatorship())
                return Lists.newArrayList("confirm");

        return Lists.newArrayList();
    }
}

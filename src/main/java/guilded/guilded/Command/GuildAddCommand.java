package guilded.guilded.Command;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

/**
 * Implements handler for Guilded command /guild add
 * Usage:        /guild add
 * Requirements: none
 */
public class GuildAddCommand {
    public static void execute(CommandSender sender, String[] args) {
        // execute method - handles plugin add command
        //GuildModel guildModel = GuildSerializer.getGuildByPlayername(sender.getName());

        if (args.length == 2) {
            if (Bukkit.getPlayer(args[1]) != null) {

                //GuildSerializer.joinGuild(guildModel.nameGuild,args[1]);
                sender.sendMessage(args[1] + " Был добавлен в гильдию ");
            } else {
                sender.sendMessage("Такого игрока нет на сервере");
            }
        } else {
            sender.sendMessage("Введите игрока которого хотите добавить");
        }
        // TODO: implement
    }

    public static List<String> complete(CommandSender sender, String[] args) {
        // complete method - returns a list of all available commands to sender
        // TODO: implement
        return Lists.newArrayList();
    }
}

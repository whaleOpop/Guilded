package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;


/**
 * Implements handler for Guilded command /guild modify
 * Usage:        /guild modify
 * Requirements: none
 */
public class GuildModifyCommand {
    public static void execute(CommandSender sender, String[] args) {
        // execute method - handles plugin modify command

        // TODO: splice modifiable attributes

        //if (args.length >= 2) {
        // Player specified guild attribute to change

        String playerName = sender.getName();
        //GuildModel gm = GuildSerializer.getGuildByPlayername(playerName);

//			if (gm != null) {
//				// Guild exists/player is in a guild
//
//				if (gm.testOperatorship(playerName)) {
//					// Player is an operator/creator of the guild
//
//					if (args[1].equalsIgnoreCase("name")) {
//						// Modify name
//
//						if (args.length >= 3) {
//							// Player specified new name for a guild
//
//							GuildSerializer.modifyGuild(playerName, args[2], null, null);
//							sender.sendMessage("Изменения сохранены");
//						} else
//							sender.sendMessage("Укажите новое название для гильдии");
//
//					} else if (args[1].equalsIgnoreCase("prefix")) {
//						// Modify prefix
//
//						if (args.length >= 3) {
//							// Player specified new prefix for a guild
//
//							GuildSerializer.modifyGuild(playerName, null, "[" + args[2] + "]", null);
//							sender.sendMessage("Изменения сохранены");
//						} else
//							sender.sendMessage("Укажите новый префикс для гильдии");
//
//					} else if (args[1].equalsIgnoreCase("color")) {
//						// Modify color
//
//						if (args.length >= 3) {
//							// Player specified new color for a guild
//
//							// TODO: test for color type
//
//							GuildSerializer.modifyGuild(playerName, null, null, args[2]);
//							sender.sendMessage("Изменения сохранены");
//						} else
//							sender.sendMessage("Укажите новый цвет для гильдии");
//					} else
//						sender.sendMessage("Пожалуйста, укажите какой атрибут гильдии нужно отредактировать: name/prefix/color");
//				} else
//					sender.sendMessage("Недостаточно прав - вы не глава или оператор гильдии");
//			} else
//				sender.sendMessage("Вы не состоите в гильдии");
//		} else
//			sender.sendMessage("Пожалуйста, укажите какой атрибут гильдии нужно отредактировать: name/prefix/color");
    }

    public static List<String> complete(CommandSender sender, String[] args) {
        // complete method - returns a list of all available commands to sender

        // If no arguments - list possible modify attributes
//		if (args.length == 2)
//			return Lists.newArrayList("name", "prefix", "color");
//
//		// Suggest some help to player
//		if (args.length > 2) {
//			if(args[1].equalsIgnoreCase("name"))
//				return Lists.newArrayList("<newName>");
//
//			if(args[1].equalsIgnoreCase("prefix"))
//				return Lists.newArrayList("<newPrefix>");
//
//			if(args[1].equalsIgnoreCase("color"))
//				return Lists.newArrayList("aqua", "black", "blue",
//						"dark_aqua", "dark_blue", "dark_gray",
//						"dark_green", "dark_purple", "dark_red",
//						"gold", "gray", "green", "light_purple",
//						"red", "white", "yellow");
//		}

        return Lists.newArrayList();
    }
}


package guilded.guilded.Command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import guilded.guilded.Guilded;
//import guilded.guilded.GuildSerializer.GuildSerializer;

/**
 * Implements handler for Guilded command /guild help
 * Usage:        /guild help
 * Requirements: none
 */
public class GuildHelpCommand {
    public static void execute(CommandSender sender, String[] args) {
        // execute method - handles plugin help command

        if (args.length >= 2) {
            // Player provided a command to list help page for
            if (args[1].equalsIgnoreCase("info")) {
                sender.sendMessage("Помощь по команде help:");
                sender.sendMessage("Использование: /guild help и /guild help <command>");
                sender.sendMessage("Описание: Показывает справочную информацию о всех командах Guilded.");

            } else if (args[1].equalsIgnoreCase("info")) {
                sender.sendMessage("Помощь по команде info:");
                sender.sendMessage("Использование: /guild info");
                sender.sendMessage("Описание: Показывает информацию о всех участниках гильдии, их ролях (только для уже вступивших в гильдию игроков)");
                if (Guilded.coinmaterialinstalled) {
                    sender.sendMessage("Интеграция с CoinMaterial: Выводит список активных игроков, сумму на счету гильдии, налог на гильдию.");
                    sender.sendMessage("Сообщает стоимость добавления нового игрока в гильдию.");
                }

            } else if (args[1].equalsIgnoreCase("role")) {
                sender.sendMessage("Помощь по команде role:");
                sender.sendMessage("Использование: /guild role");
                sender.sendMessage("Описание: Показывает информацию о вашей роли в гильдии.");

            } else if (args[1].equalsIgnoreCase("list")) {
                sender.sendMessage("Помощь по команде list:");
                sender.sendMessage("Использование: /guild list");
                sender.sendMessage("Описание: Показывает список гильдий на сервере.");

            } else if (args[1].equalsIgnoreCase("new")) {
                sender.sendMessage("Помощь по команде new:");
                sender.sendMessage("Использование: /guild new <name> <prefix> <color>");
                sender.sendMessage("Описание: Создаёт гильдию с указанными параметрами.");
                if (Guilded.coinmaterialinstalled) {
                    sender.sendMessage("Интеграция с CoinMaterial: Выводит стоимость создания гильдии.");
                }

            } else if (args[1].equalsIgnoreCase("modify")) {
                sender.sendMessage("Помощь по команде modify:");
                sender.sendMessage("Использование: /guild modify [name/prefix/color] <newValue>");
                sender.sendMessage("/guild modify name <newName>     - сменить имя гильдии");
                sender.sendMessage("/guild modify prefix <newPrefix> - сменить префикс гильдии");
                sender.sendMessage("/guild modify color <newColor>   - сменить цвет гильдии");
                sender.sendMessage("Описание: Редактирует указанные атрибуты гильдии (для создателей/операторов гильдии)");

            } else if (args[1].equalsIgnoreCase("delete")) {
                sender.sendMessage("Помощь по команде delete:");
                sender.sendMessage("Использование: /guild delete");
                sender.sendMessage("/guild delete confirm - необходимо подтверждение при удалении");
                sender.sendMessage("Описание: Удаляет вашу гильдию (только для создателей гильдий)");

            } else if (args[1].equalsIgnoreCase("request")) {
                sender.sendMessage("Помощь по команде request:");
                sender.sendMessage("Использование: /guild request <playerName>");
                sender.sendMessage("Описание: Отправляет запрос на вступление в гильдию с указанным игроком.");

            } else if (args[1].equalsIgnoreCase("add")) {
                sender.sendMessage("Помощь по команде add:");
                sender.sendMessage("Использование: /guild add <playerName>");
                sender.sendMessage("Описание: Принимает запрос на вступление в гильдию указанного игрока.");

            } else if (args[1].equalsIgnoreCase("exile")) {
                sender.sendMessage("Помощь по команде exile:");
                sender.sendMessage("Использование: /guild exile <playerName>");
                sender.sendMessage("Описание: Выгоняет указанного игрока из гильдии, если он ниже по роли.");

            } else if (args[1].equalsIgnoreCase("leave")) {
                sender.sendMessage("Помощь по команде leave:");
                sender.sendMessage("Использование: /guild leave");
                sender.sendMessage("Описание: Покинуть гильдию (если создатель покидает гильдию, права передаются другому игроку гильдии)");

            } else {
                sender.sendMessage("Пожалуйста, введите команду для получения по ней справки. /guild help <command>");
            }

        } else {
            // Player issued global help page
            sender.sendMessage("Справка по командам Guilded:");
            sender.sendMessage("Чтобы получить подробную справку по команде, введите /guild help <command>.");
            sender.sendMessage("/guild help    - выводит данную справку");
            sender.sendMessage("/guild info    - узнать информацию о гильдии");
            sender.sendMessage("/guild role    - узнать свою роль в гильдии");
            sender.sendMessage("/guild list    - показать список гильдий сервера");
            sender.sendMessage("/guild new     - создать новую гильдию");
            sender.sendMessage("/guild modify  - изменить настройки гильдии");
            sender.sendMessage("/guild delete  - удалить гильдию");
            sender.sendMessage("/guild request - послать запрос вступления в гильдию");
            sender.sendMessage("/guild add     - принять запрос вступления в гильдию");
            sender.sendMessage("/guild exile   - изгнать игрока из гильдии");
            sender.sendMessage("/guild leave   - покинуть гильдию");

            if (Guilded.coinmaterialinstalled) {
                // List help for CoinMaterial integrated commands
                sender.sendMessage("Интеграция с CoinMaterial:");
                sender.sendMessage("/wallet guild <amount>           - Снимает со счёта гильдии указанную сумму");
                sender.sendMessage("/pay guild <amount> <playerName> - Переводит на счёт гильдии указанную сумму");
                sender.sendMessage("/money guild                     - Выводит информацию о счёте вашей гильдии");
            }
        }
    }

    public static List<String> complete(CommandSender sender, String[] args) {
        // complete method - returns a list of all available commands to sender

//		String playerName = sender.getName();
//		GuildModel gm = GuildSerializer.getGuildByPlayername(playerName);
        List<String> ls = Lists.newArrayList();
//
//		System.out.println(gm.toString());
//		System.out.println(gm.testOwnership(playerName));
//		System.out.println(gm.testOperatorship(playerName));
//		System.out.println(gm.testMembership(playerName));
//		if (args.length == 2){
//		//if (gm != null) {
//			// Player is in a guild
//
//			// Player has basic rights in a guild
//			ls.add("role");
//			ls.add("leave");
//
//			if (gm.testOwnership(playerName)) {
//				// Player is guild creator - guild delete command is accessible
//				ls.add("delete");
//				ls.add("op");
//				ls.add("deop");
//			}
//
//			if (gm.testOperatorship(playerName)) {
//				// Player is guild operator - all modify guild commands are accessible
//				ls.add("modify");
//				ls.add("add");
//				ls.add("exile");
//			}
//
//			if (gm.testMembership(playerName)) {
//				// Player is a member of the guild
//				ls.add("info");
//			}
//		} else {
//			// Player is not in a guild - he might create new, get help, list all guilds or send request to enter one
//			ls.add("request");
//			ls.add("new");
//		}
//
//		// Commands available anyway
//		ls.add("list");
//		ls.add("help");


        return ls;
    }
}

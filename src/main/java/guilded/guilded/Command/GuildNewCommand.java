package guilded.guilded.Command;

import com.google.common.collect.Lists;
import guilded.guilded.GuildModel;
import guilded.guilded.GuildedSerialized.GuildSerializer;
import org.bukkit.command.CommandSender;

import java.util.List;

public class GuildNewCommand extends AbstractCommand {
    public GuildNewCommand() {
        super("guild");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/guilded help");
            return;
        }
        //Add Guild
        if (args[0].equals("new")) {
            if (args.length != 1) {
                if (args.length != 2) {
                    if (args.length != 3) {
                        if (!GuildSerializer.playerExists(sender.getName())) {

                            GuildSerializer.addGuilded(sender.getName(), args[1], args[2], args[3]);
                            sender.sendMessage("Гильдия создана");
                            return;
                        } else {
                            sender.sendMessage("Вы уже создали гильдии");
                            return;
                        }
                    } else {
                        sender.sendMessage("Введите цвет гильдии");
                        return;
                    }
                } else {
                    sender.sendMessage("Введите префикс гильдии");
                    return;
                }
            } else {
                sender.sendMessage("Название гильдии");
                return;
            }

            //Delete Guild
        } else if (args[0].equals("delete")) {
            if (args.length != 1) {
                if (args[1].equals("confirm")) {
                    if (GuildSerializer.playerExists(sender.getName())) {
                        GuildSerializer.listGuided.remove(sender.getName());
                        GuildSerializer.SaveGuild();
                        sender.sendMessage("Гильдия успешно удалена");
                        return;
                    } else {
                        sender.sendMessage("У вас нет гильдии");
                        return;
                    }

                } else {
                    sender.sendMessage("Потвердите удаление");
                    return;
                }
            } else {
                sender.sendMessage("Потвердите удаление");
            }


        } else if (args[0].equals("modify")) {

            if (args.length != 1) {
                if (args.length != 2) {
                    if (args.length != 3) {
                        if (GuildSerializer.playerExists(sender.getName())) {
                            GuildSerializer.addGuilded(sender.getName(), args[1], args[2], args[3]);
                            GuildSerializer.SaveGuild();
                            sender.sendMessage("Изменения сохранены");
                        }else {
                            sender.sendMessage("У вас нет гильдии");
                        }
                    }else {
                        sender.sendMessage("Введите новый цвет");
                    }
                }else {
                    sender.sendMessage("Введите новый префикс");
                }
            }else {
                sender.sendMessage("Введите новое название");
            }


        } else {

        }
    }


    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        // Overridden complete method - returns reload as only available command
        if (args.length == 1)
            return Lists.newArrayList("new", "delete","modify");
        if (args[0].equals("new") && args.length == 2)
            return Lists.newArrayList("<nameGuild>");
        if (args[0].equals("new") && args.length == 3)
            return Lists.newArrayList("<prefixGuild>");
        if (args[0].equals("new") && args.length == 4)
            return Lists.newArrayList("<colorGuild>");


        if (args[0].equals("delete") && args.length == 2)
            return Lists.newArrayList("<confirm?>");


        if (args[0].equals("modify") && args.length == 2)
            return Lists.newArrayList("<newNameGuild>");
        if (args[0].equals("modify") && args.length == 3)
            return Lists.newArrayList("<newPrefixGuild>");
        if (args[0].equals("modify") && args.length == 4)
            return Lists.newArrayList("<newColorGuild>");

        return Lists.newArrayList();
    }
}

package guilded.guilded.Command;

import com.google.common.collect.Lists;
import guilded.guilded.GuildedSerialized.Serialized;
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
                        if (!Serialized.playerExists(sender.getName())) {

                            Serialized.addGuilded(sender.getName(), args[1], args[2], args[3]);
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
        } else {
            if (args[0].equals("delete")) {
                if(args.length != 1) {
                    if (args[1].equals("confirm")) {
                        if (Serialized.playerExists(sender.getName())) {
                            Serialized.listGuided.remove(sender.getName());
                            Serialized.SaveGuild();
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
                }else {
                    sender.sendMessage("Потвердите удаление");
                }
            } else {
                sender.sendMessage("/guild help");
                return;
            }
        }


    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        // Overridden complete method - returns reload as only available command
        if (args.length == 1)
            return Lists.newArrayList("new", "delete");
        if (args[0].equals("new") && args.length == 2)
            return Lists.newArrayList("<nameGuild>");
        if (args[0].equals("new") && args.length == 3)
            return Lists.newArrayList("<prefixGuild>");
        if (args[0].equals("new") && args.length == 4)
            return Lists.newArrayList("<colorGuild>");

        if (args[0].equals("delete") && args.length == 2)
            return Lists.newArrayList("<confirm?>");

        return Lists.newArrayList();
    }
}

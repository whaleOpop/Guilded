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
        if (args[0].equals("new")) {
            if (args.length!=1) {
                if (args.length!=2) {
                    if (args.length!=3) {
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
        } else {
            sender.sendMessage("/guild help");
        }

    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        // Overridden complete method - returns reload as only available command
        if (args.length == 1)
            return Lists.newArrayList("new");
        if (args.length == 2)
            return Lists.newArrayList("<nameGuild>");
        if (args.length == 3)
            return Lists.newArrayList("<prefixGuild>");
        if (args.length == 4)
            return Lists.newArrayList("<colorGuild>");
        return Lists.newArrayList();
    }
}

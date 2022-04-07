package guilded.guilded.Command;

import guilded.guilded.Guilded;
import org.bukkit.command.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements abstract Guilded command
 * Usage:        Extending this class
 * Requirements: none
 */
public abstract class AbstractCommand implements CommandExecutor, TabCompleter {
    public AbstractCommand(String command) {
        // Constructor - sets executor of CoinMaterial Command
        PluginCommand pluginCommand = Guilded.getInstance().getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
        }
    }

    // Abstract execute method
    public abstract void execute(CommandSender sender, String label, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // onCommand listener override - executes command
        execute(sender, label, args);
        return true;
    }

    public List<String> complete(CommandSender sender, String args[]) {
        // Abstract complete method - plug for command args completion
        return null;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // onTabComplete method - filters command args completion via Tab key
        return filter(complete(sender, args), args);
    }

    private List<String> filter(List<String> list, String[] args) {
        // Filter method - filters to returned result ArrayList args for incomplete
        // passed ones
        if (list == null)
            return null;

        String last = args[args.length - 1];
        List<String> result = new ArrayList<String>();

        for (String arg : list) {
            if (arg.toLowerCase().startsWith(last.toLowerCase()))
                result.add(arg);
        }

        return result;
    }

    public boolean isNumber(String str) {
		// isNumber support method - tests if provided str contains number
		if (str != null && !str.isEmpty()) {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) return false;
			}
			return true;
		}
		return true;
	}

    private String readConfig(String ns, String key) {
		// readConfig method - reads value from config for given namespace and key pair 
		return Guilded.getInstance().getConfig().getString(ns + "." + key);
	}
	
	public String getLocal(String commandNS, String localize) {
		// getLocal method - returns localized string from config.yml under 'localization' namespace
		return readConfig("localization", commandNS + "." + localize);
	}
	
	public String getSettings(String settingsType, String settingName) {
		// getSettings method - returns settings values from config.yml under 'settings' namespace
		return readConfig("settings", settingsType + "." + settingName);
	}
}

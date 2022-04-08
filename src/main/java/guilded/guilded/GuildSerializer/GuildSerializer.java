package guilded.guilded.GuildSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import guilded.guilded.GuildModel;

import java.io.*;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;

/**
 * Implements Hashmapper to work with player guilds in .json files
 * Usage:        load/save HashMap of GuildModels
 * Requirements: com.google.gson
 */
public class GuildSerializer {
	public enum RoleGuild {
		Creator, Opperator, Member, Requested
	};

	static final String filePath = "plugins/DWdatabases/Guild.json";
	public static HashMap<String, GuildModel> listGuild = new HashMap<String, GuildModel>();
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static boolean guildExistsByCreator(String creatorName) {
		// playerExists method - return true if guild with given name is found in listGuild HashMap
		return listGuild.get(creatorName) != null;
	}
	
	public static void addGuild(String creatorName, String nameGuild, String prefixGuild, String colorGuild) {
		// addGuild method - adds a guild to the listGuild and saves all to .json
		
		// Empty roleGuild HashMap
		HashMap<String, RoleGuild> roleGuild = new HashMap<String, RoleGuild>();
		
		// Add command issuer as Creator, add guild to listGuild
		roleGuild.put(creatorName, RoleGuild.Creator);
		GuildSerializer.listGuild.put(creatorName, new GuildModel(nameGuild, prefixGuild, colorGuild, roleGuild));
		
		// Execute Minecraft:team commands
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		Server server = Bukkit.getServer();
		
		server.dispatchCommand(console, "team add "    + creatorName + " {\"text\":\"" + nameGuild + "\"}");
		server.dispatchCommand(console, "team modify " + creatorName + " prefix [{\"text\":\"[" + prefixGuild + "] \",\"color\":\"" + colorGuild + "\"}]");
		server.dispatchCommand(console, "team modify " + creatorName + " displayName [{\"text\" : \"" + nameGuild + ",\"color\":\"" + colorGuild + "\"}]");
		server.dispatchCommand(console, "team join "   + creatorName + " " + creatorName);
		
		// Save all guilds
		SaveGuild();
	}
	
	public static void modifyGuild(String playerName, String nameGuild, String prefixGuild, String colorGuild) {
		// modifyGuild method - modifies settings of GuildModel of a given playerName creator,
		// if any of arguments except playerName is null - does not changes them
		
		GuildModel gm = listGuild.get(playerName);
		
		// Check for any null arguments
		if (nameGuild == null) nameGuild = gm.nameGuild;
		if (prefixGuild == null) prefixGuild = gm.prefixGuild;
		if (colorGuild == null) colorGuild = gm.colorGuild;
		
		// Modify guild
		gm.nameGuild = nameGuild;
		gm.prefixGuild = prefixGuild;
		gm.colorGuild = colorGuild;
		
		// Put to the list
		listGuild.put(playerName, gm);
		
		// Execute Minecraft:team commands
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		Server server = Bukkit.getServer();
		
		server.dispatchCommand(console, "team modify " + playerName + " prefix [{\"text\":\"[" + prefixGuild + "] \",\"color\":\"" + colorGuild + "\"}]");
		server.dispatchCommand(console, "team modify " + playerName + " displayName [{\"text\" : \"" + nameGuild + ",\"color\":\"" + colorGuild + "\"}]");
		
		// Save all guilds
		GuildSerializer.SaveGuild();
	}
	
	public static void deleteGuild(String creatorName) {
		// deleteGuild method - removes guild from listGuild with ownerName
		
		// Remove from listGuild and Minecraft:team
		GuildSerializer.listGuild.remove(creatorName);
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "team remove " + creatorName);
		
		// TODO: Implement CoinMaterial guild wallet remove (CoinMaterial support)
		
		// Save guilds
		GuildSerializer.SaveGuild();
	}
	
	
	public static GuildModel getGuildByPlayername(String playerName) {
		// getGuildByPlayerRole method - returns GuildModel associated with player if not found, returns null
		for (GuildModel guild : listGuild.values())
			if (guild.hasPlayer(playerName)) 
				return guild;
		return null;
	}

	@SuppressWarnings("unchecked") // Some Infer Generic Type bullcrap, shut up pls
	public static void LoadGuild() {
		// Loads guild data listGuild from .json file
		
		// Checks if path even exists, if not, creates directories and Guild.json
		File fullPath = new File(filePath);
		if (!fullPath.exists()) {
			fullPath.getParentFile().mkdirs();
			try {
				FileWriter writer = new FileWriter(filePath);
				writer.write("");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileReader reader = new FileReader(filePath);
			listGuild = gson.fromJson(reader, HashMap.class);
			if (listGuild == null) {
				listGuild = new HashMap<String, GuildModel>();
			}
			System.out.println(listGuild);
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void SaveGuild() {
		// Saves guild data listGuild to .json file
		try {
			FileWriter file = new FileWriter(filePath);
			String json = gson.toJson(listGuild);
			file.write(json);
			file.flush();
			file.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}

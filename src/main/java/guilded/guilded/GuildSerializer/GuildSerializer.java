package guilded.guilded.GuildSerializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import guilded.guilded.GuildModel;

import java.io.*;
import java.util.HashMap;

/**
 * Implements Hashmapper to work with player guilds in .json files
 * Usage:        load/save HashMap of GuildModels
 * Requirements: com.google.gson
 */
public class GuildSerializer {
	public enum RoleGuild {
		Creator, Opperator, Member, Requested, Left, Exiled
	};

	static final String filePath = "plugins/DWdatabases/Guild.json";
	public static HashMap<String, GuildModel> listGuild = new HashMap<String, GuildModel>();
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public static boolean guildExists(String name) {
		// playerExists method - return true if guild with given name is found in listGuild HashMap
		return listGuild.get(name) != null;
	}
	
	public static void addGuild(String playerName, String nameGuild, String prefixGuild, String colorGuild) {
		// addGuild method - adds a guild to the listGuild and saves all to .json
		HashMap<String, RoleGuild> roleGuild = new HashMap<>();
		roleGuild.put(playerName, RoleGuild.Creator);
		GuildSerializer.listGuild.put(playerName, new GuildModel(nameGuild, prefixGuild, colorGuild, roleGuild));
		SaveGuild();
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

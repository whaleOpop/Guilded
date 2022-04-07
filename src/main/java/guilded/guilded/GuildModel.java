package guilded.guilded;

import guilded.guilded.GuildSerializer.GuildSerializer;

import java.util.HashMap;

/**
 * Implements GuildModel to save guilds data to
 * Usage:        class constructor
 * Requirements: GuildSerializer.RoleGuild
 */
public class GuildModel {

	String nameGuild;
	String prefixGuild;
	String colorGuild;
	HashMap<String, GuildSerializer.RoleGuild> roleGuild = new HashMap<>();

	public GuildModel(String nameGuild, String prefixGuild, String colorGuild, HashMap<String, GuildSerializer.RoleGuild> roleGuild) {
		// Simple constructor
		this.nameGuild = nameGuild;
		this.prefixGuild = prefixGuild;
		this.colorGuild = colorGuild;
		this.roleGuild = roleGuild;
	}
}
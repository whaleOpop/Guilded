package guilded.guilded;

import guilded.guilded.GuildSerializer.GuildSerializer.RoleGuild;

import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * Implements GuildModel to save guilds data to
 * Usage:        class constructor
 * Requirements: GuildSerializer.RoleGuild
 */
public class GuildModel {
	// TODO: add online support

	public String nameGuild;
	public String prefixGuild;
	public String colorGuild;
	public HashMap<String, RoleGuild> roleGuild = new HashMap<String, RoleGuild>();
	public List<String> activePlayers = Lists.newArrayList();

	public GuildModel(String nameGuild, String prefixGuild, String colorGuild, HashMap<String, RoleGuild> roleGuild) {
		// Simple constructor
		this.nameGuild = nameGuild;
		this.prefixGuild = prefixGuild;
		this.colorGuild = colorGuild;
		this.roleGuild = roleGuild;
		this.activePlayers = Lists.newArrayList();
	}
	
	public boolean hasPlayer(String nickname) {
		// hasPlayer method - returns true if player is in this guild
		return roleGuild.get(nickname) != null;
	}
	
	public boolean testMembership(String nickname) {
		// testMemberShip method - returns true if player is a guild Member/Operator/Creator
		RoleGuild rg = roleGuild.get(nickname);
		if (rg != null) {
			return (rg == RoleGuild.Member) || (rg == RoleGuild.Opperator) || (rg == RoleGuild.Creator);
		} else {
			return false;
		}
	}
	
	public boolean testOperatorship(String nickname) {
		// testOperatorShip method - returns true if player is a guild Operator/Creator
		RoleGuild rg = roleGuild.get(nickname);
		if (rg != null) {
			return (rg == RoleGuild.Opperator) || (rg == RoleGuild.Creator);
		} else {
			return false;
		}
	}
	
	public boolean testOwnership(String nickname) {
		// testOwnerShip method - returns true if player is a guild Creator
		RoleGuild rg = roleGuild.get(nickname);
		if (rg != null) {
			return rg == RoleGuild.Creator;
		} else {
			return false;
		}
	}
}
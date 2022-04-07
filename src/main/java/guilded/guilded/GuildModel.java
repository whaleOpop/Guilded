package guilded.guilded;

import guilded.guilded.GuildedSerialized.GuildSerializer;

import java.util.HashMap;

public class GuildModel {

     String nameGuild;
     String prefixGuild;
     String colorGuild;
     Integer coinGuild;

    HashMap<String, GuildSerializer.RoleGuid> roleGuild = new HashMap<>();

    public GuildModel(String nameGuild, String prefixGuild, String colorGuild, Integer coinGuild, HashMap<String, GuildSerializer.RoleGuid> roleGuild) {
        this.nameGuild = nameGuild;
        this.prefixGuild = prefixGuild;
        this.colorGuild = colorGuild;
        this.coinGuild = coinGuild;
        this.roleGuild = roleGuild;
    }

    public GuildModel(String prefixGuild) {
        this.prefixGuild = prefixGuild;
    }
}

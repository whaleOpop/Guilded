package guilded.guilded;

import guilded.guilded.GuildedSerialized.Serialized;

import java.util.HashMap;

public class GuildModule {

    String nameGuild;
    String prefixGuild;
    String colorGuild;
    Integer coinGuild;

    HashMap<String, Serialized.RoleGuid> roleGuild = new HashMap<>();

    public GuildModule(String nameGuild, String prefixGuild, String colorGuild, Integer coinGuild, HashMap<String, Serialized.RoleGuid> roleGuild) {
        this.nameGuild = nameGuild;
        this.prefixGuild = prefixGuild;
        this.colorGuild = colorGuild;
        this.coinGuild = coinGuild;
        this.roleGuild = roleGuild;
    }


}

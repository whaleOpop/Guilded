package guilded.guilded.GuildedSerialized;

import com.google.gson.Gson;
import guilded.guilded.GuildModule;

import java.io.*;
import java.util.HashMap;

public class Serialized {
     public enum RoleGuid{
        Creator,Opped,Added,Requested,Left,Exiled
     };
    static final String filePath="plugins/DWdatabases/Guilded.json";
    public static HashMap<String, GuildModule> listGuided = new HashMap<>();
    static Gson gson =new Gson();
    public static boolean playerExists(String name) {
        // playerExists method - return true if player with given name is found in
        // playerCoin HashMap
        return listGuided.get(name) != null;
    }
    public static void LoadGuild() {
        // Checks if path even exists, if not, creates directories and Coin.json
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
            listGuided = gson.fromJson(reader, HashMap.class);
            if(listGuided==null){
                 listGuided = new HashMap<>();
            }
            System.out.println(listGuided);
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void SaveGuild(){
        try {
            FileWriter file = new FileWriter(filePath);
            String json = gson.toJson(listGuided);
            file.write(json);
            file.flush();
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static void addGuilded(String playerName,String nameGuild, String prefixGuild,String colorGuild){

        HashMap<String, RoleGuid> roleGuild=new HashMap<>();
        roleGuild.put(playerName,RoleGuid.Creator);
        Serialized.listGuided.put(playerName, new GuildModule(nameGuild,prefixGuild,colorGuild,3,roleGuild));
        SaveGuild();
    }
}

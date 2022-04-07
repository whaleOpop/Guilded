package guilded.guilded.CoinSerializer;

import com.google.common.collect.Lists;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Implements Hashmapper to work with player wallets in .json files (Part of CoinMaterial integration)
 * Usage:        load/save HashMap of user wallets
 * Requirements: com.google.gson
 */
public class CoinSerializer {

	static final String filePath = "plugins/DWdatabases/Coin.json";
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static HashMap<String, Double> playerCoin = new HashMap<>();

    public static boolean walletExists(String name) {
        // walletExists method - return true if player with given name is found in
        // playerCoin HashMap as key
        return playerCoin.get(name) != null;
    }
    
    public static List<String> getAllPlayers() {
    	// getAllPlayers method - returns list of all players with wallets
    	List<String> ls = Lists.newArrayList();
    	for(String name : playerCoin.keySet()) {
    		// Get all players - exclude all guild wallets (Guilded Integration)
    		if (!name.startsWith("!guild_")) {
    			ls.add(name);
    		}
    	}
    	
    	return ls;
    }
    
    public static Double getGuildCoin(String name) {
        // getGuildCoin method - handles getting wallet value of guilds, if guild with
        // given name doesn`t have a wallet - create one with 0 coins
    	// Part of Guilded Integration
    	String guild = "!guild_" + name;
        if (walletExists(guild)) {
            return playerCoin.get(guild);
        } else {
            playerCoin.put(guild, 0.0);
            return 0.0;
        }
    }

    public static Double getPlayerCoin(String name) {
        // getPlayerCoin method - handles getting wallet value of Player, if Player with
        // given name doesn`t have a wallet - create one with 0 coins
        if (walletExists(name)) {
            return playerCoin.get(name);
        } else {
            playerCoin.put(name, 0.0);
            return 0.0;
        }
    }
    
    public static void performGuildCoinOperation(String name, Double amount, BiFunction<Double, Double, Double> bFunc) {
    	// Performs put and merge operation on playerCoin HashMap with a given bFunction - 'amount' and built-in walletValue as operands
    	// Warning: SaveCoin() is not called!
    	// Guild method
    	
    	// Call getPlayerCoin in case somehow player does not have a wallet
    	CoinSerializer.getPlayerCoin(name);
    	CoinSerializer.playerCoin.put(name, CoinSerializer.playerCoin.merge(name, amount, bFunc));
    }
    
    public static void performCoinOperation(String name, Double amount, BiFunction<Double, Double, Double> bFunc) {
    	// Performs put and merge operation on playerCoin HashMap with a given bFunction - 'amount' and built-in walletValue as operands
    	// Warning: SaveCoin() is not called!
    	
    	// Call getPlayerCoin in case somehow player does not have a wallet
    	CoinSerializer.getPlayerCoin(name);
    	CoinSerializer.playerCoin.put(name, CoinSerializer.playerCoin.merge(name, amount, bFunc));
    }

    @SuppressWarnings("unchecked") // Could not do anything to .fromJson to fix Infer Generic Type...
    public static void LoadCoin() {
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
    	
    	// Loads wallet data playerCoin from .json file
    	try {
    		FileReader reader = new FileReader(filePath);
            playerCoin = gson.fromJson(reader, HashMap.class);
            // Fix fromJson
            if (playerCoin == null) {
            	playerCoin = new HashMap<String, Double>();
            }
			reader.close();
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void SaveCoin() {
        // Saves wallet data playerCoin to .json file
        try {
        	FileWriter writer = new FileWriter(filePath);
            String json = gson.toJson(playerCoin);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

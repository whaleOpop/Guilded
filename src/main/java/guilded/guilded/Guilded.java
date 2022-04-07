package guilded.guilded;


import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class Guilded extends JavaPlugin {
    private static Guilded instance;
    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }
    public static Guilded getInstance() {
        // Simple Singleton implementation
        return instance;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

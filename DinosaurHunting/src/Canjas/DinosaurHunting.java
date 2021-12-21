package Canjas;

import org.bukkit.plugin.java.JavaPlugin;

public class DinosaurHunting extends JavaPlugin {
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new GenerationListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
    }
}

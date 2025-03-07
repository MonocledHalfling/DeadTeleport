package me.seoungmin.deadLocation;

import me.seoungmin.deadLocation.commands.TeleportDeathLocationCommand;
import me.seoungmin.deadLocation.events.DeathListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class DeadLocation extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("DeadLocation Plugin Enabled");
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        registerCommand("dl", new TeleportDeathLocationCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info("DeadLocation Plugin Disabled");
    }

    private void registerCommand(String name, TeleportDeathLocationCommand command) {
        try {
            Field commandMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getServer());
            commandMap.register(name, command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

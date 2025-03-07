package me.seoungmin.deadLocation.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.HashMap;
import java.util.UUID;

public class DeathListener implements Listener {
    private static final HashMap<UUID, Location> deathLocations = new HashMap<>();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        Location deathLocation = player.getLocation();

        deathLocations.put(player.getUniqueId(), deathLocation);
        String xyz = "월드: " + deathLocation.getWorld().getName() +
                ", 좌표: " + deathLocation.getBlockX() + ", " + deathLocation.getBlockY() + ", " + deathLocation.getBlockZ();
        String text = "[ ! ] 사망위치 저장됨! '/dl'을(를) 입력하면 해당 위치로 이동합니다!\n" + xyz;
        player.sendMessage(text);
    }

    public static Location getDeathLocation(UUID uuid) {
        return deathLocations.get(uuid);
    }
}

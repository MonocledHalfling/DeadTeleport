package me.seoungmin.deadLocation.commands;

import me.seoungmin.deadLocation.events.DeathListener;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportDeathLocationCommand extends Command {

    public TeleportDeathLocationCommand() {
        super("dl");
        setDescription("사망 위치로 이동하는 명령어");
        setUsage("/dl");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("콘솔에서는 사용할 수 없습니다.");
            return false;
        }

        Location location = DeathListener.getDeathLocation(player.getUniqueId());

        if (location == null) {
            player.sendMessage("사망 위치가 저장되어 있지 않습니다!");
            return false;
        }

        location.setY(location.getY() + 1);
        player.teleport(location);
        player.sendMessage("사망 위치로 이동했습니다!");
        return true;
    }
}

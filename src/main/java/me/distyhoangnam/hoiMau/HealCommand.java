package me.distyhoangnam.hoimau;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private final HoiMau plugin;

    public HealCommand(HoiMau plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Lệnh này chỉ dành cho người chơi!");
            return true;
        } else {
            if (this.plugin.getHealManager().canHeal(player)) {
                this.plugin.getHealManager().healPlayer(player);
            }
            return true;
        }
    }
}
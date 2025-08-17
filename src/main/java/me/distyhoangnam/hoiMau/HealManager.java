package me.distyhoangnam.hoimau;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class HealManager {
    private final HoiMau plugin;
    private final Map<UUID, Long> cooldowns = new HashMap();
    private static final long COOLDOWN_TIME = 60L;

    public HealManager(HoiMau plugin) {
        this.plugin = plugin;
    }

    public boolean canHeal(Player player) {
        if (player.hasPermission("hoimau.use")) {
            Long cooldownTime = (Long)this.cooldowns.get(player.getUniqueId());
            if (cooldownTime != null) {
                long timeLeft = (cooldownTime - System.currentTimeMillis()) / 1000L;
                if (timeLeft > 0L) {
                    String var10001 = String.valueOf(ChatColor.RED);
                    player.sendMessage(var10001 + "Vui lòng đợi " + timeLeft + " giây nữa!");
                    return false;
                }
            }

            return true;
        } else {
            player.sendMessage(String.valueOf(ChatColor.RED) + "Bạn không có quyền sử dụng lệnh này!");
            return false;
        }
    }

    public void healPlayer(Player player) {
        double maxHealth = player.getMaxHealth();
        double healAmount = maxHealth * 0.5; //Đoạn này chỉnh số máu
        double newHealth = player.getHealth() + healAmount;

        if (newHealth > maxHealth)
        {
            newHealth = maxHealth;
        }

        player.setHealth(newHealth);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
        player.sendTitle(String.valueOf(ChatColor.GREEN) + "Hồi Máu!", String.valueOf(ChatColor.YELLOW) + "Bạn đã được hồi 50% máu hiện tại", 10, 30, 10);

        int cooldownSeconds = plugin.getConfig().getInt("cooldown", 60);
        long cooldownMillis = cooldownSeconds * 1000L;

        this.cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + cooldownMillis);
    }
}
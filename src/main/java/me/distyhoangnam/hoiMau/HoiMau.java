package me.distyhoangnam.hoimau;

import org.bukkit.plugin.java.JavaPlugin;

public class HoiMau extends JavaPlugin {
    private HealManager healManager;

    public HoiMau() {
    }

    public void onEnable() {
        this.healManager = new HealManager(this);
        this.getCommand("hoimau").setExecutor(new HealCommand(this));
        this.getLogger().info("đã được kích hoạt!");
        this.saveDefaultConfig();
    }

    public void onDisable() {
        this.getLogger().info("đã được tắt!");
    }

    public HealManager getHealManager() {
        return this.healManager;
    }
}
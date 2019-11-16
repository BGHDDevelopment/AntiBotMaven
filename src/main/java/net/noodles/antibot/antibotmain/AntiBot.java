package net.noodles.antibot.antibotmain;

import net.noodles.antibot.antibotmain.Utils.Logger;
import net.noodles.antibot.antibotmain.Utils.MetricsLite;
import net.noodles.antibot.antibotmain.Utils.Settings;
import net.noodles.antibot.antibotmain.Utils.UpdateChecker;
import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import java.io.File;

public class AntiBot extends JavaPlugin implements Listener
{
    public static AntiBot plugin;
    private UpdateChecker checker;
    private static AntiBot instance;


    public void onEnable() {
        Logger.log(Logger.LogLevel.OUTLINE, "********************");
        Logger.log(Logger.LogLevel.INFO, "Initializing AntiBot Version: " + Settings.VERSION);
        Logger.log(Logger.LogLevel.INFO, "Created by: " + Settings.DEVELOPER_NAME);
        Logger.log(Logger.LogLevel.INFO, "Website: " + Settings.DEVELOPER_URL);
        Logger.log(Logger.LogLevel.INFO, "Spigot Link: " + Settings.PLUGIN_URL);
        Logger.log(Logger.LogLevel.INFO, "Support Link: " + Settings.SUPPORT_DISCORD_URL);
        Logger.log(Logger.LogLevel.OUTLINE, "********************");
        Logger.log(Logger.LogLevel.INFO, "Plugin Loading...");
        Logger.log(Logger.LogLevel.INFO, "Registering Managers...");
        AntiBot.plugin = this;
        instance = this;
        MetricsLite metrics = new MetricsLite(this);
        Logger.log(Logger.LogLevel.INFO, "Managers Registered!");
        Logger.log(Logger.LogLevel.INFO, "Registering Listeners...");
        getServer().getPluginManager().registerEvents(new Events(), this);
        Logger.log(Logger.LogLevel.INFO, "Listeners Registered!");
        Logger.log(Logger.LogLevel.INFO, "Loading Config's...");
        this.createConfig();
        Logger.log(Logger.LogLevel.INFO, "Config's Registered!");
        Logger.log(Logger.LogLevel.SUCCESS, "AntiBot Version: " + Settings.VERSION + " Loaded.");
        this.setEnabled(true);
        Logger.log(Logger.LogLevel.OUTLINE, "********************");
        this.setEnabled(true);
        Logger.log(Logger.LogLevel.INFO, "Checking for updates...");
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                Logger.log(Logger.LogLevel.OUTLINE, "********************");
                Logger.log(Logger.LogLevel.WARNING, ("AntiBot is outdated!"));
                Logger.log(Logger.LogLevel.WARNING, ("Newest version: " + this.checker.getLatestVersion()));
                Logger.log(Logger.LogLevel.WARNING, ("Your version: " + Settings.VERSION));
                Logger.log(Logger.LogLevel.WARNING, ("Please Update Here: " + Settings.PLUGIN_URL));
                Logger.log(Logger.LogLevel.OUTLINE, "********************");
            } else {
                Logger.log(Logger.LogLevel.SUCCESS, "AntiBot is up to date!");
            }
        }

    }

    @Override
    public void onDisable() {
    }

    public static AntiBot getPlugin() {
        return (AntiBot) getPlugin((Class) AntiBot.class);
    }

    private void createConfig(){
        try{
            if(!getDataFolder().exists()) getDataFolder().mkdirs();

            File file = new File(getDataFolder(), "config.yml");
            if(!file.exists()){
                saveDefaultConfig();
            }

        }catch(Exception exception){
            getLogger().info(exception.getMessage());
        }
    }


    public static AntiBot getInstance() {
        return instance;
    }

}

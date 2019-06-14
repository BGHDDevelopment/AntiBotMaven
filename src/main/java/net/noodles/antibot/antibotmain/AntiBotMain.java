package net.noodles.antibot.antibotmain;

import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import java.io.File;

public class AntiBotMain extends JavaPlugin implements Listener
{
    public static AntiBotMain plugin;
    private UpdateCheckerAntiBot checker;
    private static AntiBotMain instance;


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
        AntiBotMain.plugin = this;
        instance = this;
        MetricsLite metrics = new MetricsLite(this);
        Logger.log(Logger.LogLevel.INFO, "Managers Registered!");
        Logger.log(Logger.LogLevel.INFO, "Registering Listeners...");
        getServer().getPluginManager().registerEvents(new AntiBotEvents(), this);
        Logger.log(Logger.LogLevel.INFO, "Listeners Registered!");
        Logger.log(Logger.LogLevel.INFO, "Loading Config's...");
        this.createConfig();
        Logger.log(Logger.LogLevel.INFO, "Config's Registered!");
        Logger.log(Logger.LogLevel.SUCCESS, "AntiBot Version: " + Settings.VERSION + " Loaded.");
        this.setEnabled(true);
        Logger.log(Logger.LogLevel.OUTLINE, "********************");
        this.setEnabled(true);
        Logger.log(Logger.LogLevel.INFO, "Checking for updates...");
        this.checker = new UpdateCheckerAntiBot(this);
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

    public static AntiBotMain getPlugin() {
        return (AntiBotMain) getPlugin((Class) AntiBotMain.class);
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


    public static AntiBotMain getInstance() {
        return instance;
    }

}

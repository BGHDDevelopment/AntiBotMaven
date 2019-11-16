package net.noodles.antibot.antibotmain.Utils;

import net.noodles.antibot.antibotmain.AntiBot;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoin implements Listener
{

    public UpdateChecker checker;
    private AntiBot main;

    public UpdateJoin(AntiBot main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    @EventHandler
    public void onDevJoin(PlayerJoinEvent e) { //THIS EVENT IS USED FOR DEBUG REASONS ONLY!
        Player p = e.getPlayer();
        if (p.getName().equals("Noodles_YT")) {
            p.sendMessage(ChatColor.RED + "BGHDDevelopment Debug Message");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GREEN + "This server is using AntiBot" + " version " + Settings.VERSION);
            p.sendMessage(ChatColor.GREEN + "The newest version is " + this.checker.getLatestVersion());
            p.sendMessage(" ");

        } else {
            return;
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	if (p.hasPermission("antibot.update")) {
    		if (AntiBot.getPlugin().getConfig().getBoolean("Update.Enabled") == true){
    		this.checker = new UpdateChecker(AntiBot.plugin);
                        if (this.checker.isConnected()) {
                            if (this.checker.hasUpdate()) {
                            	p.sendMessage(ChatColor.GRAY + "=========================");
                                p.sendMessage(ChatColor.RED + "AntiBot is outdated!");
                                p.sendMessage(ChatColor.GREEN + "Newest version: " + this.checker.getLatestVersion());
                                p.sendMessage(ChatColor.RED + "Your version: " + Settings.VERSION);
                                p.sendMessage(ChatColor.GRAY + "=========================");
                            }
                        }               
       }
    }
}
}
    
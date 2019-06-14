package net.noodles.antibot.antibotmain;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class UpdateJoinEventAntiBot implements Listener
{

    public UpdateCheckerAntiBot checker;
    private AntiBotMain main;

    public UpdateJoinEventAntiBot(AntiBotMain main) {
        this.main = main;
        main.getServer().getPluginManager().registerEvents(this, main);
    }
	
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	if (p.hasPermission("antibot.update")) {
    		if (AntiBotMain.getPlugin().getConfig().getBoolean("Update.Enabled") == true){
    		this.checker = new UpdateCheckerAntiBot(AntiBotMain.plugin);
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
    
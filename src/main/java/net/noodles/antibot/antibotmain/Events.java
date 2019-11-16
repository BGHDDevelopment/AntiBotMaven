package net.noodles.antibot.antibotmain;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class Events implements Listener
{
    private ArrayList<String> move;


    private AntiBot main;

    public Events() {
        this.move = new ArrayList<>();

    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
		if (!p.hasPermission("antibot.bypass")) {
    	this.move.add(e.getPlayer().getName());
		}
    }
    
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (this.move.contains(e.getPlayer().getName())) {
            this.move.remove(e.getPlayer().getName());
        }
    }
    
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (this.move.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(AntiBot.getPlugin().getConfig().getString("Messages.antiBotCommand").replace("&", "§"));
        }
    }
    
    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e) {
        if (this.move.contains(e.getPlayer().getName())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(AntiBot.getPlugin().getConfig().getString("Messages.antiBotMessage").replace("&", "§"));
        }
    }
}

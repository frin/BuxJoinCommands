package us.frin.buxjoincommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

class ExecuteCommand implements Runnable {
	BuxJoinCommands plugin;
	Player player;
	
	public ExecuteCommand(Player player, BuxJoinCommands plugin) {
		this.player = player;
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		for (String s : this.plugin.commands) {
			this.player.performCommand(s);
		}
	}
}

public class BuxJoinCommandsListener implements Listener {
		BuxJoinCommands plugin;
		
		public BuxJoinCommandsListener(BuxJoinCommands plugin) {
			this.plugin = plugin;
		}
		
		@EventHandler //(priority = EventPriority.MONITOR)
		public void onPlayerJoin(PlayerJoinEvent evt) {
			Player player = evt.getPlayer(); // The player who joined
			
			ExecuteCommand ec = new ExecuteCommand(player, this.plugin);
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, ec, 0);
		}
		
		@EventHandler // EventPriority.NORMAL by default
		public void onPlayerQuit(PlayerQuitEvent evt) {
//			Player player = evt.getPlayer(); // The player who quit
		}
	}

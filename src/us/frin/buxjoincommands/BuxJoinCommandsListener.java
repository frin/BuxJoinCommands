package us.frin.buxjoincommands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BuxJoinCommandsListener implements Listener {
		BuxJoinCommands plugin;
		
		public BuxJoinCommandsListener(BuxJoinCommands plugin) {
			this.plugin = plugin;
		}
		
		@EventHandler // EventPriority.NORMAL by default
		public void onPlayerJoin(PlayerJoinEvent evt) {
			Player player = evt.getPlayer(); // The player who joined
			for (String s : this.plugin.commands) {
				player.performCommand(s);
			}
		}
		
		@EventHandler // EventPriority.NORMAL by default
		public void onPlayerQuit(PlayerQuitEvent evt) {
//			Player player = evt.getPlayer(); // The player who quit
		}
	}

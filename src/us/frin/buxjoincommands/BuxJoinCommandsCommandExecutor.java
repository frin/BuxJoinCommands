package us.frin.buxjoincommands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuxJoinCommandsCommandExecutor implements CommandExecutor {
	BuxJoinCommands plugin;
	
	public BuxJoinCommandsCommandExecutor(BuxJoinCommands plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("joincommands")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
			}
			else {
				Player player = (Player) sender;
				if (args.length > 0 && args[0].equalsIgnoreCase("reload") && player.hasPermission("buxjoincommands.admin")) {
					//////////////////////////////////
					// JoinCommands reload command
					//////////////////////////////////
					
					this.plugin.reloadConfig();
					this.plugin.loadCommands();
					player.sendMessage(ChatColor.DARK_PURPLE + "[BuxJoinCommands]" + ChatColor.WHITE + " List of loaded commands:");
					for (String s : this.plugin.commands) {
						player.sendMessage(ChatColor.DARK_PURPLE + "[BuxJoinCommands]" + ChatColor.WHITE + " " + s);
					}
					return true;
				}
				else if(player.hasPermission("buxjoincommands.admin")) {
					player.sendMessage(ChatColor.DARK_PURPLE + "[BuxJoinCommands]" + ChatColor.WHITE + " Reload commands with /joincommands reload");
					return true;
				}
			}
			return true;
		}
		return false;
	}
	
}

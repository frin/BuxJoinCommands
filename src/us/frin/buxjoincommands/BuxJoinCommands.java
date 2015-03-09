package us.frin.buxjoincommands;

//import net.milkbowl.vault.economy.Economy;
import java.util.List;

import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import us.frin.buxjoincommands.BuxJoinCommandsCommandExecutor;
import us.frin.buxjoincommands.BuxJoinCommandsListener;

public class BuxJoinCommands extends JavaPlugin {
	public Permission permission = null;
	public List<String> commands = null;
	
	public void loadCommands() {
		this.commands = this.getConfig().getStringList("commands");
	}
	
	@Override
	public void onEnable() {
		// Setup connections to other plugins
		if (!setupPermissions()) {
			getLogger().severe("Disabling BuxFriends - No Permissions Plugin Found");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		// Initialize listener
		getServer().getPluginManager().registerEvents(new BuxJoinCommandsListener(this), this);
		
		// Initialize command handler
		BuxJoinCommandsCommandExecutor executor = new BuxJoinCommandsCommandExecutor(this);
		this.getCommand("joincommands").setExecutor(executor);
		
		this.saveDefaultConfig();
		this.loadCommands();

		getLogger().info("Plugin BuxJoinCommands loaded successfully");
	}
	
	// Vault code
	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
		if (permissionProvider != null) {
			permission = permissionProvider.getProvider();
		}
		return (permission != null);
    }
	
	@Override
	public void onDisable() {
	}
}

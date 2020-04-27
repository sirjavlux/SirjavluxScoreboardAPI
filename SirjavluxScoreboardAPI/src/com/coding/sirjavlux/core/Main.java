package com.coding.sirjavlux.core;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private String consolePrefix = ChatColor.GRAY + "[SirjavluxScoreboardAPI]";
	
	@Override
	public void onEnable() {
		this.getServer().getConsoleSender().sendMessage(consolePrefix + ChatColor.GREEN + " Successfully enabled!");
	}
	
	@Override
	public void onDisable() {
		this.getServer().getConsoleSender().sendMessage(consolePrefix + ChatColor.RED + " Disabled!");
	}
}

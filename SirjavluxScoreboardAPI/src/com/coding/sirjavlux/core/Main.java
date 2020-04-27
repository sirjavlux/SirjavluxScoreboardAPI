package com.coding.sirjavlux.core;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	private String consolePrefix = ChatColor.GRAY + "[SirjavluxScoreboardAPI]";
	  
	@Override
	public void onEnable() {
	    getServer().getConsoleSender().sendMessage(String.valueOf(this.consolePrefix) + ChatColor.GREEN + " Successfully enabled!");
	}

	@Override
	public void onDisable() { getServer().getConsoleSender().sendMessage(String.valueOf(this.consolePrefix) + ChatColor.RED + " Disabled!"); }
}
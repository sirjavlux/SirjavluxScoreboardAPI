package com.coding.sirjavlux.board;

import org.bukkit.ChatColor;

public class SLine {

	private String lineText, definer, prefix, suffix;

	public SLine(String text) {
		this.lineText = ChatColor.translateAlternateColorCodes('&', text);
		getStringParts();
	}
	
	private void getStringParts() {
		if (this.lineText.length() > 48) {
			System.out.print("[SirjavluxScoreboardAPI] Line above 48 chars: " + this.lineText);
			this.lineText = "" + ChatColor.RED + ChatColor.STRIKETHROUGH + "------" + ChatColor.RED + "<Invalid Line>" + ChatColor.STRIKETHROUGH + "------";
		}
		
		if (this.lineText.length() <= 16) {
			this.suffix = this.lineText.substring(1, this.lineText.length());
			this.definer = this.lineText.substring(0, 1);
			this.prefix = "";
		} else if (this.lineText.length() <= 32) {
			this.suffix = this.lineText.substring(this.lineText.length() - 16, this.lineText.length());
			this.definer = this.lineText.substring(0, this.lineText.length() - 16);
			this.prefix = "";
		} else if (this.lineText.length() <= 48) {
			this.suffix = this.lineText.substring(this.lineText.length() - 16, this.lineText.length());
			this.definer = this.lineText.substring(this.lineText.length() - 32, this.lineText.length() - 16);
			this.prefix = this.lineText.substring(0, this.lineText.length() - 32);
		}
	}
	
	public String getSuffix() {
		return this.suffix;
	}
	
	public String getDefiner() {
		return this.definer;
	}
	
	public void setDefiner(String str) {
		this.definer = str;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public String getLineText() {
		return this.lineText;
	}
}
